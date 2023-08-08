//
//  Stub.swift
//  MVIFoundation
//
//  Created by SEUNGMIN OH on 2023/08/07.
//

import Combine
import MCMCombineExtension

public class Stub<Reactor: MVIFoundation.Reactor> {

    private unowned var reactor: Reactor
    private var cancellables: Set<AnyCancellable>

    public let state: CurrentValueRelay<Reactor.State>
    public let action: PassthroughRelay<Reactor.Action>
    public private(set) var actions: [Reactor.Action] = []

    public init(reactor: Reactor, cancellables: Set<AnyCancellable>) {
        self.reactor = reactor
        self.cancellables = cancellables
        self.state = CurrentValueRelay(reactor.initialState)
        self.state
            .sink(receiveValue: { [weak reactor] state in
                reactor?.currentState = state
            })
            .store(in: &self.cancellables)
        self.action = PassthroughRelay()
        self.action
            .sink(receiveValue: { [weak self] action in
                self?.actions.append(action)
            })
            .store(in: &self.cancellables)
    }
}
