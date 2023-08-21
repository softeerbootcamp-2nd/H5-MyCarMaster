//
//  WheelDrive.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct WheelDrive: Hashable, Priceable {
    let model: String
    let name: String
    let description: String
    let ratio: Int
    let price: Int
    let imageURL: URL?
}
