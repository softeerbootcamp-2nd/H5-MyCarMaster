//
//  MainViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

enum Step {
    case trim
    case engine
    case wheelDrive
    case bodyType
    case exterior

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
            return .trim
        }
    }

    var back: Step {
        switch self {
        case .trim:
            return .exterior
        case .engine:
            return .trim
        case .wheelDrive:
            return .engine
        case .bodyType:
            return .wheelDrive
        case .exterior:
            return .bodyType
        }
    }

    var progress: Float {
        switch self {
        case .trim:
            return 0.01
        case .engine:
            return 0.25
        case .wheelDrive:
            return 0.5
        case .bodyType:
            return 0.5
        case .exterior:
            return 0.5
        }
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

#if canImport(SwiftUI)
import SwiftUI

struct MainViewController_Previews: PreviewProvider {
    static let vc = MainViewController(entryStep: .trim)

    static var previews: some View {

        UIViewControllerPreview {
            vc.estimationView.configure(with: 93896000)
            return vc
        }
    }
}
#endif
