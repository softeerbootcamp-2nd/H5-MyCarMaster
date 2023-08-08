//
//  GithubTarget.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

enum GithubTarget {
    case repo(query: String)
    case userImage
}

extension GithubTarget: TargetType {
    var baseURL: URL {
        switch self {
        case .repo:
            return URL(string: "https://api.github.com")!
        case .userImage:
            return URL(string: "https://avatars.githubusercontent.com/u/46219689?v=4")!
        }
    }

    var path: String {
        switch self {
        case .repo:
            return "/search/repositories"
        case .userImage:
            return ""
        }
    }

    var task: Task {
        switch self {
        case let .repo(query):
            return .requestParameters(parameters: ["q": query])
        case .userImage:
            return .downloadContent
        }
    }

    var httpMethod: MCMNetwork.HTTPMethod {
        return .get
    }

    var headers: [String : String]? {
        return nil
    }
}
