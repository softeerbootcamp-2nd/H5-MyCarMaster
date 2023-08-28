//
//  UIView+Extension.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

extension UIView {
    /// intrinsicContentSize를 바탕으로, 그림자를 추가한다.
    func addShadow(color: UIColor = .black, opacity: Float = 1.0, radius: CGFloat = 5.0, offset: CGSize = .zero) {
        layer.shadowColor = color.cgColor
        layer.shadowOpacity = opacity
        layer.shadowRadius = radius
        layer.shadowOffset = offset
        layer.shadowPath = UIBezierPath(rect: CGRect(origin: .zero, size: intrinsicContentSize)).cgPath
    }
}
