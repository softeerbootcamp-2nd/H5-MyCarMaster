//
//  CellStyleSelectable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import UIKit

protocol CellStyleSelectable: Selectable { }

extension CellStyleSelectable {
    var unselectedBackgroundColor: UIColor { .MCM.grey1 }
    var unselectedTextColor: UIColor { .MCM.black }
    var unselectedBorderColor: CGColor { UIColor.MCM.grey2.cgColor }

    var selectedBackgroundColor: UIColor { .MCM.navyBlue1 }
    var selectedTextColor: UIColor { .MCM.black }
    var selectedBorderColor: CGColor { UIColor.MCM.navyBlue4.cgColor }
}
