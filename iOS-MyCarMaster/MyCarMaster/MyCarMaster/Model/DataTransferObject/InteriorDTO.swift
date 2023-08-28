//
//  InteriorDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Foundation

struct InteriorDTO: Codable {
    let id: Int
    let name: String
    let price: Int
    let ratio: Int
    let colorImgURL: String
    let coloredImgURL: String

    enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
        case price = "price"
        case ratio = "ratio"
        case colorImgURL = "colorImgUrl"
        case coloredImgURL = "coloredImgUrl"
    }
}

extension Interior {
    init(_ interiorDTO: InteriorDTO) {
        self.model = "펠리세이드"
        self.id = interiorDTO.id
        self.name = interiorDTO.name
        self.price = interiorDTO.price
        self.ratio = interiorDTO.ratio
        guard let colorImgURL = URL(string: interiorDTO.colorImgURL) else {
            fatalError("서버 개발자 에러: 유효하지 않은 URL입니다.")
        }
        self.colorImgURL = colorImgURL
        guard let coloredImgURL = URL(string: interiorDTO.coloredImgURL) else {
            fatalError("서버 개발자 에러: 유효하지 않은 URL입니다.")
        }
        self.coloredImgURL = coloredImgURL
    }
}
