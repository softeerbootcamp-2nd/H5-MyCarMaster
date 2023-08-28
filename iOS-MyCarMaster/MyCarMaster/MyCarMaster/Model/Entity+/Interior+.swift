//
//  Interior+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

extension Interior: ColorListCellStateConvertible {
    var colorListCellState: ColorListCellState {
        return ColorListCellState(
            model: model,
            titleName: name,
            ratio: ratio,
            price: price,
            colorImageURL: colorImgURL
        )
    }
}

extension Interior: QuotationContentItemStateConvertible {
    var quotationContentItemState: QuotationContentItemState {
        return .init(
            titleDescription: "내장 색상",
            title: self.name,
            imageURL: colorImgURL,
            price: self.price,
            isAdditionalPrice: true
        )
    }
}
