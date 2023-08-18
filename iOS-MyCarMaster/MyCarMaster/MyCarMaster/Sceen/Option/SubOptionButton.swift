//
//  SubOptionButton.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/17.
//

import UIKit

final class SubOptionButton: UIView {

    private var subOption: SubOption?

    private let contentView = UIStackView().then { stackView in
        stackView.axis = .horizontal
        stackView.distribution = .equalSpacing
        stackView.alignment = .center
        stackView.spacing = 8
    }

    private let titleLabel = UILabel().then { label in
        label.setContentCompressionResistancePriority(.defaultLow, for: .horizontal)
        label.showsExpansionTextWhenTruncated = true
        label.style = .bodyLarge1(nil)
        label.textColor = .MCM.black
    }

    private let disclosureIndicator = UIImageView().then { imageView in
        imageView.tintColor = .MCM.black
        imageView.preferredSymbolConfiguration = .init(pointSize: 12)
        imageView.image = UIImage(systemName: "chevron.right")
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
        self.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(buttonDidTap(_:))))
    }

    private func configureLayout() {
        addSubview(contentView)
        contentView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentView.topAnchor.constraint(equalTo: topAnchor),
            contentView.leadingAnchor.constraint(equalTo: leadingAnchor),
            contentView.trailingAnchor.constraint(equalTo: trailingAnchor),
            contentView.bottomAnchor.constraint(equalTo: bottomAnchor),
            contentView.heightAnchor.constraint(equalToConstant: 28)
        ])

        [titleLabel, disclosureIndicator].forEach { subview in
            contentView.addArrangedSubview(subview)
        }
    }
}

// MARK: - API
extension SubOptionButton {
    @objc
    private func buttonDidTap(_ sender: UITapGestureRecognizer) {
        // persent()
        print(#function, subOption)
    }

    func configure(with subOption: SubOption) {
        titleLabel.setText(subOption.name)
        self.subOption = subOption
    }
}
