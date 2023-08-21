//
//  UINavigationController+TextStyle.swift
//  MCMResource
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import UIKit

extension UINavigationController {
    public func setLargeTitleStyle(_ style: Resource.Typeface) {
        self.navigationBar.largeTitleTextAttributes = style.textStyle.attributes
    }
    
    public func setTitleStyle(_ style: Resource.Typeface) {
        self.navigationBar.titleTextAttributes = style.textStyle.attributes
    }
}
