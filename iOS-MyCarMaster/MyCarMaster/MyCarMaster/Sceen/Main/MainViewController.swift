//
//  MainViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

enum Step: Int, CaseIterable {
    case trim = 1
    case engine
    case wheelDrive
    case bodyType
    case exterior
    case interior
    case option
    case quotation

    var title: String {
        switch self {
        case .trim:
            return "트림 선택"
        case .engine:
            return "엔진 종류"
        case .wheelDrive:
            return "구동 방식"
        case .bodyType:
            return "바디 타입"
        case .exterior:
            return "외장 색상"
        case .interior:
            return "내장 색상"
        case .option:
            return "추가 옵션"
        case .quotation:
            return "견적서 완성"
        }
    }

    var next: Step {
        switch self {
        case .trim:
            return .engine
        case .engine:
            return .wheelDrive
        case .wheelDrive:
            return .bodyType
        case .bodyType:
            return .exterior
        case .exterior:
            return .interior
        case .interior:
            return .option
        case .option:
            return .quotation
        case.quotation:
            return .trim
        }
    }

    var back: Step {
        switch self {
        case .trim:
            return .quotation
        case .engine:
            return .trim
        case .wheelDrive:
            return .engine
        case .bodyType:
            return .wheelDrive
        case .exterior:
            return .bodyType
        case .interior:
            return .exterior
        case .option:
            return .interior
        case .quotation:
            return .option
        }
    }

    var progress: Float {
        return Float(self.rawValue) / Float(Self.allCases.count)
    }
}

final class MainViewController: UIViewController {

    private var currentStep: Step?

    let stepNavigatorView = StepNavigatorView()
    var stepViewController: UIViewController?
    let estimationView = EstimationView()

    init(entryStep: Step) {
        super.init(nibName: nil, bundle: nil)
        moveTo(entryStep)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(stepNavigatorView)
        stepNavigatorView.translatesAutoresizingMaskIntoConstraints = false
        stepNavigatorView.layer.zPosition = 1

        view.addSubview(estimationView)
        estimationView.translatesAutoresizingMaskIntoConstraints = false
        estimationView.layer.zPosition = 1

        setCoordiante()
    }

    private func configureLayout() {
        guard let stepViewController else { return }

        NSLayoutConstraint.activate([
            stepNavigatorView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            stepNavigatorView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stepNavigatorView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            stepNavigatorView.heightAnchor.constraint(equalToConstant: 104),

            estimationView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            estimationView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
            estimationView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            estimationView.heightAnchor.constraint(equalToConstant: 143),

            stepViewController.view.topAnchor.constraint(equalTo: stepNavigatorView.bottomAnchor),
            stepViewController.view.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stepViewController.view.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            stepViewController.view.bottomAnchor.constraint(equalTo: estimationView.topAnchor),
        ])
    }

    private func setCoordiante() {
        estimationView.nextButton.addTarget(self, action: #selector(moveNext), for: .touchUpInside)
        estimationView.backButton.addTarget(self, action: #selector(moveBack), for: .touchUpInside)
    }

    @objc
    private func moveNext() {
        moveTo(currentStep?.next ?? .trim)
    }

    @objc
    private func moveBack() {
        moveTo(currentStep?.back ?? .trim)
    }

    private func moveTo(_ step: Step) {
        currentStep = step
        stepNavigatorView.progressView.progressLine.progress = step.progress
        stepNavigatorView.progressView.progressIndicatorLabel.setText(step.title)
        switch step {
        case .trim:
            moveTo(TrimViewController())
        case .engine:
            moveTo(EngineViewController())
        case .wheelDrive:
            moveTo(WheelDriveViewController())
        case .bodyType:
            moveTo(BodyTypeViewController())
        case .exterior:
            moveTo(ExteriorViewController())
        case .interior:
            moveTo(InteriorViewController())
        case .option:
            moveTo(OptionViewController())
        case .quotation:
            moveTo(QuotationViewController())
        }
    }

    private func changeStepViewControllerTo(_ stepViewController: UIViewController) {
        self.stepViewController?.willMove(toParent: nil)
        self.stepViewController?.view.removeFromSuperview()
        self.stepViewController?.removeFromParent()

        self.stepViewController = stepViewController

        addChild(stepViewController)
        view.addSubview(stepViewController.view)
        stepViewController.view.translatesAutoresizingMaskIntoConstraints = false
        stepViewController.view.layer.zPosition = 0
        configureLayout()

        stepViewController.didMove(toParent: self)
    }
}

// MARK: - API
extension MainViewController {
    func moveTo(_ stepViewController: UIViewController) {
        changeStepViewControllerTo(stepViewController)
    }
}
