//
//  OnboardingViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/04.
//

import UIKit
import MVIFoundation
import MCMNetwork

final class OnboardingViewController: UIViewController {

    let repository = GithubSearchRepository()

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
//        let viewController = ViewController()
//        viewController.modalPresentationStyle = .fullScreen
//        present(viewController, animated: false)

        repository.dumpImage { image in
            DispatchQueue.main.async {
                let imageView = UIImageView()
                imageView.image = image
                imageView.sizeToFit()
                imageView.center = self.view.center
                self.view.addSubview(imageView)
            }
        }
    }
}
