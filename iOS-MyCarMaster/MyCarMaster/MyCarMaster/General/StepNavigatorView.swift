//
//  StepNavigatorView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

final class ProgressView: UIView {

    let progressLine: UIProgressView = {
        let progressView = UIProgressView(progressViewStyle: .bar)
        progressView.progressTintColor = .MCM.navyBlue5
        progressView.trackTintColor = .MCM.coolGrey1
        progressView.progress = 0.05
        return progressView
    }()

    let progressIndicatorLabel: UILabel = {
        let label = UILabel()
        label.style = .titleSmall
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
        backgroundColor = .MCM.white
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

final class StepNavigatorView: UIView {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: 0, height: 104)
    }

    let backButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "chevron.backward")!, for: .normal)
        button.tintColor = .MCM.black
        return button
    }()

    let modelSelectionButton: UIButton = {
        let button = UIButton()
        button.style = .titleLarge2
        button.setStyledTitle("Palisade", for: .normal)
        button.setTitleColor(.MCM.black, for: .normal)
        return button
    }()

    let progressView = ProgressView()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        backgroundColor = .MCM.white
    }

    private func configureLayout() {
        let topContainer = UIView()

        addSubview(topContainer)
        topContainer.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            topContainer.topAnchor.constraint(equalTo: topAnchor),
            topContainer.leadingAnchor.constraint(equalTo: leadingAnchor),
            topContainer.trailingAnchor.constraint(equalTo: trailingAnchor),
            topContainer.heightAnchor.constraint(equalToConstant: 56),
        ])

        topContainer.addSubview(modelSelectionButton)
        modelSelectionButton.translatesAutoresizingMaskIntoConstraints = false

        topContainer.addSubview(backButton)
        backButton.translatesAutoresizingMaskIntoConstraints = false

        addSubview(progressView)
        progressView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            modelSelectionButton.centerXAnchor.constraint(equalTo: topContainer.centerXAnchor),
            modelSelectionButton.centerYAnchor.constraint(equalTo: topContainer.centerYAnchor),

            backButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
            backButton.centerYAnchor.constraint(equalTo: topContainer.centerYAnchor),

            progressView.leadingAnchor.constraint(equalTo: leadingAnchor),
            progressView.trailingAnchor.constraint(equalTo: trailingAnchor),
            progressView.bottomAnchor.constraint(equalTo: bottomAnchor),
            progressView.heightAnchor.constraint(equalToConstant: 48),
        ])
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct StepNavigatorViewPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UIViewPreview {
            return StepNavigatorView()
        }
    }
}
#endif
