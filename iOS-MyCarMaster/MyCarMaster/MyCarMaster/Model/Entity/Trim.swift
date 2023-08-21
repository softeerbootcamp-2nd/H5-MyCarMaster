//
//  Trim.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct Trim: Hashable, Priceable {
    let model: String
    let name: String
    let ratio: Int
    let description: String
    var price: Int
    var imageURL: URL?
}
