//
//  WheelDriveDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct WheelDriveDTO: Codable {
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

extension WheelDrive {
    init(_ wheelDriveDTO: WheelDriveDTO) {
        self.model = "펠리세이드"
        self.id = wheelDriveDTO.id
        self.name = wheelDriveDTO.name
        self.description = wheelDriveDTO.description
        self.ratio = wheelDriveDTO.ratio
        self.price = wheelDriveDTO.price
        guard let imageURL = URL(string: wheelDriveDTO.imgURL) else {
            fatalError("서버 개발자 에러: 유효하지 않은 URL입니다.")
        }
        self.imageURL = imageURL
    }
}
