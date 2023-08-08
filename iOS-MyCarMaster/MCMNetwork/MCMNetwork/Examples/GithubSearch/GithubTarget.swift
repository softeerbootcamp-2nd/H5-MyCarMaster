//
//  GithubTarget.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

enum GithubTarget {
    case repo(query: String)
    case user(query: String)
}

extension GithubTarget: TargetType {

    var baseURL: String {
        return "https://api.github.com"
    }

    var path: String {
        switch self {
        case .repo:
            return "/search/repositories"
        case .user:
            return "/search/users"
        }
    }

    var parameters: [URLQueryItem] {
        switch self {
        case let .repo(query):
            return [URLQueryItem(name: "q", value: query)]
        case let .user(query):
            return [URLQueryItem(name: "q", value: query)]
        }
    }

    var httpMethod: MCMNetwork.HTTPMethod {
        return .get
    }
}
