//
//  String+TextStyle.swift
//  MCMResource
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit

extension String {
    public func styled(with typeface: Resource.Typeface) -> NSAttributedString {
        return attributedString(textStyle: typeface.textStyle)
    }

    func attributedString(textStyle: TextStyle) -> NSAttributedString {
        let attributedString = NSMutableAttributedString(string: self)
        attributedString.addAttributes(textStyle.attributes, range: NSRange(location: 0, length: self.count))
        return attributedString
    }
}

