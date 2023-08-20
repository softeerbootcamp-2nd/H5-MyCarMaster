//
//  MainReactor.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

import MVIFoundation

final class MainReactor: Reactor {
    
    enum Action {
        case moveNext
        case moveBack
    }
    
    enum Mutation {
        case setLoading(Bool)
        case changeStepViewController(UIViewController)
    }
    
    struct State {
        var isLoading: Bool
        var currentStepViewController: UIViewController
    }
    
    let initialState: State
    let router: Router
    
    init(initialState: State, router: Router) {
        self.initialState = initialState
        self.router = router
    }
    
    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .moveNext:
            router.nextStep()
            return Just(Mutation.setLoading(true))
                .eraseToAnyPublisher()
        case .moveBack:
            router.backStep()
            return Just(Mutation.setLoading(true))
                .eraseToAnyPublisher()
        }
    }
    
    func transform(mutation: AnyPublisher<Mutation, Never>) -> AnyPublisher<Mutation, Never> {
        let routerMuatation = router.currentStepPublisher
            .flatMap { step -> AnyPublisher<Mutation, Never> in
                return [
                    Just(Mutation.changeStepViewController(DIContainer.resolveViewController(for: step))),
                    Just(Mutation.setLoading(false))
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
        }
        return newState
    }
}
