//
//  StepBottomView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import Combine
import UIKit

import MVIFoundation

final class StepBottomView: UIView {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: UIScreen.main.bounds.width, height: 143)
    }

    private let inset: CGFloat = 16

    private let topContainer: UIView = {
        let view = UIView()
        view.backgroundColor = .MCM.white
        return view
    }()

    private let bottomContainer: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 12
        stackView.distribution = .fillEqually
        return stackView
    }()

    private let priceTitleLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.black
        label.setText("예상 가격")
        return label
    }()

    private let priceLabel: UILabel = {
        let label = UILabel()
        label.style = .titleLarge1(nil)
        label.textColor = .MCM.black
        label.setText("93,896,000원")
        return label
    }()

    let leftButton = ThemeButton().then { button in
        button.secondaryStyle()
    }

    let rightButton = ThemeButton().then { button in
        button.selectedStyle()
    }

    let summaryButton: UIButton = {
        let button = UIButton()
        button.style = .bodyMedium1(nil)
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.MCM.black.cgColor
        button.setTitleColor(.MCM.black, for: .normal)
        button.setStyledTitle("견적요약", for: .normal)
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
        backgroundColor = .MCM.white
        addShadow(
            color: .MCM.black.withAlphaComponent(0.08),
            radius: 4,
            offset: CGSize(width: 0, height: -4)
        )
    }

    private func configureLayout() {
        [topContainer, bottomContainer].forEach { container in
            addSubview(container)
            container.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            topContainer.topAnchor.constraint(equalTo: topAnchor, constant: inset),
            topContainer.leadingAnchor.constraint(equalTo: leadingAnchor, constant: inset),
            topContainer.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -inset),
            topContainer.heightAnchor.constraint(equalToConstant: 48),

            bottomContainer.leadingAnchor.constraint(equalTo: leadingAnchor, constant: inset),
            bottomContainer.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -inset),
            bottomContainer.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -inset),
            bottomContainer.heightAnchor.constraint(equalToConstant: 44),
        ])

        configureTopContainer()
        configureBottomContainer()
    }

    private func configureTopContainer() {
        let priceStackView: UIStackView = {
            let stackView = UIStackView()
            stackView.axis = .vertical
            stackView.spacing = 0
            return stackView
        }()

        priceStackView.addArrangedSubview(priceTitleLabel)
        priceStackView.addArrangedSubview(priceLabel)

        [priceStackView, summaryButton].forEach { subview in
            topContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            priceStackView.topAnchor.constraint(equalTo: topContainer.topAnchor),
            priceStackView.leadingAnchor.constraint(equalTo: topContainer.leadingAnchor, constant: 12),
            priceStackView.bottomAnchor.constraint(equalTo: topContainer.bottomAnchor),
            priceStackView.widthAnchor.constraint(equalToConstant: 136),

            summaryButton.topAnchor.constraint(equalTo: topContainer.topAnchor, constant: 6),
            summaryButton.trailingAnchor.constraint(equalTo: topContainer.trailingAnchor, constant: -12),
            summaryButton.bottomAnchor.constraint(equalTo: topContainer.bottomAnchor, constant: -6),
            summaryButton.widthAnchor.constraint(equalToConstant: 75),
        ])
    }

    private func configureBottomContainer() {
        bottomContainer.addArrangedSubview(leftButton)
        bottomContainer.addArrangedSubview(rightButton)
    }
}

// MARK: - API
extension StepBottomView {
    func updateLeftButtonTitle(_ title: String?) {
        guard let title else {
            leftButton.isHidden = true
            return
        }
        leftButton.isHidden = false
        leftButton.setStyledTitle(title, for: .normal)
    }

    func updateRightButtonTitle(_ title: String?) {
        guard let title else {
            rightButton.isHidden = true
            return
        }
        rightButton.isHidden = false
        rightButton.setStyledTitle(title, for: .normal)
    }

    func updateTotalPrice(_ totalPrice: Int) {
        priceLabel.setText(totalPrice.formatted(style: .currency))
    }

    func addTarget<T: UIControl>(
        _ target: Any?,
        _ keyPath: KeyPath<StepBottomView, T>,
        action: Selector,
        for event: UIControl.Event
    ) {
        self[keyPath: keyPath].addTarget(target, action: action, for: event)
    }

    func configure(with estimationPrice: Int) {
        priceLabel.setText(estimationPrice.formatted(style: .currency))
    }
}
