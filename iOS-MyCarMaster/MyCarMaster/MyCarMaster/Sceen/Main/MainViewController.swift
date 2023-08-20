//
//  MainViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import Combine
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
        reactor?.action.send(.moveNext)
    }

    @objc
    private func moveBack() {
        reactor?.action.send(.moveBack)
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

extension MainViewController: Reactable {
    func bindState(reactor: MainReactor) {
        reactor.state.map(\.currentStepViewController)
            .sink { [weak self] currentViewController in
                self?.changeStepViewControllerTo(currentViewController)
            }
            .store(in: &cancellables)
        reactor.state.map(\.isLoading)
            .sink { isLoading in
                print("isLoading: \(isLoading)")
            }
            .store(in: &cancellables)
    }
}

// MARK: - API
extension MainViewController {
    func moveTo(_ stepViewController: UIViewController) {
        changeStepViewControllerTo(stepViewController)
    }
}
