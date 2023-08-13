//
//  BasicListCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

import MCMResource

final class BasicListCell: UICollectionViewCell, Selectable, ContentSizeEstimatable {

    static var intrinsicContentSize: CGSize {
        return CGSize(width: UIScreen.main.bounds.width - 16 * 2, height: 118)
    }

    override var isSelected: Bool {
        willSet {
            if newValue {
                self.select()
            } else {
                self.deselect()
            }
        }
    }

    private let leftContainer: UIView = {
        let view = UIView()
        return view
    }()

    private let rightContainer: UIView = {
        let view = UIView()
        return view
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.style = .titleMedium1
        label.textColor = .MCM.black
        return label
    }()

    private var descriptionLabelBottomToLeftContainer: NSLayoutConstraint!

    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium2
        label.textColor = .MCM.grey3
        label.numberOfLines = 0
        label.lineBreakStrategy = .hangulWordPriority
        label.setContentCompressionResistancePriority(.defaultLow, for: .vertical)
        label.setContentHuggingPriority(.defaultLow, for: .horizontal)
        label.setContentHuggingPriority(.defaultLow, for: .vertical)
        return label
    }()

    private lazy var detailButton: UIButton = {
        let button = UIButton()
        button.style = .bodySmall2
        button.setStyledTitle("자세히 보기 >", for: .normal)
        button.setTitleColor(.MCM.black, for: .normal)
        return button
    }()

    private let additoryLabel: UILabel = {
        let label = UILabel()
        label.style = .caption
        label.textColor = .MCM.navyBlue4
        return label
    }()

    private let priceLabel: UILabel = {
        let label = UILabel()
        label.style = .titleMedium2
        label.textColor = .MCM.black
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
        layer.borderWidth = 1.0
        deselect()
    }

    private func configureLayout() {
        [leftContainer, rightContainer].forEach { container in
            contentView.addSubview(container)
            container.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            leftContainer.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            leftContainer.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            leftContainer.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16),

            rightContainer.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            rightContainer.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            rightContainer.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16),
        ])

        configureLeftContainer()
        configureRightContainer()

        NSLayoutConstraint.activate([
            leftContainer.trailingAnchor.constraint(equalTo: rightContainer.leadingAnchor),
        ])
    }

    private func configureLeftContainer() {
        [titleLabel, descriptionLabel].forEach { subview in
            leftContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        // swiftlint:disable:next line_length
        descriptionLabelBottomToLeftContainer = descriptionLabel.bottomAnchor.constraint(equalTo: leftContainer.bottomAnchor)

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: leftContainer.topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            titleLabel.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),

            descriptionLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor),
            descriptionLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            descriptionLabel.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),

            descriptionLabelBottomToLeftContainer
        ])
    }

    private func configureRightContainer() {
        [additoryLabel, priceLabel].forEach { subview in
            rightContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            additoryLabel.topAnchor.constraint(equalTo: rightContainer.topAnchor),
            additoryLabel.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),

            priceLabel.bottomAnchor.constraint(equalTo: rightContainer.bottomAnchor),
            priceLabel.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),

            rightContainer.widthAnchor.constraint(greaterThanOrEqualTo: additoryLabel.widthAnchor, multiplier: 1.0),
        ])
    }

    private func updateConstraintsIfHasDetailButton() {
        leftContainer.addSubview(detailButton)
        detailButton.translatesAutoresizingMaskIntoConstraints = false

        descriptionLabelBottomToLeftContainer.isActive = false
        NSLayoutConstraint.activate([
            detailButton.bottomAnchor.constraint(equalTo: leftContainer.bottomAnchor),
            detailButton.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),

            descriptionLabel.bottomAnchor.constraint(equalTo: detailButton.topAnchor, constant: -11),
        ])
    }
}

// MARK: - API
extension BasicListCell {
    func configure(with state: BasicListCellState) {
        titleLabel.setText(state.name)
        descriptionLabel.setText(state.description)
        additoryLabel.setText("\(state.model) 구매자의 \(state.ratio.formatted(style: .percent))가 선택")
        priceLabel.setText("+\(state.price.formatted(style: .currency))")
        if state.hasDetailButton {
            updateConstraintsIfHasDetailButton()
        }
    }
}
