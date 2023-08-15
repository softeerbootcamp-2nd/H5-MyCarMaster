//
//  OptionListCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

final class OptionListCell: UICollectionViewCell, CellStyleSelectable {

    private let mainContentView = UIView()

    private let leftContainer = UIView()

    private let categoryLabel = PaddingLabel().then { label in
        label.style = .buttonTitleSmall
        label.configure(left: 8, right: 8)
    }

    private let titleLabel = UILabel().then { label in
        label.style = .titleLarge2
        label.numberOfLines = 0
        label.lineBreakStrategy = .hangulWordPriority
        label.textColor = .MCM.black
    }

    private let additoryLabel = UILabel().then { label in
        label.style = .bodySmall2
        label.textColor = .MCM.grey3
    }

    private let priceLabel = UILabel().then { label in
        label.style = .titleMedium2
        label.textColor = .MCM.black
    }

    private let rightContainer = UIView()

    private let buttonStackView = UIStackView().then { stackView in
        stackView.axis = .horizontal
        stackView.spacing = 12
    }

    private let wishListButton = UIButton().then { button in
        if #available(iOS 15.0, *) {
            var config = UIButton.Configuration.plain()
            config.contentInsets = .init(top: 6, leading: 12, bottom: 6, trailing: 12)
            config.baseForegroundColor = .MCM.black
            button.configuration = config
        } else {
            button.contentEdgeInsets = UIEdgeInsets(top: 6, left: 12, bottom: 6, right: 12)
            button.setTitleColor(.MCM.black, for: .normal)
        }
        button.style = .bodySmall1
        button.backgroundColor = .MCM.white
        button.layer.cornerRadius = 14
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.MCM.grey2.cgColor
        button.setStyledTitle("고민해보기", for: .normal)
    }

    private let symbolConfiguration = UIImage.SymbolConfiguration.init(pointSize: 10, weight: .bold)
    private lazy var appendButton = UIButton().then { button in
        button.setImage(UIImage(systemName: "plus")?.withConfiguration(symbolConfiguration), for: .normal)
        button.contentMode = .scaleAspectFit
        button.clipsToBounds = true
        button.layer.cornerRadius = 14
    }

    private lazy var removeButton = UIButton().then { button in
        button.setImage(UIImage(systemName: "xmark")?.withConfiguration(symbolConfiguration), for: .normal)
        button.clipsToBounds = true
        button.layer.cornerRadius = 14
    }

    private let foldStateImage = UIImage(systemName: "chevron.down")
    private let unfoldStateImage = UIImage(systemName: "chevron.up")
    private let foldStateIndicatorView = UIImageView().then { imageView in
        imageView.tintColor = .MCM.black
    }

    private let additionalContentView = UIView()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        unselectedStyle()
    }

    private func configureLayout() {
        [mainContentView, additionalContentView].forEach { subview in
            contentView.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            mainContentView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 14),
            mainContentView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            mainContentView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            mainContentView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -14),
        ])

        configureMainContentViewLayout()
    }

    private func configureMainContentViewLayout() {
        [leftContainer, rightContainer].forEach { subview in
            mainContentView.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            leftContainer.topAnchor.constraint(equalTo: mainContentView.topAnchor),
            leftContainer.leadingAnchor.constraint(equalTo: mainContentView.leadingAnchor),
            leftContainer.bottomAnchor.constraint(equalTo: mainContentView.bottomAnchor),
            leftContainer.trailingAnchor.constraint(equalTo: rightContainer.leadingAnchor),

            rightContainer.topAnchor.constraint(equalTo: mainContentView.topAnchor),
            rightContainer.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            rightContainer.bottomAnchor.constraint(equalTo: mainContentView.bottomAnchor),
        ])

        configureLeftContainerLayout()
        configureRightContainerLayout()
    }

    private func configureLeftContainerLayout() {
        [categoryLabel, titleLabel, additoryLabel, priceLabel].forEach { subview in
            leftContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            categoryLabel.topAnchor.constraint(equalTo: leftContainer.topAnchor),
            categoryLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),

            titleLabel.topAnchor.constraint(equalTo: categoryLabel.bottomAnchor, constant: 8),
            titleLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            titleLabel.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),

            additoryLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 4),
            additoryLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            additoryLabel.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),

            priceLabel.topAnchor.constraint(equalTo: additoryLabel.bottomAnchor, constant: 8),
            priceLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            priceLabel.bottomAnchor.constraint(equalTo: leftContainer.bottomAnchor),
        ])
    }

    private func configureRightContainerLayout() {
        [buttonStackView, foldStateIndicatorView].forEach { subview in
            rightContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        [wishListButton, removeButton, appendButton].forEach { subview in
            buttonStackView.addArrangedSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            buttonStackView.topAnchor.constraint(equalTo: rightContainer.topAnchor),
            buttonStackView.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),
            buttonStackView.leadingAnchor.constraint(equalTo: rightContainer.leadingAnchor),
            buttonStackView.heightAnchor.constraint(equalToConstant: 28),

            removeButton.widthAnchor.constraint(equalTo: removeButton.heightAnchor, multiplier: 1.0),
            appendButton.widthAnchor.constraint(equalTo: appendButton.heightAnchor, multiplier: 1.0),

            foldStateIndicatorView.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),
            foldStateIndicatorView.bottomAnchor.constraint(equalTo: rightContainer.bottomAnchor),
            foldStateIndicatorView.heightAnchor.constraint(equalToConstant: 18),
            foldStateIndicatorView.widthAnchor.constraint(equalToConstant: 18),
        ])
    }
}

// MARK: - API
extension OptionListCell {
    func configure(with stateConvertible: OptionListCellMainStateConvertible) {
        let state = stateConvertible.optionListCellMainState

        categoryLabel.setText(state.category)
        titleLabel.setText(state.name)
        additoryLabel.setText("\(state.model) 구매자의 \(state.ratio.formatted(style: .percent))가 선택")
        priceLabel.setText("+\(state.price.formatted(style: .currency))")
    }
    
    func selectedStyle() {
        backgroundColor = selectedBackgroundColor
        layer.borderWidth = 1.0
        layer.borderColor = selectedBorderColor
        
        wishListButton.isHidden = false
        removeButton.isHidden = false
        appendButton.isHidden = true
        
        removeButton.tintColor = .MCM.white
        removeButton.backgroundColor = .MCM.navyBlue5
        removeButton.layer.borderWidth = 0
        
        categoryLabel.backgroundColor = .MCM.navyBlue4
        categoryLabel.textColor = .MCM.navyBlue5
    }
    
    func wishListedStyle() {
        backgroundColor = .MCM.gold1
        layer.borderWidth = 1.0
        layer.borderColor = UIColor.MCM.gold3.cgColor
        
        wishListButton.isHidden = true
        removeButton.isHidden = false
        appendButton.isHidden = false
        
        removeButton.tintColor = .MCM.gold3
        removeButton.backgroundColor = .MCM.white
        removeButton.layer.borderColor = UIColor.MCM.grey2.cgColor
        removeButton.layer.borderWidth = 1.0
        
        appendButton.tintColor = .MCM.white
        appendButton.backgroundColor = .MCM.gold3
        appendButton.layer.borderColor = UIColor.MCM.gold3.cgColor
        appendButton.layer.borderWidth = 1.0
        
        categoryLabel.backgroundColor = .MCM.gold2
        categoryLabel.textColor = .MCM.gold3
    }
    
    func unselectedStyle() {
        backgroundColor = .MCM.grey1
        layer.borderWidth = 1.0
        layer.borderColor = UIColor.MCM.grey1.cgColor
        
        wishListButton.isHidden = false
        removeButton.isHidden = true
        appendButton.isHidden = false
        
        appendButton.tintColor = .MCM.white
        appendButton.backgroundColor = .MCM.navyBlue5
        appendButton.layer.borderColor = UIColor.MCM.navyBlue5.cgColor
        appendButton.layer.borderWidth = 1.0
        
        categoryLabel.backgroundColor = .MCM.navyBlue4
        categoryLabel.textColor = .MCM.navyBlue5
    }
    
    func fold() {
        foldStateIndicatorView.image = foldStateImage
    }
    
    func unfold() {
        foldStateIndicatorView.image = unfoldStateImage
    }
}
