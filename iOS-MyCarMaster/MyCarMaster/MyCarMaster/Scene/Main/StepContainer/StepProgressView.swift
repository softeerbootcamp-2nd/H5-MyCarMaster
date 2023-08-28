//
//  StepProgressView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import UIKit

final class StepProgressView: UIView {

    private let progressLine: UIProgressView = {
        let progressView = UIProgressView(progressViewStyle: .bar)
        progressView.progressTintColor = .MCM.navyBlue5
        progressView.trackTintColor = .MCM.coolGrey1
        progressView.progress = 0.05
        return progressView
    }()

    private let progressIndicatorLabel: UILabel = {
        let label = UILabel()
        label.style = .titleSmall(nil)
        label.textColor = .MCM.navyBlue5
        label.setText("트림선택")
        return label
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
    }

    private func configureLayout() {
        addSubview(progressLine)
        progressLine.translatesAutoresizingMaskIntoConstraints = false

        addSubview(progressIndicatorLabel)
        progressIndicatorLabel.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            progressLine.topAnchor.constraint(equalTo: topAnchor, constant: 11),
            progressLine.leadingAnchor.constraint(equalTo: leadingAnchor),
            progressLine.trailingAnchor.constraint(equalTo: trailingAnchor),
            progressLine.heightAnchor.constraint(equalToConstant: 2),

            progressIndicatorLabel.centerXAnchor.constraint(equalTo: centerXAnchor),
            progressIndicatorLabel.topAnchor.constraint(equalTo: progressLine.bottomAnchor, constant: 7)
        ])
    }
}

extension StepProgressView {
    func updateProgress(with step: Step) {
        progressLine.progress = step.progress
        progressIndicatorLabel.text = step.title
    }
}
