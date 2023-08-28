//
//  ExteriorReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class ExteriorReactor: Reactor {

    enum Action {
        case viewDidLoad
        case exteriorDidSelect(Exterior)
        case dataSourceDidApply
        case fetchExteriorImage(URL)
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchExteriorList([Exterior])
        case fetchSelectedExterior(Exterior?)
        case alertError(String)
        case fetchExteriorImage(UIImage)
    }

    struct State {
        var isLoading: Bool
        var exteriorList: [Exterior]
        var selectedExterior: Exterior?
        var errorDescription: String?
        var selectedExteriorImage: UIImage?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        initialState: ExteriorReactor.State,
        estimationManager: EstimationManageable?,
        stepNetworkProvider: NetworkProvider<StepTarget>?
    ) {
        self.initialState = initialState
        self.estimationManager = estimationManager
        self.stepNetworkProvider = stepNetworkProvider
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .viewDidLoad:
            return [
                Just(Mutation.setLoading(true)).eraseToAnyPublisher(),
                fetchExteriorList(),
                Just(Mutation.setLoading(false)).eraseToAnyPublisher(),
            ].concatenate()
        case let .exteriorDidSelect(exterior):
            return updateExterior(exterior)
        case .dataSourceDidApply:
            return fetchSelectedExterior()
        case let .fetchExteriorImage(url):
            return fetchExteriorImage(url)
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.exterior)
            .flatMap({ exterior in
                return Just(Mutation.fetchSelectedExterior(exterior))
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
        case let .fetchExteriorList(exteriorList):
            newState.exteriorList = exteriorList
        case let .fetchSelectedExterior(exterior):
            newState.selectedExterior = exterior
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        case let .fetchExteriorImage(image):
            newState.selectedExteriorImage = image
        }
        return newState
    }
}

extension ExteriorReactor {
    private func updateExterior(_ exterior: Exterior) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.exterior, value: exterior)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedExterior() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let selectedExterior = estimationManager.estimation.exterior
        return Just(Mutation.fetchSelectedExterior(selectedExterior))
            .eraseToAnyPublisher()
    }
}

// MARK: Network
extension ExteriorReactor {
    private func fetchExteriorImage(_ url: URL) -> AnyPublisher<Mutation, Never> {
        if let image = ImageCacheManager.shared.object(forKey: url as NSURL) {
            return Just(Mutation.fetchExteriorImage(image))
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

                return Mutation.fetchExteriorImage(image)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }

    private func fetchExteriorList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchExteriorListFromNetwork()
#elseif OFFLINE
        fetchExteriorListFromCache()
#endif
    }

    private func fetchExteriorListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let trimId = estimationManager?.estimation.trim?.id else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 트림을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchExterior(trimId: trimId))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decodeToExterior()
    }

    private func fetchExteriorListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "ExteriorDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decodeToExterior()
    }
}

fileprivate extension Publisher where Output == Data {
    typealias Mutation = ExteriorReactor.Mutation
    func decodeToExterior() -> AnyPublisher<Mutation, Never> {
        self
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let exteriorDTOList = response.result.exteriors else {
                    throw URLError(.cannotDecodeRawData)
                }
                let exteriorList = exteriorDTOList.map { Exterior($0) }
                return Mutation.fetchExteriorList(exteriorList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
                .eraseToAnyPublisher()
    }
}
