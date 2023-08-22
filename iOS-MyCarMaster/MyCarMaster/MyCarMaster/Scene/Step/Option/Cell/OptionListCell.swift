//
//  OptionListCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

final class OptionListCell: UICollectionViewCell, CellStyleSelectable {

    // MARK: Property
    private var optionState: OptionListCellMainState?

    var canExpand: Bool = false {
        didSet {
            updateUIIfExpandable()
        }
    }

    var isExpanded: Bool = false {
        didSet {
            updateUIForExpand()
        }
    }

    // MARK: View
    private let contentStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 12
    }

    private let mainContentView = UIView()

    private let leftContainer = UIView()

    private let categoryLabel = PaddingLabel().then { label in
        label.style = .buttonTitleSmall(nil)
        label.configure(left: 8, right: 8)
    }

    private let titleLabel = UILabel().then { label in
        label.style = .titleLarge2(nil)
        label.numberOfLines = 0
        label.lineBreakStrategy = .hangulWordPriority
        label.textColor = .MCM.black
    }

    private let additoryLabel = UILabel().then { label in
        label.style = .caption(nil)
        label.textColor = .MCM.grey3
    }

    private let priceLabel = UILabel().then { label in
        label.style = .titleMedium2(nil)
        label.textColor = .MCM.black
    }

    private let rightContainer = UIView()

    private let buttonStackView = UIStackView().then { stackView in
        stackView.axis = .horizontal
        stackView.spacing = 12
    }

    private let considerButton = UIButton().then { button in
        if #available(iOS 15.0, *) {
            var config = UIButton.Configuration.plain()
            config.contentInsets = .init(top: 6, leading: 12, bottom: 6, trailing: 12)
            config.baseForegroundColor = .MCM.black
            button.configuration = config
        } else {
            button.contentEdgeInsets = UIEdgeInsets(top: 6, left: 12, bottom: 6, right: 12)
            button.setTitleColor(.MCM.black, for: .normal)
        }
        button.style = .bodySmall1(nil)
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

    private let disclosureButton = UIImageView().then { imageView in
        imageView.tintColor = .MCM.black
        imageView.contentMode = .center
        imageView.preferredSymbolConfiguration = .init(pointSize: 12)
        imageView.image = UIImage(systemName: "chevron.down")
        imageView.isUserInteractionEnabled = true
    }

    private let additionalContentView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 6
        stackView.alignment = .fill
    }

    // MARK: LifeCycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
        addSelectionAction()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func prepareForReuse() {
        updateUIIfExpandable()
    }

    private func configureUI() {
        unselectedStyle()
        updateUIForExpand()
        updateUIIfExpandable()
    }
}

// MARK: - Layout
extension OptionListCell {
    private func configureLayout() {
        contentView.addSubview(contentStackView)
        contentStackView.translatesAutoresizingMaskIntoConstraints = false

        [mainContentView, additionalContentView].forEach { subview in
            contentStackView.addArrangedSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            contentStackView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 14),
            contentStackView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            contentStackView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            contentStackView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -14),
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
            rightContainer.widthAnchor.constraint(greaterThanOrEqualToConstant: 115),
        ])

        configureLeftContainerLayout()
        configureRightContainerLayout()
    }

    private func configureLeftContainerLayout() {
        let topStackView = UIStackView().then { stackView in
            stackView.axis = .horizontal
            stackView.distribution = .fillProportionally
            stackView.spacing = 8
        }

        let contentStackView = UIStackView().then { stackView in
            stackView.axis = .vertical
            stackView.spacing = 8
        }

        [contentStackView].forEach { subview in
            leftContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        [categoryLabel, additoryLabel].forEach { subview in
            topStackView.addArrangedSubview(subview)
        }

        [topStackView, titleLabel, priceLabel].forEach { subview in
            contentStackView.addArrangedSubview(subview)
        }

        NSLayoutConstraint.activate([
            contentStackView.topAnchor.constraint(equalTo: leftContainer.topAnchor),
            contentStackView.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            contentStackView.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),
            contentStackView.bottomAnchor.constraint(equalTo: leftContainer.bottomAnchor),
        ])
    }

    private func configureRightContainerLayout() {
        [buttonStackView, disclosureButton].forEach { subview in
            rightContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        [considerButton, removeButton, appendButton].forEach { subview in
            buttonStackView.addArrangedSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            buttonStackView.topAnchor.constraint(equalTo: rightContainer.topAnchor),
            buttonStackView.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),
            buttonStackView.heightAnchor.constraint(equalToConstant: 28),

            removeButton.widthAnchor.constraint(equalTo: removeButton.heightAnchor, multiplier: 1.0),
            appendButton.widthAnchor.constraint(equalTo: appendButton.heightAnchor, multiplier: 1.0),

            disclosureButton.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),
            disclosureButton.bottomAnchor.constraint(equalTo: rightContainer.bottomAnchor),
            disclosureButton.heightAnchor.constraint(equalToConstant: 18),
            disclosureButton.widthAnchor.constraint(equalToConstant: 18),
        ])
    }
}

// MARK: - Style
extension OptionListCell {
    private func addSelectionAction() {
        considerButton.addTarget(self, action: #selector(consideredStyle), for: .touchUpInside)
        removeButton.addTarget(self, action: #selector(unselectedStyle), for: .touchUpInside)
        appendButton.addTarget(self, action: #selector(selectedStyle), for: .touchUpInside)
        disclosureButton.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(disclosureButtonDidTap)))
    }

    @objc
    func disclosureButtonDidTap() {
        if canExpand {
            self.isExpanded.toggle()
        } else {
            self.presentOptionDetail()
        }
    }

    @objc
    func selectedStyle() {
        backgroundColor = selectedBackgroundColor
        layer.borderWidth = 1.0
        layer.borderColor = selectedBorderColor

        considerButton.isHidden = false
        removeButton.isHidden = false
        appendButton.isHidden = true

        removeButton.tintColor = .MCM.white
        removeButton.backgroundColor = .MCM.navyBlue5
        removeButton.layer.borderWidth = 0

        categoryLabel.backgroundColor = .MCM.navyBlue4
        categoryLabel.textColor = .MCM.navyBlue5
    }

    @objc
    func consideredStyle() {
        backgroundColor = .MCM.gold1
        layer.borderWidth = 1.0
        layer.borderColor = UIColor.MCM.gold3.cgColor

        considerButton.isHidden = true
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

    @objc
    func unselectedStyle() {
        backgroundColor = .MCM.grey1
        layer.borderWidth = 1.0
        layer.borderColor = UIColor.MCM.grey2.cgColor

        considerButton.isHidden = false
        removeButton.isHidden = true
        appendButton.isHidden = false

        appendButton.tintColor = .MCM.white
        appendButton.backgroundColor = .MCM.navyBlue5
        appendButton.layer.borderColor = UIColor.MCM.navyBlue5.cgColor
        appendButton.layer.borderWidth = 1.0

        categoryLabel.backgroundColor = .MCM.navyBlue4
        categoryLabel.textColor = .MCM.navyBlue5
    }

    private func updateUIForExpand() {
        UIView.performWithoutAnimation {
            let upsideDown = CGAffineTransform(rotationAngle: .pi)
            self.disclosureButton.transform = self.isExpanded ? upsideDown : .identity
            self.additionalContentView.isHidden = !self.isExpanded
            self.invalidateIntrinsicContentSize()
        }
    }

    private func updateUIIfExpandable() {
        if !canExpand {
            disclosureButton.image = UIImage(systemName: "chevron.right")
        } else {
            disclosureButton.image = UIImage(systemName: "chevron.down")
        }
    }
}

extension OptionListCell {
    private func presentOptionDetail() {
        // present
        print(#function, optionState)
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

        canExpand = !state.subOptions.isEmpty
        additionalContentView.removeAllArrangedSubviews()
        state.subOptions.forEach { subOption in
            let subOptionButton = SubOptionButton()
            subOptionButton.configure(with: subOption)
            additionalContentView.addArrangedSubview(subOptionButton)
        }

        optionState = state
    }
}

extension UIStackView {
    func removeAllArrangedSubviews() {
        arrangedSubviews.forEach {
            self.removeArrangedSubview($0)
            NSLayoutConstraint.deactivate($0.constraints)
            $0.removeFromSuperview()
        }
    }
}
