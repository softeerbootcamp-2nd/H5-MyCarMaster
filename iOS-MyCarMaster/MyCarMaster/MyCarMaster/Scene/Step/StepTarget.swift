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
    case fetchEngine(trimId: Int)
    case fetchWheelDrive(trimId: Int, engineId: Int)
    case fetchBodyType(modelId: Int)
    case fetchExterior(trimId: Int)
    case fetchInterior(trimId: Int, exteriorColorId: Int)
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
        case .fetchEngine:
            return "engines"
        case .fetchWheelDrive:
            return "wheel-drives"
        case .fetchBodyType:
            return "body-types"
        case .fetchExterior:
            return "exterior-colors"
        case .fetchInterior:
            return "interior-colors"
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
        case let .fetchTrim(modelId):
            return .requestParameters(parameters: ["modelId": modelId])
        case let .fetchEngine(trimId):
            return .requestParameters(parameters: ["trimId": trimId])
        case let .fetchWheelDrive(trimId, engineId):
            return .requestParameters(parameters: ["trimId": trimId, "engineId": engineId])
        case let .fetchBodyType(modelId):
            return .requestParameters(parameters: ["modelId": modelId])
        case let .fetchExterior(trimId):
            return .requestParameters(parameters: ["trimId": trimId])
        case let .fetchInterior(trimId, exteriorColorId):
            return .requestParameters(parameters: ["trimId": trimId, "exteriorColorId": exteriorColorId])
        }
    }

    var headers: [String: String]? {
        switch self {
        default:
            return nil
        }
    }
}
