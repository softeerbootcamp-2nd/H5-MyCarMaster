//
//  RootDTO.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Foundation

struct RootDTO: Codable {
    let code: Int
    let message: String
    let result: Result
}

struct Result: Codable {
    let exclusiveTags: [String]?

    let trims: [TrimDTO]?
    let engines: [EngineDTO]?
    let wheelDrives: [WheelDriveDTO]?
    let bodyTypes: [BodyTypeDTO]?
    let exteriors: [ExteriorDTO]?
    let interiors: [InteriorDTO]?
    let options: [OptionDTO]?

    enum CodingKeys: String, CodingKey {
        case exclusiveTags

        case trims
        case engines
        case wheelDrives
        case bodyTypes
        case exteriors = "exteriorColors"
        case interiors = "interiorColors"
        case options
    }
}
