//
//  BodyType+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

extension BodyType: BasicListCellStateConvertible {
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

extension BodyType: QuotationContentItemStateConvertible {
    var quotationContentItemState: QuotationContentItemState {
        return .init(
            titleDescription: "바디 타입",
            title: self.name,
            imageURL: nil,
            price: self.price,
            isAdditionalPrice: false
        )
    }
}
