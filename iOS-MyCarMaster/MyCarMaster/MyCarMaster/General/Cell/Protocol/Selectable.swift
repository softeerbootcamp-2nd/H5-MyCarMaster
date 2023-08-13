//
//  Selectable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import UIKit

protocol Selectable { }

extension Selectable where Self: UICollectionViewCell {
    func select() {
        backgroundColor = .MCM.navyBlue1
        layer.borderColor = UIColor.MCM.navyBlue4.cgColor
    }

    func deselect() {
        backgroundColor = .MCM.grey1
        layer.borderColor = UIColor.MCM.grey2.cgColor
    }
}
