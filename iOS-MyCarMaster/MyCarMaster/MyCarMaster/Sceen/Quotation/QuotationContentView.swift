//
//  QuotationContentView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

final class QuotationContentView: UIView {

    // MARK: Property
    private let itemSpacing: CGFloat = 12

    // MARK: View
    private let titleLabel = UILabel().then { label in
        label.style = .titleLarge1(nil)
        label.textColor = .MCM.black
        label.setText("최종 견적")
    }

    private lazy var contentItemStackView = UIStackView().then { stackView in
        stackView.spacing = itemSpacing
        stackView.axis = .vertical
    }

    private let trimContentItemView = QuotationContentItemView()
    private let engineContentItemView = QuotationContentItemView()
    private let wheelDriveContentItemView = QuotationContentItemView()
    private let bodyTypeContentItemView = QuotationContentItemView()
    private let exteriorContentItemView = QuotationContentItemView()
    private let interiorContentItemView = QuotationContentItemView()
    private let optionContentItemView = QuotationContentOptionView()

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
        [titleLabel, contentItemStackView].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor),

            contentItemStackView.topAnchor.constraint(equalTo: titleLabel.topAnchor),
            contentItemStackView.leadingAnchor.constraint(equalTo: leadingAnchor),
            contentItemStackView.trailingAnchor.constraint(equalTo: trailingAnchor),
            bottomAnchor.constraint(equalTo: contentItemStackView.bottomAnchor),
        ])

        [
            trimContentItemView,
            engineContentItemView,
            wheelDriveContentItemView,
            bodyTypeContentItemView,
            exteriorContentItemView,
            interiorContentItemView,
            optionContentItemView
        ].forEach { subview in
            contentItemStackView.addArrangedSubview(subview)
            contentItemStackView.addArrangedSubview(SeparatorView())
        }
    }
}

// MARK: - API
extension QuotationContentView {
    func configure(with quotation: Quotation) {
        trimContentItemView.configure(with: quotation.trim)
        engineContentItemView.configure(with: quotation.engine)
        wheelDriveContentItemView.configure(with: quotation.wheelDrive)
        bodyTypeContentItemView.configure(with: quotation.bodyType)
        exteriorContentItemView.configure(with: quotation.exterior)
        interiorContentItemView.configure(with: quotation.interior)
        optionContentItemView.configure(with: quotation)
    }
}
