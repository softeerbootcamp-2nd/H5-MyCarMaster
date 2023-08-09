//
//  Formatter.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import Foundation

extension Int {
    func formatted(style: NumberFormatter.Style) -> String {
        let numberFormatter = NumberFormatter()
        let number = NSNumber(value: self)

        if style == .currency {
            numberFormatter.numberStyle = .decimal
            let sign = self >= 0 ? "+" : "-"
            return "\(sign)\(numberFormatter.string(from: number)!)원"
        } else {
            numberFormatter.numberStyle = style
            return numberFormatter.string(from: NSNumber(value: self))!
        }
    }
}
