//
//  EstimationManager.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import Combine

import MCMCombineExtension

typealias Estimation = Quotation

protocol EstimationManageable: AnyObject {
    var estimation: Estimation { get }
    var estimationPublisher: AnyPublisher<Estimation, Never> { get }

    func update<T>(_ keyPath: WritableKeyPath<Estimation, T?>, value: T)
    func update<T: Priceable>(_ keyPath: WritableKeyPath<Estimation, T?>, value: T)
    func update(_ keyPath: WritableKeyPath<Estimation, [Option]>, value: Option)
}

final class EstimationManager: EstimationManageable {

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

    func update<T>(_ keyPath: WritableKeyPath<Estimation, T?>, value: T) {
        var estimation = self.estimation
        estimation[keyPath: keyPath] = value
        estimationSubject.accept(estimation)
    }

    func update<T: Priceable>(_ keyPath: WritableKeyPath<Estimation, T?>, value: T) {
        var estimation = self.estimation
        estimation[keyPath: keyPath] = value
        estimation.totalPrice = calculateTotalPrice(estimation)
        estimationSubject.accept(estimation)
    }

    func update(_ keyPath: WritableKeyPath<Estimation, [Option]>, value: Option) {
        var estimation = self.estimation
        if keyPath == \.selectedOptions {
            estimation.selectedOptions?.insert(value)
            estimation.consideredOptions?.remove(value)
        } else {
            estimation.consideredOptions?.insert(value)
            estimation.selectedOptions?.remove(value)
        }

        estimation.selectedOptionsTotalPrice = calculateSelectOptionsTotalPrice(estimation)
        estimation.totalPrice = calculateTotalPrice(estimation)

        estimationSubject.accept(estimation)
    }

    private func calculateTotalPrice(_ estimation: Estimation) -> Int {
        var totalPrice = 0
        totalPrice += estimation.trim?.price ?? 0
        totalPrice += estimation.engine?.price ?? 0
        totalPrice += estimation.bodyType?.price ?? 0
        totalPrice += estimation.wheelDrive?.price ?? 0
        totalPrice += estimation.exterior?.price ?? 0
        totalPrice += estimation.interior?.price ?? 0
        totalPrice += calculateSelectOptionsTotalPrice(estimation)
        return totalPrice
    }

    private func calculateSelectOptionsTotalPrice(_ estimation: Estimation) -> Int {
        return estimation.selectedOptions?.map(\.price).reduce(0, +) ?? 0
    }
}
