//
//  WheelDriveReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class WheelDriveReactor: Reactor {

    enum Action {
        case viewDidLoad
        case wheelDriveDidSelect(WheelDrive)
        case dataSourceDidApply
        case fetchWheelDriveImage(URL)
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchWheelDriveList([WheelDrive])
        case fetchSelectedWheelDrive(WheelDrive?)
        case alertError(String)
        case fetchWheelDriveImage(UIImage)
    }

    struct State {
        var isLoading: Bool
        var wheelDriveList: [WheelDrive]
        var selectedWheelDrive: WheelDrive?
        var selectedWheelDriveImage: UIImage?
        var errorDescription: String?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        initialState: WheelDriveReactor.State,
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
                fetchWheelDriveList(),
                Just(Mutation.setLoading(false)).eraseToAnyPublisher(),
            ].concatenate()
        case let .wheelDriveDidSelect(wheelDrive):
            return updateWheelDrive(wheelDrive)
        case .dataSourceDidApply:
            return fetchSelectedWheelDrive()
        case let .fetchWheelDriveImage(url):
            return fetchWheelDriveImage(url)
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.wheelDrive)
            .flatMap({ wheelDrive in
                return Just(Mutation.fetchSelectedWheelDrive(wheelDrive))
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
        case let .fetchWheelDriveList(wheelDriveList):
            newState.wheelDriveList = wheelDriveList
        case let .fetchSelectedWheelDrive(wheelDrive):
            newState.selectedWheelDrive = wheelDrive
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        case let .fetchWheelDriveImage(image):
            newState.selectedWheelDriveImage = image
        }
        return newState
    }
}

extension WheelDriveReactor {
    private func updateWheelDrive(_ wheelDrive: WheelDrive) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.wheelDrive, value: wheelDrive)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedWheelDrive() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let selectedWheelDrive = estimationManager.estimation.wheelDrive
        return Just(Mutation.fetchSelectedWheelDrive(selectedWheelDrive))
            .eraseToAnyPublisher()
    }
}

// MARK: Network
extension WheelDriveReactor {
    private func fetchWheelDriveImage(_ url: URL) -> AnyPublisher<Mutation, Never> {
        if let image = ImageCacheManager.shared.object(forKey: url as NSURL) {
            return Just(Mutation.fetchWheelDriveImage(image))
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

                return Mutation.fetchWheelDriveImage(image)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
            .eraseToAnyPublisher()
    }

    private func fetchWheelDriveList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchWheelDriveListFromNetwork()
#elseif OFFLINE
        fetchWheelDriveListFromCache()
#endif
    }

    private func fetchWheelDriveListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let trimId = estimationManager?.estimation.trim?.id,
              let engineId = estimationManager?.estimation.engine?.id
        else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 트림 또는 엔진을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchWheelDrive(trimId: trimId, engineId: engineId))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decodeToWheelDrive()
    }

    private func fetchWheelDriveListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "WheelDriveDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decodeToWheelDrive()
    }
}

fileprivate extension Publisher where Output == Data {
    typealias Mutation = WheelDriveReactor.Mutation
    func decodeToWheelDrive() -> AnyPublisher<Mutation, Never> {
        self
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let wheelDriveDTOList = response.result.wheelDrives else {
                    throw URLError(.cannotDecodeRawData)
                }
                let wheelDriveList = wheelDriveDTOList.map { WheelDrive($0) }
                return Mutation.fetchWheelDriveList(wheelDriveList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
                .eraseToAnyPublisher()
    }
}
