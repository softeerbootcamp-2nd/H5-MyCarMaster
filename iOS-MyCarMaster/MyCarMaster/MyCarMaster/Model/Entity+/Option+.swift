//
//  Option+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import Foundation

extension Option: OptionListCellMainStateConvertible {
    var optionListCellMainState: OptionListCellMainState {
        return .init(
            model: self.model,
            category: self.category,
            name: self.name,
            price: self.price,
            ratio: self.price,
            subOptions: self.subOptions
        )
    }
}
