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
                initialState: .init(engineList: []),
                estimationManager: estimationManager,
                stepNetworkProvider: stepNetworkProvider
            )
            let engineViewController = EngineViewController()
            engineViewController.reactor = engineReactor
            return engineViewController
        case .wheelDrive:
            return WheelDriveViewController()
        case .bodyType:
            return BodyTypeViewController()
        case .exterior:
            return ExteriorViewController()
        case .interior:
            return InteriorViewController()
        case .option:
            return OptionViewController()
        case .quotation:
            return QuotationViewController()
        }
    }
}
