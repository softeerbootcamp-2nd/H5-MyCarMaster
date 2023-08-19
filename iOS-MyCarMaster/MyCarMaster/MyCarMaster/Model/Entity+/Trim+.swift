//
//  Trim+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

extension Trim: BasicListCellStateConvertible {
    var basicListCellState: BasicListCellState {
        return .init(
            model: self.model,
            name: self.name,
            description: self.description,
            ratio: self.ratio,
            price: self.price,
            hasDetailButton: true
        )
    }
}

extension Trim: QuotationContentItemStateConvertible {
    var quotationContentItemState: QuotationContentItemState {
        return .init(
            titleDescription: "트림",
            title: self.name,
            imageURL: nil,
            price: self.price,
            isAdditionalPrice: false
        )
    }
}
