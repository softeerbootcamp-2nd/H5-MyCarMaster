//
//  ColorListCellState.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import Foundation

struct ColorListCellState {
    var model: String
    var titleName: String
    var ratio: Int
    var price: Int
    var colorImageURL: URL?
}

protocol ColorListCellStateConvertible {
    var colorListCellState: ColorListCellState { get }
}
