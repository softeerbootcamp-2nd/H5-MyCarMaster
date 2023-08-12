//
//  CGSize+Operator.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import Foundation

extension CGSize {
    func multiply(by constant: CGFloat) -> CGSize {
        return CGSize(width: width * constant, height: height * constant)
    }

    func add(to constant: CGFloat) -> CGSize {
        return CGSize(width: width + constant, height: height + constant)
    }
}
