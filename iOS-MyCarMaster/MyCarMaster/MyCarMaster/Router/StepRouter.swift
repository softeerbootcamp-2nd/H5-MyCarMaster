//
//  StepRouter.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

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

    init(entryStep: Step, estimationManager: EstimationManageable) {
        self.currentStepSubject = CurrentValueSubject(entryStep)
        self.estimationManager = estimationManager
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
                estimationManager: estimationManager
            )
            let trimViewController = TrimViewController()
            trimViewController.reactor = trimReactor
            return trimViewController
        case .engine:
            return EngineViewController()
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
