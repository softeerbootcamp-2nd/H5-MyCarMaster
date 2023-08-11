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
    
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        
        self = .unknown
    }
}
