//
//  String+DTOURL.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import Foundation

extension String {
    var iOS: String {
        return self.replacingOccurrences(of: ".png", with: "_ios.png")
    }
}
