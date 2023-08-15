//
//  Option.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import Foundation

struct Option: Codable {
    let model: String
    let category: String
    let name: String
    let price: Int
    let ratio: Int
    let imgURL: URL?
    let summary: String
    let description: String
    let tag: String
    let subOptions: [SubOption]
}

struct SubOption: Codable {
    let name: String
    let imgURL: URL?
    let description: String

    enum CodingKeys: String, CodingKey {
        case name = "name"
        case imgURL = "imgUrl"
        case description = "description"
    }
}
