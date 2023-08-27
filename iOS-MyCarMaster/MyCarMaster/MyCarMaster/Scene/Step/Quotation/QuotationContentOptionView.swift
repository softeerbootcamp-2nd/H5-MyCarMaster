//
//  QuotationContentOptionView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

final class QuotationContentOptionView: UIView {

    // MARK: View
    private let contentStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.alignment = .fill
        stackView.spacing = 18
    }

    private let titleStackView = UIStackView().then { stackView in
        stackView.axis = .horizontal
        stackView.alignment = .center
    }

    private let titleDecriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.coolGrey2
        label.setText("옵션")
        label.sizeToFit()
    }

    private let modifyButton = UIButton().then { button in
        button.style = .bodyMedium2(nil)
        button.setTitleColor(.MCM.coolGrey2, for: .normal)
        button.setStyledTitle("변경하기 >", for: .normal)
    }

    private let selectedOptionDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.setText("추가 옵션")
        label.textColor = .MCM.black
    }

    private let selectedOptionStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 12
        stackView.alignment = .fill
        stackView.distribution = .fill
    }

    private let consideredOptionDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.setText("고민 옵션")
        label.textColor = .MCM.black
    }

    private let consideredOptionStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 12
        stackView.alignment = .fill
        stackView.distribution = .fill
    }

    private let totalPriceStackView = UIStackView().then { stackView in
        stackView.axis = .horizontal
        stackView.distribution = .fill
    }

    private let totalPriceDescriptionLabel = UILabel().then { label in
        label.style = .titleLarge2(nil)
        label.setText("추가 옵션 가격")
        label.textColor = .MCM.black
    }

    private let totalPriceLabel = UILabel().then { label in
        label.style = .titleLarge2(nil)
        label.textColor = .MCM.black
        label.setContentHuggingPriority(.defaultHigh, for: .horizontal)
    }

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
        addSubview(contentStackView)
        contentStackView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentStackView.topAnchor.constraint(equalTo: topAnchor),
            contentStackView.leadingAnchor.constraint(equalTo: leadingAnchor),
            contentStackView.trailingAnchor.constraint(equalTo: trailingAnchor),
            contentStackView.bottomAnchor.constraint(equalTo: bottomAnchor),
        ])

        configureContantStackViewLayout()
    }

    private func configureContantStackViewLayout() {
        [
            titleStackView,
            selectedOptionDescriptionLabel, selectedOptionStackView,
            consideredOptionDescriptionLabel, consideredOptionStackView,
            totalPriceStackView
        ].forEach { subview in
            contentStackView.addArrangedSubview(subview)
        }

        [titleDecriptionLabel, modifyButton].forEach { subview in
            titleStackView.addArrangedSubview(subview)
        }

        [totalPriceDescriptionLabel, totalPriceLabel].forEach { subview in
            totalPriceStackView.addArrangedSubview(subview)
        }
    }
}

extension QuotationContentOptionView {
    func configure(with stateConvertible: QuotationContentOptionStateConvertible) {
        let state = stateConvertible.quotationContentOptionState

        selectedOptionStackView.removeAllArrangedSubviews()
        state.selectedOptions.forEach { option in
            let optionItemView = QuotationContentOptionItemView()
            optionItemView.configure(with: option)
            optionItemView.considerButtonStyle()
            selectedOptionStackView.addArrangedSubview(optionItemView)
        }

        consideredOptionStackView.removeAllArrangedSubviews()
        state.consideredOptions.forEach { option in
            let optionItemView = QuotationContentOptionItemView()
            optionItemView.configure(with: option)
            optionItemView.selectButtonStyle()
            consideredOptionStackView.addArrangedSubview(optionItemView)
        }

        totalPriceLabel.setText("+\(state.selectedOptionsTotalPrice.formatted(style: .currency))")
    }
}
