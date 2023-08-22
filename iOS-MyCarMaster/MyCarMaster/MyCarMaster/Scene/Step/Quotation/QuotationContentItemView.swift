//
//  QuotationContentItemView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

final class QuotationContentItemView: UIView {

    // MARK: View
    private let titleDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium1(nil)
        label.textColor = .MCM.coolGrey2
    }

    private let modifyButton = UIButton().then { button in
        button.style = .bodyMedium2(nil)
        button.setTitleColor(.MCM.coolGrey2, for: .normal)
        button.setStyledTitle("변경하기 >", for: .normal)
    }

    private let titleStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 3
        stackView.alignment = .leading
    }

    private let titleImageView = UIImageView().then { imageView in
        imageView.contentMode = .scaleToFill
        imageView.backgroundColor = .systemBlue
    }

    private let titleLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.black
    }

    private let priceLabel = UILabel().then { label in
        label.style = .bodyLarge1(nil)
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
        backgroundColor = .MCM.white
    }

    private func configureLayout() {
        [titleDescriptionLabel, modifyButton, titleStackView, priceLabel].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleDescriptionLabel.topAnchor.constraint(equalTo: topAnchor),
            titleDescriptionLabel.leadingAnchor.constraint(equalTo: leadingAnchor),

            modifyButton.topAnchor.constraint(equalTo: topAnchor),
            modifyButton.trailingAnchor.constraint(equalTo: trailingAnchor),

            titleStackView.topAnchor.constraint(equalTo: titleDescriptionLabel.bottomAnchor, constant: 18),
            titleStackView.leadingAnchor.constraint(equalTo: leadingAnchor),
            bottomAnchor.constraint(equalTo: titleStackView.bottomAnchor),

            priceLabel.trailingAnchor.constraint(equalTo: trailingAnchor),
            priceLabel.bottomAnchor.constraint(equalTo: bottomAnchor),
        ])

        [titleImageView, titleLabel].forEach { subview in
            titleStackView.addArrangedSubview(subview)
        }

        titleImageView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            titleImageView.widthAnchor.constraint(equalToConstant: 120),
            titleImageView.heightAnchor.constraint(equalToConstant: 32),
            titleImageView.leadingAnchor.constraint(equalTo: titleStackView.leadingAnchor),
        ])
    }
}

// MARK: - API
extension QuotationContentItemView {
    func configure(with stateConvertible: QuotationContentItemStateConvertible) {
        let state = stateConvertible.quotationContentItemState

        titleDescriptionLabel.setText(state.titleDescription)

        if let imageURL = state.imageURL,
           let imageData = try? Data(contentsOf: imageURL) {
            titleImageView.image = UIImage(data: imageData)
            titleImageView.isHidden = false
            titleLabel.style = .bodyMedium2(nil)
        } else {
            titleImageView.isHidden = true
            titleLabel.style = .bodyLarge1(nil)
        }

        titleLabel.setText(state.title)

        let priceLabelText = (state.isAdditionalPrice ? "+" : "") + state.price.formatted(style: .currency)
        priceLabel.setText(priceLabelText)
    }
}
