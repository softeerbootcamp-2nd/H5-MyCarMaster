//
//  Engine+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

extension Engine: BasicListCellStateConvertible {
    var basicListCellState: BasicListCellState {
        return .init(
            model: self.model,
            name: self.name,
            description: self.description,
            ratio: self.ratio,
            price: self.price,
            hasDetailButton: false
        )
    }
}
