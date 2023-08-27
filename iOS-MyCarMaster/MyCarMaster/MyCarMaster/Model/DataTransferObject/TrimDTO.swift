//
//  TrimDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct TrimDTO: Codable {
    let id: Int
    let name: String
    let description: String
    let price: Int
    let ratio: Int
    let imgURL: String

    enum CodingKeys: String, CodingKey {
        case id
        case name
        case description
        case price
        case ratio
        case imgURL = "imgUrl"
    }
}

extension Trim {
    init(_ trimDTO: TrimDTO) {
        self.model = "펠리세이드"
        self.id = trimDTO.id
        self.name = trimDTO.name
        self.ratio = trimDTO.ratio
        self.description = trimDTO.description
        self.price = trimDTO.price
        self.imageURL = URL(string: trimDTO.imgURL)
    }
}
