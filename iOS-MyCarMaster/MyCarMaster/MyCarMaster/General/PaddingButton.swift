//
//  PaddingButton.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

final class PaddingButton: UIButton {

    private let vInsets: CGFloat
    private let hInsets: CGFloat

    init(vertical vInsets: CGFloat, horizontal hInsets: CGFloat) {
        self.vInsets = vInsets
        self.hInsets = hInsets
        super.init(frame: .zero)
        configureUI()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        if #available(iOS 15.0, *) {
            var config = UIButton.Configuration.filled()
            config.contentInsets = NSDirectionalEdgeInsets(top: vInsets, leading: hInsets, bottom: vInsets, trailing: hInsets)
            config.background.cornerRadius = 0
            configuration = config
        } else {
            contentEdgeInsets = UIEdgeInsets(top: vInsets, left: hInsets, bottom: vInsets, right: hInsets)
        }
    }
}

// MARK: - API
extension PaddingButton {
    func configureUI(
        titleColor: UIColor? = nil,
        backgroundColor: UIColor? = nil,
        borderWidth: CGFloat? = nil,
        borderColor: UIColor? = nil
    ) {
        if let titleColor {
            if #available(iOS 15.0, *) {
                configuration?.baseForegroundColor = titleColor
            } else {
                setTitleColor(titleColor, for: .normal)
            }
        }

        if let backgroundColor {
            if #available(iOS 15.0, *) {
                configuration?.background.backgroundColor = backgroundColor
            } else {
                self.backgroundColor = backgroundColor
            }
        }

        if let borderWidth {
            if #available(iOS 15.0, *) {
                configuration?.background.strokeWidth = borderWidth
            } else {
                layer.borderWidth = borderWidth
            }
        }

        if let borderColor {
            if #available(iOS 15.0, *) {
                configuration?.background.strokeColor = borderColor
            } else {
                layer.borderColor = borderColor.cgColor
            }
        }
    }
}
