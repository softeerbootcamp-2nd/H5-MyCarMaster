//
//  ButtonStyleCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

final class ButtonCell: UICollectionViewCell {

    override var isSelected: Bool {
        willSet {
            if newValue {
                selectedStyle()
            } else {
                unselectedStyle()
            }
        }
    }

    private let titleLabel = UILabel().then { label in
        label.style = .bodyMedium1
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
        unselectedStyle()
    }

    private func configureLayout() {
        [titleLabel].forEach { subview in
            contentView.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 6),
            titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            titleLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -6),
        ])
    }

    func setStyledTitle(_ title: String) {
        titleLabel.setText(title)
    }
}

extension ButtonCell: ButtonStyleSeletable {
    func selectedStyle() {
        backgroundColor = selectedBackgroundColor
        titleLabel.textColor = selectedTextColor
        layer.borderWidth = 1.0
        layer.borderColor = selectedBorderColor
    }

    func unselectedStyle() {
        backgroundColor = unselectedBackgroundColor
        titleLabel.textColor = unselectedTextColor
        layer.borderWidth = 1.0
        layer.borderColor = unselectedBorderColor
    }
}
