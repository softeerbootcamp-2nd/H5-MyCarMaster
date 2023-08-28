//
//  QuotationView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/18.
//

import UIKit

final class QuotationView: UIView {

    // MARK: Property
    private let inset: CGFloat = 26

    // MARK: View
    private let scrollView = UIScrollView().then { scrollView in
        scrollView.backgroundColor = .MCM.white
    }

    private let contentView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 20
    }

    private let mainContentView = UIView()

    private let titleLabel = UILabel().then { label in
        label.style = .titleLarge1(.init(alignment: .center))
        label.textColor = .MCM.black
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
    }

    private let subtitleLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.grey3
        label.setText("카마스터 찾기를 통해 구매 상담을 할 수 있어요")
    }

    private let previewImageView = SpriteRotationView()

    private let priceDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.black
        label.setText("예상 가격")
    }

    private let priceLabel = UILabel().then { label in
        label.style = .titleLarge1(.init(alignment: .center))
        label.textColor = .MCM.black
    }

    private let quotationContentView = QuotationContentView()

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
        addSubview(scrollView)
        scrollView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: bottomAnchor),
        ])

        scrollView.addSubview(contentView)
        contentView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentView.topAnchor.constraint(equalTo: scrollView.topAnchor),
            contentView.widthAnchor.constraint(equalTo: scrollView.widthAnchor, constant: -2 * inset),
            contentView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor, constant: inset),
            contentView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor, constant: -inset),

            scrollView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
        ])

        configureContentViewLayout()
    }

    private func configureContentViewLayout() {
        [mainContentView, quotationContentView].forEach { subview in
            contentView.addArrangedSubview(subview)
        }

        configureMainContentViewLayout()
    }

    private func configureMainContentViewLayout() {
        [titleLabel, subtitleLabel, previewImageView, priceDescriptionLabel, priceLabel].forEach { subview in
            mainContentView.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: mainContentView.topAnchor, constant: 18),
            titleLabel.centerXAnchor.constraint(equalTo: mainContentView.centerXAnchor),
            titleLabel.widthAnchor.constraint(equalToConstant: 190),

            subtitleLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 8),
            subtitleLabel.centerXAnchor.constraint(equalTo: mainContentView.centerXAnchor),

            previewImageView.topAnchor.constraint(equalTo: subtitleLabel.bottomAnchor, constant: 12),
            previewImageView.leadingAnchor.constraint(equalTo: mainContentView.leadingAnchor),
            previewImageView.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            previewImageView.heightAnchor.constraint(equalTo: previewImageView.widthAnchor, multiplier: 170/292),

            priceDescriptionLabel.topAnchor.constraint(equalTo: previewImageView.bottomAnchor),
            priceDescriptionLabel.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            priceLabel.topAnchor.constraint(equalTo: priceDescriptionLabel.bottomAnchor, constant: 4),
            priceLabel.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            mainContentView.bottomAnchor.constraint(equalTo: priceLabel.bottomAnchor),
        ])
    }
}

// MARK: - API
extension QuotationView {
    func configure(with quotation: Quotation) {
        titleLabel.setText("\(quotation.model.name)와 함께\n드라이브 떠나볼까요?")
        previewImageView.setImage(UIImage(named: "abyss_sprite")!)
        priceLabel.setText("\(quotation.totalPrice.formatted(style: .currency))")
        quotationContentView.configure(with: quotation)
    }
}
