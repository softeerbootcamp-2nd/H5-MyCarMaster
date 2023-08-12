//
//  BodyTypeDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct BodyTypeDTO: Codable {
    var id: Int
    var name: String
    var description: String
    var price: Int
    var ratio: Int
    var imgURL: String

    enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
        case description = "description"
        case price = "price"
        case ratio = "ratio"
        case imgURL = "imgUrl"
    }
}

extension BodyType {
    init(_ bodyTypeDTO: BodyTypeDTO) {
        self.model = "펠리세이드"
        self.name = bodyTypeDTO.name
        self.description = bodyTypeDTO.description
        self.ratio = bodyTypeDTO.ratio
        self.price = bodyTypeDTO.price
        self.imageURL = URL(string: bodyTypeDTO.imgURL)
    }
}
