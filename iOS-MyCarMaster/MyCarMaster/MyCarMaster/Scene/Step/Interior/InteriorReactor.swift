//
//  InteriorReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class InteriorReactor: Reactor {

    enum Action {
        case viewDidLoad
        case interiorDidSelect(Interior)
        case dataSourceDidApply
        case fetchInteriorImage(URL)
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchInteriorList([Interior])
        case fetchSelectedInterior(Interior?)
        case alertError(String)
        case fetchInteriorImage(UIImage)
    }

    struct State {
        var isLoading: Bool
        var interiorList: [Interior]
        var selectedInterior: Interior?
        var selectedInteriorImage: UIImage?
        var errorDescription: String?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        initialState: InteriorReactor.State,
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
                fetchInteriorList(),
                Just(Mutation.setLoading(false)).eraseToAnyPublisher(),
            ].concatenate()
        case let .interiorDidSelect(interior):
            return updateInterior(interior)
        case .dataSourceDidApply:
            return fetchSelectedInterior()
        case let .fetchInteriorImage(url):
            return fetchInteriorImage(url)
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.interior)
            .flatMap({ interior in
                return Just(Mutation.fetchSelectedInterior(interior))
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
        case let .fetchInteriorList(interiorList):
            newState.interiorList = interiorList
        case let .fetchSelectedInterior(interior):
            newState.selectedInterior = interior
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        case let .fetchInteriorImage(image):
            newState.selectedInteriorImage = image
        }
        return newState
    }
}

extension InteriorReactor {
    private func updateInterior(_ interior: Interior) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.interior, value: interior)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedInterior() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let selectedInterior = estimationManager.estimation.interior
        return Just(Mutation.fetchSelectedInterior(selectedInterior))
            .eraseToAnyPublisher()
    }
}

// MARK: Network
extension InteriorReactor {
    private func fetchInteriorImage(_ url: URL) -> AnyPublisher<Mutation, Never> {
        if let image = ImageCacheManager.shared.object(forKey: url as NSURL) {
            return Just(Mutation.fetchInteriorImage(image))
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

                return Mutation.fetchInteriorImage(image)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }

    private func fetchInteriorList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchInteriorListFromNetwork()
#elseif OFFLINE
        fetchInteriorListFromCache()
#endif
    }

    private func fetchInteriorListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let trimId = estimationManager?.estimation.trim?.id,
              let exteriorColorId = estimationManager?.estimation.exterior?.id
        else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 트림 또는 외장색상을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchInterior(trimId: trimId, exteriorColorId: exteriorColorId))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decodeToInterior()
    }

    private func fetchInteriorListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "InteriorDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decodeToInterior()
    }
}

fileprivate extension Publisher where Output == Data {
    typealias Mutation = InteriorReactor.Mutation
    func decodeToInterior() -> AnyPublisher<Mutation, Never> {
        self
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let interiorDTOList = response.result.interiors else {
                    throw URLError(.cannotDecodeRawData)
                }
                let interiorList = interiorDTOList.map { Interior($0) }
                return Mutation.fetchInteriorList(interiorList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
                .eraseToAnyPublisher()
    }
}
