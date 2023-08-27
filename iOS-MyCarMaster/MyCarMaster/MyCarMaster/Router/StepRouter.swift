//
//  StepRouter.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

import MCMNetwork

final class StepRouter {

    private let currentStepSubject: CurrentValueSubject<Step, Never>
    private var currentStep: Step {
        return currentStepSubject.value
    }

    lazy var currentStepPublisher = currentStepSubject
        .share()
        .eraseToAnyPublisher()

    lazy var currentStepViewController = currentStepPublisher
        .compactMap { [weak self] in
            self?.resolveStepViewController(for: $0)
        }
        .eraseToAnyPublisher()

    weak var estimationManager: EstimationManageable?
    weak var stepNetworkProvider: NetworkProvider<StepTarget>?

    init(
        entryStep: Step,
        estimationManager: EstimationManageable,
        stepNetworkProvider: NetworkProvider<StepTarget>
    ) {
        self.currentStepSubject = CurrentValueSubject(entryStep)
        self.estimationManager = estimationManager
        self.stepNetworkProvider = stepNetworkProvider
    }

    func nextStep() {
        guard let nextStep = currentStep.next else {
            fatalError("프로그래밍 오류: 다음 스텝이 존재하지 않습니다.")
        }
        currentStepSubject.send(nextStep)
    }

    func backStep() {
        guard let backStep = currentStep.back else {
            fatalError("프로그래밍 오류: 이전 스텝이 존재하지 않습니다.")
        }
        currentStepSubject.send(backStep)
    }

    func presentEstimation() {
    }
}

extension StepRouter {
    func resolveStepViewController(for step: Step) -> UIViewController {
        switch step {
        case .trim:
            let trimReactor = TrimReactor(
                initialState: .init(isLoading: false, trimList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let trimViewController = TrimViewController()
            trimViewController.reactor = trimReactor
            return trimViewController
        case .engine:
            let engineReactor = EngineReactor(
                initialState: .init(isLoading: false, engineList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let engineViewController = EngineViewController()
            engineViewController.reactor = engineReactor
            return engineViewController
        case .wheelDrive:
            let wheelDriveReactor = WheelDriveReactor(
                initialState: .init(isLoading: false, wheelDriveList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let wheelDriveViewController = WheelDriveViewController()
            wheelDriveViewController.reactor = wheelDriveReactor
            return wheelDriveViewController
        case .bodyType:
            let bodyTypeReactor = BodyTypeReactor(
                initialState: .init(isLoading: false, bodyTypeList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let bodyTypeViewController = BodyTypeViewController()
            bodyTypeViewController.reactor = bodyTypeReactor
            return bodyTypeViewController
        case .exterior:
            let exteriorReactor = ExteriorReactor(
                initialState: .init(isLoading: false, exteriorList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let exteriorViewController = ExteriorViewController()
            exteriorViewController.reactor = exteriorReactor
            return exteriorViewController
        case .interior:
            let interiorReactor = InteriorReactor(
                initialState: .init(isLoading: false, interiorList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let interiorViewController = InteriorViewController()
            interiorViewController.reactor = interiorReactor
            return interiorViewController
        case .option:
            let optionReactor = OptionReactor(
                initialState: .init(isLoading: false, optionList: [], selectedOptions: [], consideredOptions: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let optionViewController = OptionViewController()
            optionViewController.reactor = optionReactor
            return optionViewController
        case .quotation:
            return QuotationViewController()
        }
    }
}
