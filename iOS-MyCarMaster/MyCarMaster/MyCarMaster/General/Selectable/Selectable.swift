//
//  Selectable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import UIKit

protocol Selectable {
    var unselectedBackgroundColor: UIColor { get }
    var unselectedTextColor: UIColor { get }
    var unselectedBorderColor: CGColor { get }
    
    var selectedBackgroundColor: UIColor { get }
    var selectedTextColor: UIColor { get }
    var selectedBorderColor: CGColor { get }
    
    func selectedStyle()
    func unselectedStyle()
}
