//
//  OnboardingViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/04.
//

import UIKit
import MCMNetwork
import MVIFoundation

final class OnboardingViewController: UIViewController {

    private var onboardingView: OnboardingView {
        return view as? OnboardingView ?? OnboardingView()
    }

    override func loadView() {
        super.loadView()
        view = OnboardingView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        onboardingView.addTarget(keyPath: \.startButton, action: #selector(startButtonDidTap(_:)), for: .touchUpInside)
        onboardingView.playLottie()
    }

    @objc
    func startButtonDidTap(_ sender: UIButton) {
        // TODO: Routing 레이어에서 RootViewController 교체
        let viewController = DIContainer.resolveMainViewController()
        viewController.modalPresentationStyle = .fullScreen
        present(viewController, animated: false)
    }
}
