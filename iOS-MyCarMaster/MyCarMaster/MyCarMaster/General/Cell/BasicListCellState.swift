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

extension BasicListCellState {
    init(from trim: Trim) {
        self
    }
    init(from engine: Engine) {
        self.init(model: engine.model, name: engine.name, description: engine.description, ratio: engine.ratio, price: engine.price, hasDetailButton: false)
    }
    init(from wheelDrive: WheelDrive) {
        self.init(model: wheelDrive.model, name: wheelDrive.name, description: wheelDrive.description, ratio: wheelDrive.ratio, price: wheelDrive.price, hasDetailButton: false)
    }
    init(from bodyType: BodyType) {
        self.init(model: bodyType.model, name: bodyType.name, description: bodyType.description, ratio: bodyType.ratio, price: bodyType.price, hasDetailButton: false)
    }
}
