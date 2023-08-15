//
//  UICollectionViewCell+Selectable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

extension Selectable where Self: UICollectionViewCell {
    func selectedStyle() {
        backgroundColor = selectedBackgroundColor
        layer.borderColor = selectedBorderColor
    }
    
    func unselectedStyle() {
        backgroundColor = unselectedBackgroundColor
        layer.borderColor = unselectedBorderColor
    }
}
