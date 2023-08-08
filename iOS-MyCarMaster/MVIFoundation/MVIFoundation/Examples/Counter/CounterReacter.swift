//
//  CounterReacter.swift
//  MVIFoundation
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Combine

final class Counter: Reactor {

    struct State {
        var count = 0
        var isLoading: Bool = false
        var alertMessage: String?
    }

    enum Mutation {
        case increaseValue
        case decreaseValue
        case setLoading(Bool)
        case setAlertMessage(String)
    }

    enum Action {
        case decrement
        case increment
    }

    let initialState: State

    init() {
        self.initialState = State(
            count: 0,
            isLoading: false
        )
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .increment:
            return [
                Just(Mutation.setLoading(true))
                    .eraseToAnyPublisher(),
                Just(Mutation.increaseValue)
                    .delay(for: .milliseconds(500), scheduler: RunLoop.main)
                    .eraseToAnyPublisher(),
                Just(Mutation.setLoading(false))
                    .eraseToAnyPublisher(),
                Just(Mutation.setAlertMessage("increased!"))
                    .eraseToAnyPublisher()
            ]
                .concatenate()
                .eraseToAnyPublisher()

        case.decrement:
            return [
                Just(Mutation.setLoading(true))
                    .eraseToAnyPublisher(),
                Just(Mutation.decreaseValue)
                    .delay(for: .milliseconds(500), scheduler: RunLoop.main)
                    .eraseToAnyPublisher(),
                Just(Mutation.setLoading(false))
                    .eraseToAnyPublisher(),
                Just(Mutation.setAlertMessage("decreased!"))
                    .eraseToAnyPublisher()
            ]
                .concatenate()
                .eraseToAnyPublisher()
        }
    }

    func reduce(state: State, mutation: Mutation) -> State {
        var state = state
        switch mutation {
        case .increaseValue:
            state.count += 1
        case .decreaseValue:
            state.count -= 1
        case let .setLoading(isLoading):
            state.isLoading = isLoading
        case let .setAlertMessage(message):
            state.alertMessage = message
        }
        return state
    }
}
