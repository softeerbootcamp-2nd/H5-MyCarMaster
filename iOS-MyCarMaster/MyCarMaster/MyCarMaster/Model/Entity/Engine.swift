//
//  Engine.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct Engine: Hashable, Priceable {
    var model: String
    var id: Int
    var name: String
    var ratio: Int
    var description: String
    var fuelMin: Double
    var fuelMax: Double
    var power: Int
    var toque: Double
    var price: Int
    var imageURL: URL?
}
