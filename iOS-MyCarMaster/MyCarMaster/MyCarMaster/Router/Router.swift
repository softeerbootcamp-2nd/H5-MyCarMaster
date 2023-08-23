//
//  Router.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import Combine
import UIKit

import MCMNetwork

final class Router {

    typealias Estimation = Quotation

    private weak var window: UIWindow?
    private weak var navigationController: UINavigationController?
    private let estimationManager: EstimationManager
    private let stepRouter: StepRouter
    private let stepNetworkProvider: NetworkProvider<StepTarget>

    init(window: UIWindow?, initialEstimation: Estimation) {
        self.window = window
        self.estimationManager = EstimationManager(estimation: initialEstimation)
        self.stepNetworkProvider = NetworkProvider<StepTarget>()
        self.stepRouter = StepRouter(
            entryStep: .trim,
            estimationManager: estimationManager,
            stepNetworkProvider: stepNetworkProvider
        )
    }

    func start() {
        showOnboarding()
    }

    func showOnboarding() {
        let onboardingViewController = OnboardingViewController(router: self)
        window?.rootViewController = onboardingViewController
    }

    func showModelSelection() {
        let rootViewControlller = ModelSelectionViewController(router: self, estimaitonManager: estimationManager)
        let navigationController = UINavigationController(rootViewController: rootViewControlller)
        window?.rootViewController = navigationController
        self.navigationController = navigationController
    }

    func showStepContainer() {
        let stepContainer = resolveStepContainer()
        navigationController?.navigationBar.topItem?.title = ""
        navigationController?.pushViewController(stepContainer, animated: false)
    }
}

extension Router {
    func resolveStepContainer() -> StepContainer {
        let stepBottomReactor = StepBottomReactor(
            stepRouter: stepRouter,
            estimationManager: estimationManager,
            initialState: .init(totalPrice: 0)
        )
        let stepBottomViewController = StepBottomViewController()
        stepBottomViewController.reactor = stepBottomReactor

        let stepContainer = StepContainer(stepBottomViewController: stepBottomViewController)

#if DEBUG
        let reactor = StepContainerReactor(
            initialState: .init(
                isLoading: false,
                currentStep: .trim,
                currentStepViewController: stepRouter.resolveStepViewController(for: .trim)
            ),
            stepRouter: stepRouter,
            estimationManager: estimationManager
        )
#else
        let reactor = StepContainerReactor(
            initialState: .init(
                isLoading: false,
                currentStep: .trim,
                currentStepViewController: stepRouter.resolveStepViewController(for: .trim)
            ),
            stepRouter: stepRouter
        )
#endif
        stepContainer.reactor = reactor
        return stepContainer
    }
}