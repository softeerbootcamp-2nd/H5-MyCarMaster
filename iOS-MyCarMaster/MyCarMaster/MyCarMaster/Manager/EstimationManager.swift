//
//  EstimationManager.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import Combine

import MCMCombineExtension

typealias Estimation = Quotation

final class EstimationManager {

    private let estimationSubject: CurrentValueRelay<Estimation>
    var estimation: Estimation {
        return estimationSubject.value
    }
    lazy var estimationPublisher = estimationSubject
        .share()
        .eraseToAnyPublisher()

    init(estimation: Estimation) {
        self.estimationSubject = CurrentValueRelay<Estimation>(estimation)
    }

    func update<T>(_ keyPath: WritableKeyPath<Estimation, T>, value: T) {
        var estimation = self.estimation
        estimation[keyPath: keyPath] = value
        estimationSubject.accept(estimation)
    }
}
