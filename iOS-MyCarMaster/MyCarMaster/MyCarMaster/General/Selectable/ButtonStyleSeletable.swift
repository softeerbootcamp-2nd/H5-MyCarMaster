//
//  ButtonStyleSeletable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

protocol ButtonStyleSeletable: Selectable { }

extension ButtonStyleSeletable {
    var unselectedBackgroundColor: UIColor { .MCM.grey1 }
    var unselectedTextColor: UIColor { .MCM.grey3 }
    var unselectedBorderColor: CGColor { UIColor.MCM.grey2.cgColor }

    var selectedBackgroundColor: UIColor { .MCM.navyBlue5 }
    var selectedTextColor: UIColor { .MCM.grey1 }
    var selectedBorderColor: CGColor { UIColor.MCM.navyBlue5.cgColor }
}
