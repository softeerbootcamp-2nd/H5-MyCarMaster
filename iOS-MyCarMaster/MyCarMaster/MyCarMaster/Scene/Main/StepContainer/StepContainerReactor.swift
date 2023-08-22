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
#if DEBUG
        case dumpEstimation
#endif
    }

    enum Mutation {
        case setLoading(Bool)
        case changeStepProgress(Step)
        case changeStepViewController(UIViewController)
#if DEBUG
        case dumpEstimation(Estimation)
#endif
    }

    struct State {
        var isLoading: Bool
        var currentStep: Step
        var currentStepViewController: UIViewController
    }

    let initialState: State
    weak var stepRouter: StepRouter?

    init(initialState: State, stepRouter: StepRouter) {
        self.initialState = initialState
        self.stepRouter = stepRouter
    }

#if DEBUG
    weak var estimationManager: EstimationManager?
    init(initialState: State, stepRouter: StepRouter, estimationManager: EstimationManager) {
        self.initialState = initialState
        self.stepRouter = stepRouter
        self.estimationManager = estimationManager
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .dumpEstimation:
            guard let estimation = estimationManager.map(\.estimation) else {
                return Empty<Mutation, Never>()
                    .eraseToAnyPublisher()
            }

            return Just<Mutation>(Mutation.dumpEstimation(estimation))
                .eraseToAnyPublisher()
        }
    }
#endif

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let stepRouter else {
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        }

        let stepMuatation = stepRouter.currentStepPublisher
            .flatMap { step -> AnyPublisher<Mutation, Never> in
                return [
                    Just(Mutation.changeStepProgress(step)),
                ].concatenate()
            }

        let stepViewControllerMutation = stepRouter.currentStepViewController
            .flatMap { stepViewController -> AnyPublisher<Mutation, Never> in
                return Just(Mutation.changeStepViewController(stepViewController))
                    .eraseToAnyPublisher()
            }

        return Publishers.Merge3(mutation, stepMuatation, stepViewControllerMutation)
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
#if DEBUG
        case let .dumpEstimation(estimation):
            dump(estimation)
#endif
        }
        return newState
    }
}
