//
//  OptionReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class OptionReactor: Reactor {

    enum Action {
        case viewDidLoad
        case optionDidSelect(Option)
        case optionDidConsider(Option)
        case optionDidUnselect(Option)
        case dataSourceDidApply
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchOptionList([Option])
        case fetchSelectedOptions([Option])
        case fetchConsideredOptions([Option])
        case alertError(String)
    }

    struct State {
        var isLoading: Bool
        var optionList: [Option]
        var selectedOptions: [Option]
        var consideredOptions: [Option]
        var errorDescription: String?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        initialState: OptionReactor.State,
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
                fetchOptionList(),
                Just(Mutation.setLoading(false)).eraseToAnyPublisher(),
            ].concatenate()
        case let .optionDidSelect(option):
            return updateSelectedOption(option)
        case let .optionDidConsider(option):
            return updateConsideredOption(option)
        case let .optionDidUnselect(option):
            return updateUnselectedOption(option)
        case .dataSourceDidApply:
            return [
                fetchSelectedOptions(),
                fetchConsideredOptions()
            ].concatenate()
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let esitmationMutation = estimationManager.estimationPublisher.map(\.selectedOptions, \.consideredOptions)
            .flatMap({ selectedOptions, consideredOptions in
                return Publishers.Merge(
                    Just(Mutation.fetchSelectedOptions(Array(selectedOptions))),
                    Just(Mutation.fetchConsideredOptions(Array(consideredOptions)))
                )
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
        case let .fetchOptionList(optionList):
            newState.optionList = optionList
        case let .fetchSelectedOptions(selectedOptions):
            newState.selectedOptions = selectedOptions
        case let .fetchConsideredOptions(consideredOptions):
            newState.consideredOptions = consideredOptions
        case let .alertError(errorDescription):
            newState.errorDescription = errorDescription
        }
        return newState
    }
}

extension OptionReactor {
    private func updateSelectedOption(_ option: Option) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.selectedOptions, value: option)
        return Empty().eraseToAnyPublisher()
    }

    private func updateConsideredOption(_ option: Option) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.update(\.consideredOptions, value: option)
        return Empty().eraseToAnyPublisher()
    }

    private func updateUnselectedOption(_ option: Option) -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        estimationManager.remove(value: option)
        return Empty().eraseToAnyPublisher()
    }

    private func fetchSelectedOptions() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let selectedOptions = estimationManager.estimation.selectedOptions
        return Just(Mutation.fetchSelectedOptions(Array(selectedOptions)))
            .eraseToAnyPublisher()
    }

    private func fetchConsideredOptions() -> AnyPublisher<Mutation, Never> {
        guard let estimationManager else {
            return Empty().eraseToAnyPublisher()
        }

        let consideredOptions = estimationManager.estimation.consideredOptions
        return Just(Mutation.fetchConsideredOptions(Array(consideredOptions)))
            .eraseToAnyPublisher()
    }
}

// MARK: Network
extension OptionReactor {
    private func fetchOptionList() -> AnyPublisher<Mutation, Never> {
#if ONLINE
        fetchOptionListFromNetwork()
#elseif OFFLINE
        fetchOptionListFromCache()
#endif
    }

    private func fetchOptionListFromNetwork() -> AnyPublisher<Mutation, Never> {
        guard let stepNetworkProvider else {
            fatalError("개발자 오류: StepProvider가 존재하지 않음")
        }

        guard let trimId = estimationManager?.estimation.trim?.id,
              let engineId = estimationManager?.estimation.engine?.id,
              let wheelDriveId = estimationManager?.estimation.wheelDrive?.id,
              let bodyTypeId = estimationManager?.estimation.bodyType?.id,
              let interiorId = estimationManager?.estimation.interior?.id
        else {
            return Just(Mutation.alertError("잘못된 접근 경로입니다: 트림을 선택하지 않았습니다."))
                .eraseToAnyPublisher()
        }

        return stepNetworkProvider.requestPublisher(.fetchOption(
            trimId: trimId,
            engineId: engineId,
            wheelDriveId: wheelDriveId,
            bodyTypeId: bodyTypeId,
            interiorColorId: interiorId
        ))
            .retry(1)
            .tryMap({ element -> Data in
                guard let httpResponse = element.response as? HTTPURLResponse,
                      httpResponse.statusCode == 200 else {
                    throw URLError(.badServerResponse)
                }
                return element.data
            })
            .decodeToOption()
    }

    private func fetchOptionListFromCache() -> AnyPublisher<Mutation, Never> {
        guard let fileURL = Bundle.main.url(forResource: "OptionDTO", withExtension: "json") else {
            fatalError("개발자 에러: 파일이 존재하지 않습니다.")
        }
        guard let data = try? Data(contentsOf: fileURL) else {
            fatalError("개발자 에러: 유효하지 않은 파일입니다")
        }

        return Just(data)
            .decodeToOption()
    }
}

fileprivate extension Publisher where Output == Data {
    typealias Mutation = OptionReactor.Mutation
    func decodeToOption() -> AnyPublisher<Mutation, Never> {
        self
            .decode(type: RootDTO.self, decoder: JSONDecoder())
            .tryMap({ response -> Mutation in
                guard let optionDTOList = response.result.options else {
                    throw URLError(.cannotDecodeRawData)
                }
                let optionList = optionDTOList.map { Option($0) }
                return Mutation.fetchOptionList(optionList)
            })
            .catch({ error in
                return Just(Mutation.alertError(error.localizedDescription))
            })
                .eraseToAnyPublisher()
    }
}
