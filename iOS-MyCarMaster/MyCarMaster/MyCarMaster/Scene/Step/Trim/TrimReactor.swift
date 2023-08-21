//
//  TrimReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/22.
//

import Combine
import UIKit

import MVIFoundation

final class TrimReactor: Reactor {

    enum Action {
        case viewDidAppear
        case trimDidSelect(Trim)
    }

    enum Mutation {
        case setLoading(Bool)
        case fetchTrimList([Trim])
        case fetchSelectedTrim(Trim?)
    }

    struct State {
        var isLoading: Bool
        var trimList: [Trim]
        var selectedTrim: Trim?
    }

    let initialState: State
    weak var estimationManager: EstimationManageable?

    internal init(initialState: TrimReactor.State, estimationManager: EstimationManageable?) {
        self.initialState = initialState
        self.estimationManager = estimationManager
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case let .trimDidSelect(trim):
            return updateTrim(trim)
        case .viewDidAppear:
            return fetchSelectedTrim()
        }
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
        }
        return state
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
}
