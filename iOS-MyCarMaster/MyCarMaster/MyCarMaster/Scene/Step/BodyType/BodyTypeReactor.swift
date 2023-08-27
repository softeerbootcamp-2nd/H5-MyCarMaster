//
//  BodyTypeReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class BodyTypeReactor: Reactor {

    enum Action {
        case viewDidLoad
        case bodyTypeDidSelect(BodyType)
        case dataSourceDidApply
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchBodyTypeList([BodyType])
        case fetchSelectedBodyType(BodyType?)
        case alertError(String)
    }

    struct State {
        var isLoading: Bool
        var bodyTypeList: [BodyType]
        var selectedBodyType: BodyType?
        var errorDescription: String?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        initialState: BodyTypeReactor.State,
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
                fetchBodyTypeList(),
                Just(Mutation.setLoading(false)).eraseToAnyPublisher(),
            ].concatenate()
        case let .bodyTypeDidSelect(bodyType):
            return updateBodyType(bodyType)
        case .dataSourceDidApply:
            return fetchSelectedBodyType()
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.bodyType)
            .flatMap({ bodyType in
                return Just(Mutation.fetchSelectedBodyType(bodyType))
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
        case let .fetchBodyTypeList(bodyTypeList):
            newState.bodyTypeList = bodyTypeList
        case let .fetchSelectedBodyType(bodyType):
            newState.selectedBodyType = bodyType
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        }
        return newState
    }
}

extension BodyTypeReactor {
    private func updateBodyType(_ bodyType: BodyType) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.bodyType, value: bodyType)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedBodyType() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let selectedBodyType = estimationManager.estimation.bodyType
        return Just(Mutation.fetchSelectedBodyType(selectedBodyType))
            .eraseToAnyPublisher()
    }
}

// MARK: Network
extension BodyTypeReactor {
    private func fetchBodyTypeList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchBodyTypeListFromNetwork()
#elseif OFFLINE
        fetchBodyTypeListFromCache()
#endif
    }

    private func fetchBodyTypeListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let modelId = estimationManager?.estimation.model?.id else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 모델을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchBodyType(modelId: modelId))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decodeToBodyType()
    }

    private func fetchBodyTypeListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "BodyTypeDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decodeToBodyType()
    }
}

fileprivate extension Publisher where Output == Data {
    typealias Mutation = BodyTypeReactor.Mutation
    func decodeToBodyType() -> AnyPublisher<Mutation, Never> {
        self
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let bodyTypeDTOList = response.result.bodyTypes else {
                    throw URLError(.cannotDecodeRawData)
                }
                let bodyTypeList = bodyTypeDTOList.map { BodyType($0) }
                return Mutation.fetchBodyTypeList(bodyTypeList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
                .eraseToAnyPublisher()
    }
}
