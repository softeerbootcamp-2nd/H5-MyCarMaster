//
//  ThemeButton.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import UIKit

final class ThemeButton: UIButton, ButtonStyleSeletable {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: 0, height: 44)
    }

    override init(frame: CGRect) {
        super.init(frame: .zero)
        configureUI()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        style = .titleMedium2(nil)
    }
}
