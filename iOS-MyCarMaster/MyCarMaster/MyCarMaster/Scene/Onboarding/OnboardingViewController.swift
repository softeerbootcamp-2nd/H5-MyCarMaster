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

    private weak var router: Router?

    private var onboardingView: OnboardingView {
        return view as? OnboardingView ?? OnboardingView()
    }

    init(router: Router) {
        self.router = router
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
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
        router?.showModelSelection()
    }
}
