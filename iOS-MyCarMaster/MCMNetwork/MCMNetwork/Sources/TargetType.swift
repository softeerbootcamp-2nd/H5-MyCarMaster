//
//  Endpoint.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public protocol TargetType {
    var baseURL: String { get }
    var path: String { get }
    var parameters: [URLQueryItem] { get }
    var httpMethod: HTTPMethod { get }
    var headers: [String: String] { get }
}

// MARK: Default Implementaion
public extension TargetType {

    var parameters: [URLQueryItem] {
        return []
    }

    var headers: [String: String] {
        return [:]
    }
}

// MARK: Internal Function
extension TargetType {
    func requestURL() -> URL? {
        guard var component = URLComponents(string: baseURL) else {
            // TODO: baseURL 에러
            return nil
        }
        component.path = path
        component.queryItems = parameters
        return component.url
    }
}

// MARK: CustomStringConvertible
extension TargetType {
    var description: String {
        return "baseURL: \(baseURL), path: \(path), parameters: \(parameters)"
    }
}
