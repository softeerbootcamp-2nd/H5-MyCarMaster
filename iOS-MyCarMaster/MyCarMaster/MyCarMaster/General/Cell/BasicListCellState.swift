//
//  BasicListCellSate.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

struct BasicListCellState {
    var model: String
    var name: String
    var description: String
    var ratio: Int
    var price: Int
    var hasDetailButton: Bool
}

protocol BasicListCellStateConvertible {
    var basicListCellState: BasicListCellState { get }
}
