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
            ratio: self.ratio,
            previewImageURL: self.subOptions.first?.imgURL,
            subOptions: self.subOptions
        )
    }
}

extension Option: QuotationContentOptionItemStateConvertible {
    var quotationContentOptionItemState: QuotationContentOptionItemState {
        return .init(
            title: self.name,
            price: self.price
        )
    }
}
