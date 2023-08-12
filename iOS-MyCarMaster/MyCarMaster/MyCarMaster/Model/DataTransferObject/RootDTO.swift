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

enum Result: Codable {
    case unknown
    case trims([TrimDTO])
    case engines([EngineDTO])
    case wheelDrives([WheelDriveDTO])

    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        if let trimDTOList = try? container.decode([TrimDTO].self, forKey: .trims) {
            self = .trims(trimDTOList)
            return
        }

        if let engineDTOList = try? container.decode([EngineDTO].self, forKey: .engines) {
            self = .engines(engineDTOList)
            return
        }

        if let wheelDriveDTOList = try? container.decode([WheelDriveDTO].self, forKey: .wheelDrives) {
            self = .wheelDrives(wheelDriveDTOList)
            return
        }

        self = .unknown
    }
}
