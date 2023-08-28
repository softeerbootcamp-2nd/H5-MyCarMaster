//
//  EngineReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/27.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class EngineReactor: Reactor {

    enum Action {
        case viewDidLoad
        case engineDidSelect(Engine)
        case dataSourceDidApply
        case fetchEngineImage(URL)
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchEngineList([Engine])
        case fetchSelectedEngine(Engine?)
        case alertError(String)
        case fetchEngineImage(UIImage)
    }

    struct State {
        var isLoading: Bool
        var engineList: [Engine]
        var selectedEngine: Engine?
        var selectedEngineImage: UIImage?
        var errorDescription: String?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        initialState: EngineReactor.State,
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
                fetchEngineList(),
                Just(Mutation.setLoading(false)).eraseToAnyPublisher(),
            ].concatenate()
        case let .engineDidSelect(engine):
            return updateEngine(engine)
        case .dataSourceDidApply:
            return fetchSelectedEngine()
        case let .fetchEngineImage(url):
            return fetchEngineImage(url)
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.engine)
            .flatMap({ engine in
                return Just(Mutation.fetchSelectedEngine(engine))
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
        case let .fetchEngineList(engineList):
            newState.engineList = engineList
        case let .fetchSelectedEngine(engine):
            newState.selectedEngine = engine
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        case let .fetchEngineImage(image):
            newState.selectedEngineImage = image
        }
        return newState
    }
}

extension EngineReactor {
    private func fetchEngineImage(_ url: URL) -> AnyPublisher<Mutation, Never> {
        if let image = ImageCacheManager.shared.object(forKey: url as NSURL) {
            return Just(Mutation.fetchEngineImage(image))
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

                return Mutation.fetchEngineImage(image)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }

    private func updateEngine(_ engine: Engine) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.engine, value: engine)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedEngine() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let selectedEngine = estimationManager.estimation.engine
        return Just(Mutation.fetchSelectedEngine(selectedEngine))
            .eraseToAnyPublisher()
    }
}

// MARK: Network
extension EngineReactor {
    private func fetchEngineList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchEngineListFromNetwork()
#elseif OFFLINE
        fetchEngineListFromCache()
#endif
    }

    private func fetchEngineListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let trimId = estimationManager?.estimation.trim?.id else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 트림을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchEngine(trimId: trimId))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decodeToEngine()
    }

    private func fetchEngineListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "EngineDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decodeToEngine()
    }
}

fileprivate extension Publisher where Output == Data {
    typealias Mutation = EngineReactor.Mutation
    func decodeToEngine() -> AnyPublisher<Mutation, Never> {
        self
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let engineDTOList = response.result.engines else {
                    throw URLError(.cannotDecodeRawData)
                }
                let engineList = engineDTOList.map { Engine($0) }
                return Mutation.fetchEngineList(engineList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
                .eraseToAnyPublisher()
    }
}
