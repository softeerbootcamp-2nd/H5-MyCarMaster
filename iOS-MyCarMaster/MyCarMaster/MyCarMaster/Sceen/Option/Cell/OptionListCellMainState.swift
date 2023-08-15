//
//  OptionListCellMainState.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import Foundation

struct OptionListCellMainState {
    let model: String
    let category: String
    let name: String
    let price: Int
    let ratio: Int
}

protocol OptionListCellMainStateConvertible {
    var optionListCellMainState: OptionListCellMainState { get }
}
