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

    func checkTrimCanChange() -> Bool

    func update<T>(_ keyPath: WritableKeyPath<Estimation, T?>, value: T)
    func update<T: Priceable>(_ keyPath: WritableKeyPath<Estimation, T?>, value: T)
    func update(_ keyPath: WritableKeyPath<Estimation, Set<Option>>, value: Option)
    func remove(value: Option)
    func removeAll()
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
}

extension EstimationManager {
    func checkTrimCanChange() -> Bool {
        return estimation.engine == nil &&
        estimation.bodyType == nil &&
        estimation.wheelDrive == nil &&
        estimation.bodyType == nil &&
        estimation.exterior == nil &&
        estimation.interior == nil &&
        estimation.selectedOptions.isEmpty &&
        estimation.consideredOptions.isEmpty
    }
}

extension EstimationManager {
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

    func update(_ keyPath: WritableKeyPath<Estimation, Set<Option>>, value: Option) {
        var estimation = self.estimation
        if keyPath == \.selectedOptions {
            estimation.selectedOptions.insert(value)
            estimation.consideredOptions.remove(value)
        } else {
            estimation.consideredOptions.insert(value)
            estimation.selectedOptions.remove(value)
        }

        estimation.selectedOptionsTotalPrice = calculateSelectOptionsTotalPrice(estimation)
        estimation.totalPrice = calculateTotalPrice(estimation)

        estimationSubject.accept(estimation)
    }

    func remove(value: Option) {
        var estimation = self.estimation

        if estimation.selectedOptions.contains(value) {
            estimation.selectedOptions.remove(value)
            estimation.selectedOptionsTotalPrice = calculateSelectOptionsTotalPrice(estimation)
            estimation.totalPrice = calculateTotalPrice(estimation)
        } else if estimation.consideredOptions.contains(value) {
            estimation.consideredOptions.remove(value)
        }

        estimationSubject.accept(estimation)
    }

    func removeAll() {
        var newEstimation = self.estimation
        newEstimation.engine = nil
        newEstimation.wheelDrive = nil
        newEstimation.bodyType = nil
        newEstimation.exterior = nil
        newEstimation.interior = nil
        newEstimation.selectedOptions.removeAll()
        newEstimation.consideredOptions.removeAll()
        estimationSubject.accept(newEstimation)
    }
}

extension EstimationManager {
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
        return estimation.selectedOptions.map(\.price).reduce(0, +) ?? 0
    }
}
