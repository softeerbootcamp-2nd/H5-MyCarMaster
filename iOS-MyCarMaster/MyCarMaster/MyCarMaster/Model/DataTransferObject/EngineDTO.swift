//
//  EngineDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct EngineDTO: Codable {
    var id: Int
    var name: String
    var description: String
    var price: Int
    var ratio: Int
    var imgURL: String
    var power: Int
    var toque: Double
    var fuelMin: Double
    var fuelMax: Double

    enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
        case description = "description"
        case price = "price"
        case ratio = "ratio"
        case imgURL = "imgUrl"
        case power = "power"
        case toque = "toque"
        case fuelMin = "fuelMin"
        case fuelMax = "fuelMax"
    }
}

extension Engine {
    init(_ engineDTO: EngineDTO) {
        self.model = "펠리세이드"
        self.id = engineDTO.id
        self.name = engineDTO.name
        self.ratio = engineDTO.ratio
        self.description = engineDTO.description
        self.fuelMin = engineDTO.fuelMin
        self.fuelMax = engineDTO.fuelMax
        self.power = engineDTO.power
        self.toque = engineDTO.toque
        self.price = engineDTO.price
        self.imageURL = URL(string: engineDTO.imgURL)
    }
}
