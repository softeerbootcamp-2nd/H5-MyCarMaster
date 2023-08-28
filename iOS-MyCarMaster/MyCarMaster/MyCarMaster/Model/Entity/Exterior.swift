//
//  Exterior.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct Exterior: Hashable, Priceable {
    var model: String
    var id: Int
    var name: String
    var price: Int
    var ratio: Int
    var colorImgURL: URL
    var coloredImgURL: URL
}
