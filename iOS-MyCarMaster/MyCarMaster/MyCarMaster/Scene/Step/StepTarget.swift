//
//  StepTarget.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import Foundation

import MCMNetwork

enum StepTarget {
    case fetchTrim(modelId: Int)
}

extension StepTarget: TargetType {

    var baseURL: URL {
        switch self {
        default:
            guard let severURL = URL(string: Dependency.serverURL) else {
                fatalError("개발자오류: 잘못된 URL입니다.")
            }
            return severURL
        }
    }

    var path: String {
        switch self {
        case .fetchTrim:
            return "trims"
        }
    }

    var httpMethod: MCMNetwork.HTTPMethod {
        switch self {
        default:
            return .get
        }
    }

    var task: MCMNetwork.Task {
        switch self {
        case .fetchTrim(let modelId):
            return .requestParameters(parameters: ["modelId": modelId])
        }
    }

    var headers: [String: String]? {
        switch self {
        default:
            return nil
        }
    }
}
