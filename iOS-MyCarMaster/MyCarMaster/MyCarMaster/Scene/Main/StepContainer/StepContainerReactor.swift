//
//  StepContainerReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

import MVIFoundation

final class StepContainerReactor: Reactor {

    enum Action {
        case moveNext
        case moveBack
    }

    enum Mutation {
        case setLoading(Bool)
        case changeStepProgress(Step)
        case changeStepViewController(UIViewController)
    }

    struct State {
        var isLoading: Bool
        var currentStep: Step
        var currentStepViewController: UIViewController
    }

    let initialState: State
    let stepRouter: StepRouter

    init(initialState: State, stepRouter: StepRouter) {
        self.initialState = initialState
        self.stepRouter = stepRouter
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .moveNext:
            stepRouter.nextStep()
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        case .moveBack:
            stepRouter.backStep()
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        let routerMuatation = stepRouter.currentStepPublisher
            .flatMap { step -> AnyPublisher<Mutation, Never> in
                return [
                    Just(Mutation.changeStepViewController(self.stepRouter.resolveStepViewController(for: step))),
                    Just(Mutation.changeStepProgress(step)),
                ].concatenate()
            }

        return Publishers.Merge(mutation, routerMuatation)
            .eraseToAnyPublisher()
    }

    func reduce(state: State, mutation: Mutation) -> State {
        var newState = state
        switch mutation {
        case let .changeStepViewController(viewController):
            newState.currentStepViewController = viewController
        case let .setLoading(isLoading):
            newState.isLoading = isLoading
        case let .changeStepProgress(step):
            newState.currentStep = step
        }
        return newState
    }
}
