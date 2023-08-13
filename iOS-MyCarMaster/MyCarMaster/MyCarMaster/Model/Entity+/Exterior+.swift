//
//  Exterior+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import Foundation

extension Exterior: ColorListCellStateConvertible {
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
