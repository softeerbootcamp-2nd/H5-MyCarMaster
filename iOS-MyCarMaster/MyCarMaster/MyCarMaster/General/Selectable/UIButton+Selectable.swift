//
//  UICollectionViewCell+ButtonStyleSelectable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

extension Selectable where Self: UIButton {

    func selectedStyle() {
        backgroundColor = selectedBackgroundColor
        setTitleColor(selectedTextColor, for: .normal)
        layer.borderWidth = 1.0
        layer.borderColor = selectedBorderColor
    }

    func secondaryStyle() {
        backgroundColor = .MCM.white
        setTitleColor(selectedBackgroundColor, for: .normal)
        layer.borderWidth = 1.0
        layer.borderColor = selectedBorderColor
    }

    func unselectedStyle() {
        backgroundColor = unselectedBackgroundColor
        setTitleColor(unselectedTextColor, for: .normal)
        layer.borderWidth = 1.0
        layer.borderColor = unselectedBorderColor
    }
}
