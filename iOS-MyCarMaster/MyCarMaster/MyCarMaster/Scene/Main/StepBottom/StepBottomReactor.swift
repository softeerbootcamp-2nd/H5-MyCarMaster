//
//  StepBottomReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import Combine
import UIKit

import MVIFoundation

final class StepBottomReactor: Reactor {

    weak var stepRouter: StepRouter?
    weak var estimationManager: EstimationManager?

    var initialState: State

    init(
        stepRouter: StepRouter,
        estimationManager: EstimationManager,
        initialState: State
    ) {
        self.stepRouter = stepRouter
        self.estimationManager = estimationManager
        self.initialState = initialState
    }

    enum Action {
        case leftButtonDidTap
        case rightButtonDidTap
        case summaryButtonDidTap
    }

    enum Mutation {
        case changeTotalPrice(Int)
        case changeLeftButtonTitle(String?)
        case changeRightButtonTitle(String?)
    }

    struct State {
        var leftButtonTitle: String?
        var rightButtonTitle: String?
        var totalPrice: Int
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .leftButtonDidTap:
            stepRouter?.backStep()
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        case .rightButtonDidTap:
            stepRouter?.nextStep()
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        case .summaryButtonDidTap:
            stepRouter?.presentEstimation()
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        }
    }

    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        guard let stepRouter, let estimationManager else {
            return Empty<Mutation, Never>()
                .eraseToAnyPublisher()
        }

        let routeMutation = stepRouter.currentStepPublisher
            .flatMap { step -> AnyPublisher<Mutation, Never> in
                return [
                    Just(Mutation.changeLeftButtonTitle(step.leftButtonTitle)),
                    Just(Mutation.changeRightButtonTitle(step.rightButtonTitle))
                ].concatenate()
            }

        let estimationMutation = estimationManager.estimationPublisher
            .map(\.totalPrice)
            .flatMap { totalPrice -> AnyPublisher<Mutation, Never> in
                return Just(Mutation.changeTotalPrice(totalPrice))
                    .eraseToAnyPublisher()
            }

        return Publishers.Merge3(mutation, routeMutation, estimationMutation)
            .eraseToAnyPublisher()
    }

    func reduce(state: State, mutation: Mutation) -> State {
        var newState = state
        switch mutation {
        case let .changeTotalPrice(totalPrice):
            newState.totalPrice = totalPrice
        case let .changeLeftButtonTitle(title):
            newState.leftButtonTitle = title
        case let .changeRightButtonTitle(title):
            newState.rightButtonTitle = title
        }
        return newState
    }
}
