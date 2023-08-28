//
//  Trim.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct Trim: Hashable, Priceable {
    var model: String
    var id: Int
    var name: String
    var ratio: Int
    var description: String
    var price: Int
    var imageURL: URL?
}
