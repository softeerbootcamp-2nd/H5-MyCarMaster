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

        var number: NSNumber
        if style == .currency {
            number = NSNumber(value: self)
            numberFormatter.numberStyle = .decimal
            // swiftlint:disable:next force_unwrapping
            return "\(numberFormatter.string(from: number)!)원"
        } else if style == .percent {
            number = NSNumber(value: Double(self) / 100)
        } else {
            number = NSNumber(value: self)
        }

        numberFormatter.numberStyle = style
        // swiftlint:disable:next force_unwrapping
        return numberFormatter.string(from: number)!
    }
}
