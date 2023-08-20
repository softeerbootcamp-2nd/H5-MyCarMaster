//
//  MainViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

import MVIFoundation

final class MainViewController: UIViewController {

    var cancellables = Set<AnyCancellable>()
    
    let stepNavigatorView = StepNavigatorView()
    var stepViewController: UIViewController?
    let estimationView = EstimationView()

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
