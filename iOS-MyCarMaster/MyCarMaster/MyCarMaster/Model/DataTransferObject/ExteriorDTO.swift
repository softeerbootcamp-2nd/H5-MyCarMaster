//
//  ExteriorDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct ExteriorDTO: Codable {
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

extension Exterior {
    init(_ exteriorDTO: ExteriorDTO) {
        self.model = "펠리세이드"
        self.id = exteriorDTO.id
        self.name = exteriorDTO.name
        self.price = exteriorDTO.price
        self.ratio = exteriorDTO.ratio
        guard let colorImgURL = URL(string: exteriorDTO.colorImgURL) else {
            fatalError("서버 개발자 에러: 유효하지 않은 URL입니다.")
        }
        self.colorImgURL = colorImgURL
        guard let coloredImgURL = URL(string: exteriorDTO.coloredImgURL + "mid/sprite.png") else {
            fatalError("서버 개발자 에러: 유효하지 않은 URL입니다.")
        }
        self.coloredImgURL = coloredImgURL
    }
}
