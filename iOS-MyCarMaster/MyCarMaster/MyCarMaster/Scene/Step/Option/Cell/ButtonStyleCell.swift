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

    private let inset: UIEdgeInsets = .init(top: 6, left: 12, bottom: 6, right: 12)

    private let titleLabel = UILabel().then { label in
        label.style = .bodyMedium1(nil)
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
            titleLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: inset.top),
            titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: inset.left),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -inset.right),
            titleLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -inset.bottom),
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
