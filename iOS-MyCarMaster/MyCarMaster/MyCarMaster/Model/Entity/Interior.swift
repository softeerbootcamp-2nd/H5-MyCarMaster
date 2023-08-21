//
//  Interior.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

struct Interior: Hashable {
    let model: String
    let name: String
    let price: Int
    let ratio: Int
    let colorImgURL: URL?
    let coloredImgURL: URL?
}