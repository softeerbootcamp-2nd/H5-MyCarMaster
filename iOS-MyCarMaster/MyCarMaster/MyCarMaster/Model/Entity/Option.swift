//
//  Option.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/16.
//

import Foundation

struct Option: Hashable, Priceable {
    var model: String
    var id: Int
    var category: String
    var name: String
    var price: Int
    var ratio: Int
    var imgURL: URL?
    var summary: String?
    var description: String?
    var tag: String?
    var subOptions: [SubOption]
}

struct SubOption: Hashable {
    let name: String
    let imgURL: URL?
    let description: String?

    enum CodingKeys: String, CodingKey {
        case name = "name"
        case imgURL = "imgUrl"
        case description = "description"
    }
}
