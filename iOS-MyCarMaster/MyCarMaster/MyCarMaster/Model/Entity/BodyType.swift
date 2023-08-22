//
//  BodyType.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct BodyType: Hashable, Priceable {
    var model: String
    var name: String
    var description: String
    var ratio: Int
    var price: Int
    var imageURL: URL?
}
