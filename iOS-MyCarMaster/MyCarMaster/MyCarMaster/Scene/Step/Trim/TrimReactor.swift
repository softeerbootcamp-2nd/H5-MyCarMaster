//
//  TrimReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/22.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class TrimReactor: Reactor {

    enum Action {
        case viewDidLoad
        case trimDidSelect(Trim)
        case shouldSelectTrim(Trim)
        case dataSourceDidApply
        case resetAndSelectTrim(Trim)
        case fetchTrimImage(URL)
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchTrimList([Trim])
        case fetchSelectedTrim(Trim?)
        case showSelectionAlert(Trim)
        case alertError(String)
        case fetchTrimImage(UIImage)
    }

    struct State {
        var isLoading: Bool
        var trimList: [Trim]
        var selectedTrim: Trim?
        var selectedTrimImage: UIImage?
        var showSelectionAlert: Trim?
        var errorDescription: String?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    internal init(
        initialState: TrimReactor.State,
        estimationManager: EstimationManageable?,
        stepNetworkProvider: NetworkProvider<StepTarget>?
    ) {
        self.initialState = initialState
        self.estimationManager = estimationManager
        self.stepNetworkProvider = stepNetworkProvider
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case let .trimDidSelect(trim):
            return updateTrim(trim)
        case .viewDidLoad:
            return [
                Just(Mutation.setLoading(true))
                    .eraseToAnyPublisher(),
                fetchTrimList(),
                Just(Mutation.setLoading(false))
                    .eraseToAnyPublisher(),
            ].concatenate()
        case .dataSourceDidApply:
            return fetchSelectedTrim()
        case let .shouldSelectTrim(trim):
            return changeTrimIfCan(trim)
        case let .resetAndSelectTrim(trim):
            return [
                resetEstimation(),
                updateTrim(trim)
            ].concatenate()
        case let .fetchTrimImage(url):
            return fetchTrimImage(url)
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.trim)
            .flatMap({ trim in
                return Just(Mutation.fetchSelectedTrim(trim))
                    .eraseToAnyPublisher()
            })
            .eraseToAnyPublisher()

        return Publishers.Merge(mutation, esitmationMutation)
            .eraseToAnyPublisher()
    }

    func reduce(state: State, mutation: Mutation) -> State {
        var newState = state
        switch mutation {
        case let .setLoading(isLoading):
            newState.isLoading = isLoading
        case let .fetchTrimList(trimList):
            newState.trimList = trimList
        case let .fetchSelectedTrim(trim):
            newState.selectedTrim = trim
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        case let .showSelectionAlert(trim):
            newState.showSelectionAlert = trim
        case let .fetchTrimImage(image):
            newState.selectedTrimImage = image
        }
        return newState
    }
}

extension TrimReactor {
    private func updateTrim(_ trim: Trim) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.trim, value: trim)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedTrim() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        return Just(Mutation.fetchSelectedTrim(estimationManager.estimation.trim))
            .eraseToAnyPublisher()
    }

    private func changeTrimIfCan(_ trim: Trim) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        if estimationManager.checkTrimCanChange() {
            return updateTrim(trim)
        } else {
            return Just(Mutation.showSelectionAlert(trim))
                .eraseToAnyPublisher()
        }
    }

    private func resetEstimation() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.removeAll()
        return Empty().eraseToAnyPublisher()
    }
}

extension TrimReactor {
    private func fetchTrimImage(_ url: URL) -> AnyPublisher<Mutation, Never> {
        if let image = ImageCacheManager.shared.object(forKey: url as NSURL) {
            return Just(Mutation.fetchTrimImage(image))
                .eraseToAnyPublisher()
        }

        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        return stepNetworkProvider.requestPublisher(.fetchImage(url: url))
            .retry(1)
            .tryMap({ element -> Mutation in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }

                guard let image = UIImage(data: element.data) else {
                    throw URLError(.badServerResponse)
                }
                ImageCacheManager.shared.setObject(image, forKey: url as NSURL)

                return Mutation.fetchTrimImage(image)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }

    private func fetchTrimList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchTrimListFromNetwork()
#elseif OFFLINE
        fetchTrimListFromCache()
#endif
    }

    private func fetchTrimListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let modelId = estimationManager?.estimation.model?.id else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 모델을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchTrim(modelId: modelId))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let trimDTOList = response.result.trims else {
                    throw URLError(.cannotDecodeRawData)
                }
                let trimList = trimDTOList.map { Trim($0) }
                return Mutation.fetchTrimList(trimList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }

    private func fetchTrimListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "TrimDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let trimDTOList = response.result.trims else {
                    throw URLError(.cannotDecodeRawData)
                }
                let trimList = trimDTOList.map { Trim($0) }
                return Mutation.fetchTrimList(trimList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }
}
