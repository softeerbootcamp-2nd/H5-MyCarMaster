//
//  OptionDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import Foundation

struct OptionDTO: Codable {
    let id: Int
    let category: String
    let name: String
    let price: Int
    let ratio: Int
    let imgURL: String
    let summary: String
    let description: String
    let tag: String
    let subOptions: [SubOptionDTO]

    enum CodingKeys: String, CodingKey {
        case id = "id"
        case category = "category"
        case name = "name"
        case price = "price"
        case ratio = "ratio"
        case imgURL = "imgUrl"
        case summary = "summary"
        case description = "description"
        case tag = "tag"
        case subOptions = "subOptions"
    }
}

struct SubOptionDTO: Codable {
    let name: String
    let imgURL: String
    let description: String

    enum CodingKeys: String, CodingKey {
        case name = "name"
        case imgURL = "imgUrl"
        case description = "description"
    }
}

// MARK: Convert
extension Option {
    init(_ optionDTO: OptionDTO) {
        self.model = "펠리세이드"
        self.category = optionDTO.category
        self.name = optionDTO.name
        self.price = optionDTO.price
        self.ratio = optionDTO.ratio
        self.imgURL = URL(string: optionDTO.imgURL)
        self.summary = optionDTO.summary
        self.description = optionDTO.description
        self.tag = optionDTO.tag
        self.subOptions = optionDTO.subOptions.map { SubOption($0) }
    }
}

extension SubOption {
    init(_ subOptionDTO: SubOptionDTO) {
        self.name = subOptionDTO.name
        self.imgURL = URL(string: subOptionDTO.imgURL)
        self.description = subOptionDTO.description
    }
}
