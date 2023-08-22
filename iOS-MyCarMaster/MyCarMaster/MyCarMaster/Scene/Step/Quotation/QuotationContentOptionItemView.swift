//
//  QuotationContentOptionItemView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

final class QuotationContentOptionItemView: UIView {

    // MARK: View
    private let titleLabel = UILabel().then { label in
        label.style = .bodyLarge1(nil)
        label.textColor = .MCM.black
        label.setContentCompressionResistancePriority(.defaultLow, for: .horizontal)
        label.numberOfLines = 0
    }

    private let actionButton = PaddingButton(vertical: 2, horizontal: 6).then { button in
        button.style = .buttonTitleSmall(nil)
        button.setTitleColor(.MCM.white, for: .normal)
    }

    private let priceLabel = UILabel().then { label in
        label.style = .titleMedium2(nil)
        label.textColor = .MCM.black
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
    }

    private func configureLayout() {
        [titleLabel, actionButton, priceLabel].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor),
            bottomAnchor.constraint(equalTo: titleLabel.bottomAnchor),

            actionButton.centerYAnchor.constraint(equalTo: centerYAnchor),
            actionButton.leadingAnchor.constraint(equalTo: titleLabel.trailingAnchor, constant: 12),

            priceLabel.centerYAnchor.constraint(equalTo: centerYAnchor),
            priceLabel.leadingAnchor.constraint(greaterThanOrEqualTo: actionButton.trailingAnchor, constant: 64),
            priceLabel.trailingAnchor.constraint(equalTo: trailingAnchor),
        ])
    }
}

// MARK: - API
extension QuotationContentOptionItemView {
    func configure(with stateConvertible: QuotationContentOptionItemStateConvertible) {
        let state = stateConvertible.quotationContentOptionItemState

        titleLabel.setText(state.title)
        priceLabel.setText("+\(state.price.formatted(style: .currency))")
        considerButtonStyle()
    }

    func selectButtonStyle() {
        actionButton.configureUI(titleColor: .MCM.white, backgroundColor: .MCM.navyBlue5)
        actionButton.setStyledTitle("확정하기", for: .normal)
    }

    func considerButtonStyle() {
        actionButton.configureUI(titleColor: .MCM.white, backgroundColor: .MCM.gold3)
        actionButton.setStyledTitle("제외하기", for: .normal)
    }
}
