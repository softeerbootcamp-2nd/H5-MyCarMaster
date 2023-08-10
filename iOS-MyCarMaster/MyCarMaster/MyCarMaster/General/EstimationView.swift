//
//  EstimationView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

final class GeneralButton: UIButton {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: 0, height: 44)
    }

    let isHighlight: Bool

    init(isHighlight: Bool) {
        self.isHighlight = isHighlight
        super.init(frame: .zero)
        configureUI()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        style = .titleMedium2
        layer.borderWidth = 1.0
        layer.borderColor = UIColor.MCM.navyBlue5.cgColor

        if isHighlight { highlightStyled() }
        else { normalStyled() }
    }
}

// MARK: - API
extension GeneralButton {
    func highlightStyled() {
        backgroundColor = .MCM.navyBlue5
        setTitleColor(.MCM.white, for: .normal)
    }

    func normalStyled() {
        backgroundColor = .MCM.white
        setTitleColor(.MCM.navyBlue5, for: .normal)
    }
}

final class EstimationView: UIView {

    private let inset: CGFloat = 16

    let priceTitleLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium2
        label.setText("예상 가격")
        return label
    }()

    let priceLabel: UILabel = {
        let label = UILabel()
        label.style = .titleLarge1
        return label
    }()

    let backButton: GeneralButton = {
        let button = GeneralButton(isHighlight: false)
        button.setStyledTitle("이전", for: .normal)
        return button
    }()

    let nextButton: GeneralButton = {
        let button = GeneralButton(isHighlight: true)
        button.setStyledTitle("다음", for: .normal)
        button.highlightStyled()
        return button
    }()

    let summaryButton: UIButton = {
        let button = UIButton()
        button.style = .bodyMedium1
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.MCM.black.cgColor
        button.setStyledTitle("견적요약", for: .normal)
        button.setContentHuggingPriority(.defaultHigh, for: .vertical)
        return button
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
        backgroundColor = .systemBackground
        layer.shadowColor = UIColor.MCM.black.withAlphaComponent(0.08).cgColor
        layer.shadowOpacity = 1
        layer.shadowRadius = 10
        layer.shadowOffset = CGSize(width: 0, height: -4)
        clipsToBounds = true
    }

    private func configureLayout() {
        let contentView = UIView()

        addSubview(contentView)
        contentView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentView.topAnchor.constraint(equalTo: topAnchor, constant: inset),
            contentView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: inset),
            contentView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -inset),
            contentView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -inset),
        ])

        let estimationStackView: UIStackView = {
            let stackView = UIStackView()
            stackView.axis = .horizontal
            stackView.backgroundColor = .systemBrown
            stackView.distribution = .fillProportionally
            stackView.alignment = .center
            return stackView
        }()

        contentView.addSubview(estimationStackView)
        estimationStackView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            estimationStackView.topAnchor.constraint(equalTo: contentView.topAnchor),
            estimationStackView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            estimationStackView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
        ])

        let priceStackView: UIStackView = {
            let stackView = UIStackView()
            stackView.axis = .vertical
            stackView.spacing = 0
            stackView.backgroundColor = .systemPink
            return stackView
        }()

        estimationStackView.addArrangedSubview(priceStackView)

        priceStackView.addArrangedSubview(priceTitleLabel)
        priceStackView.addArrangedSubview(priceLabel)

        estimationStackView.addArrangedSubview(summaryButton)

        let controlButtonStackView: UIStackView = {
            let stackView = UIStackView()
            stackView.axis = .horizontal
            stackView.spacing = 12
            stackView.distribution = .fillEqually
            return stackView
        }()

        contentView.addSubview(controlButtonStackView)
        controlButtonStackView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            controlButtonStackView.topAnchor.constraint(equalTo: estimationStackView.bottomAnchor, constant: 20),
            controlButtonStackView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            controlButtonStackView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
        ])

        controlButtonStackView.addArrangedSubview(backButton)
        controlButtonStackView.addArrangedSubview(nextButton)
    }
}

// MARK: - API
extension EstimationView {
    func configure(with estimation: Int) {
        priceLabel.setText(estimation.formatted(style: .currency))
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct EstimationControlViewPreviews_Previews: PreviewProvider {
    static let view = EstimationView()

    static var previews: some View {
        UIViewPreview {
            view.configure(with: 93896000)
            return view
        }
    }
}
#endif
