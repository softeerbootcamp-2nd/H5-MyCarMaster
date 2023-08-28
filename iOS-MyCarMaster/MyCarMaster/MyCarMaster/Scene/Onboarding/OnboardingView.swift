//
//  OnboardingView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/03.
//

import UIKit

import Lottie
import MCMResource

final class OnboardingView: UIView {

    private let lottieView: LottieAnimationView = {
        let view = LottieAnimationView(filePath: Resource.Lottie.launchScreenPath)
        view.contentMode = .scaleAspectFill
        return view
    }()

    private let gradientLayer: CAGradientLayer = {
        let layer = CAGradientLayer()
        layer.colors = [
            UIColor.black.withAlphaComponent(0.8).cgColor,
            UIColor.black.withAlphaComponent(0.0).cgColor
        ]
        return layer
    }()

    private let titleLabel = UILabel().then { label in
        label.style = .headlineLarge(nil)
        label.setText("내게 맞는 견적부터\n카마스터 연결까지")
        label.textColor = .MCM.white
        label.numberOfLines = 0
    }

    private let subTitleLabel = UILabel().then { label in
        label.style = .bodyLarge2(nil)
        label.setText("마이 카마스터와 함께해요")
        label.textColor = .MCM.white
    }

    private(set) var startButton = UIButton().then { button in
        button.backgroundColor = .MCM.white
        button.style = .bodyMedium1(nil)
        button.setStyledTitle("마이 카마스터 시작하기", for: .normal)
        button.setTitleColor(.MCM.black, for: .normal)
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
    }

    private func configureLayout() {
        addSubview(lottieView)
        lottieView.translatesAutoresizingMaskIntoConstraints = false

        lottieView.layer.addSublayer(gradientLayer)

        addSubview(titleLabel)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        addSubview(subTitleLabel)
        subTitleLabel.translatesAutoresizingMaskIntoConstraints = false
        addSubview(startButton)
        startButton.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            lottieView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            lottieView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            lottieView.topAnchor.constraint(equalTo: self.topAnchor),
            lottieView.bottomAnchor.constraint(equalTo: self.bottomAnchor),

            titleLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 24),
            titleLabel.topAnchor.constraint(equalTo: self.safeAreaLayoutGuide.topAnchor, constant: 92),

            subTitleLabel.leadingAnchor.constraint(equalTo: titleLabel.leadingAnchor),
            subTitleLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 8),

            startButton.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            startButton.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -16),
            startButton.bottomAnchor.constraint(equalTo: self.safeAreaLayoutGuide.bottomAnchor, constant: -16),
            startButton.heightAnchor.constraint(equalToConstant: 44),
        ])
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        gradientLayer.frame.size = CGSize(width: bounds.width, height: bounds.height * 0.4)
    }
}

// MARK: - API
extension OnboardingView {
    func addTarget<T: UIControl>(keyPath: KeyPath<OnboardingView, T>, action: Selector, for controlEvents: T.Event) {
        self[keyPath: keyPath].addTarget(nil, action: action, for: controlEvents)
    }

    func playLottie(_ completion: ((_ isComplete: Bool) -> Void)? = nil) {
        lottieView.play(completion: completion)
    }
}
