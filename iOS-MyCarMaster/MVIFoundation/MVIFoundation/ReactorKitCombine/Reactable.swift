//
//  ReactableView.swift
//  MVIFoundation
//
//  Created by SEUNGMIN OH on 2023/08/07.
//

import Combine

private typealias AnyReactable = AnyObject

private enum MapTables {
    static let reactor = WeakMapTable<AnyReactable, Any>()
}

public protocol Reactable: AnyObject {
    
    associatedtype Reactor

    var cancellables: Set<AnyCancellable> { get set }

    var reactor: Reactor? { get set }

    /// - warning: It's not recommended to call this method directly.
    func bindState(reactor: Reactor)
}

// MARK: - Default Implementations

extension Reactable {
    public var reactor: Reactor? {
        get {
            guard let reactor = MapTables.reactor.value(forKey: self) as? Reactor else {
                fatalError("사용하기 전에, 먼저 Reactor을 등록하세요")
            }
            return reactor
        }
        set {
            MapTables.reactor.setValue(newValue, forKey: self)

            cancellables = Set<AnyCancellable>()

            if let reactor = newValue {
                bindState(reactor: reactor)
            }
        }
    }
}
