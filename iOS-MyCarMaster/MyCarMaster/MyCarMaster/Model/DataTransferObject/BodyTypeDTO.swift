//
//  BodyTypeDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct BodyTypeDTO: Codable {
    var id: Int
    var name: String
    var description: String
    var price: Int
    var ratio: Int
    var imgURL: String

    enum CodingKeys: String, CodingKey {
        case id = "id"
        case name = "name"
        case description = "description"
        case price = "price"
        case ratio = "ratio"
        case imgURL = "imgUrl"
    }
}

extension BodyType {
    init(_ bodyTypeDTO: BodyTypeDTO) {
        self.model = "펠리세이드"
        self.id = bodyTypeDTO.id
        self.name = bodyTypeDTO.name
        self.description = bodyTypeDTO.description
        self.ratio = bodyTypeDTO.ratio
        self.price = bodyTypeDTO.price
        guard let imageURL = URL(string: bodyTypeDTO.imgURL) else {
            fatalError("서버 개발자 에러: 유효하지 않은 URL입니다.")
        }
        self.imageURL = imageURL
    }
}
