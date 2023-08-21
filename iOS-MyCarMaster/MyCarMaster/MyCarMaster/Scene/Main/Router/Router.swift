//
//  Router.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

final class Router {

    typealias Estimation = Quotation

    private weak var window: UIWindow?
    private weak var navigationController: UINavigationController?
    private let estimationManager: EstimationManager

    init(window: UIWindow?, initialEstimation: Estimation) {
        self.window = window
        self.estimationManager = EstimationManager(estimation: initialEstimation)
    }

    func presentOnboarding() {
        let onboardingViewController = OnboardingViewController(router: self)
        window?.rootViewController = onboardingViewController
    }

    func presentModelSelection() {
        let rootViewControlller = ModelSelectionViewController(router: self)
        let navigationController = UINavigationController(rootViewController: rootViewControlller)
        window?.rootViewController = navigationController
        self.navigationController = navigationController
    }

    func pushStepContainer() {
        let stepContainer = resolveStepContainer()
        navigationController?.navigationBar.prefersLargeTitles = false
        navigationController?.navigationItem.largeTitleDisplayMode = .never
        navigationController?.navigationBar.topItem?.title = ""
        navigationController?.pushViewController(stepContainer, animated: false)
    }
}

extension Router {
    func resolveStepContainer() -> StepContainer {
        let stepRouter = StepRouter(entryStep: .trim)

        let stepBottomReactor = StepBottomReactor(
            stepRouter: stepRouter,
            estimationManager: estimationManager,
            initialState: .init(totalPrice: 0)
        )
        let stepBottomViewController = StepBottomViewController()
        stepBottomViewController.reactor = stepBottomReactor

        let stepContainer = StepContainer(stepBottomViewController: stepBottomViewController)
        let reactor = MainReactor(
            initialState: .init(
                isLoading: false,
                currentStep: .trim,
                currentStepViewController: stepRouter.resolveStepViewController(for: .trim)
            ),
            stepRouter: stepRouter
        )
        stepContainer.reactor = reactor
        return stepContainer
    }
}