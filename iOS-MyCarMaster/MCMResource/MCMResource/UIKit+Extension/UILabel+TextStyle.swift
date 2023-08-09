//
//  UILabel+TextStyle.swift
//  MCMResource
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit

extension UILabel {
    private struct AssociatedKeys {
        static var typeface = "typeface"
    }

    public var style: Resource.Typeface {
        get {
            guard let style = objc_getAssociatedObject(self, &AssociatedKeys.typeface) as? Resource.Typeface else {
                return .default
            }
            return style
        }
        set {
            objc_setAssociatedObject(self, &AssociatedKeys.typeface, newValue, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN_NONATOMIC)
            self.font = newValue.textStyle.font
        }
    }

    public func setText(_ text: String) {
        self.attributedText = text.styled(with: style)
    }
}
