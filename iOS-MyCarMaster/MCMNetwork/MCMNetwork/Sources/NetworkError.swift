//
//  NetworkError.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public enum NetworkError: Error, Equatable {
    case badBaseURL(baseURL: String)
    case badRequestURL(targetDescription: String)
    case badRequestParameters(_ parameter: String)
    case jsonDecodingError
    case unknown(errorMessage: String)
    case requestMapping(_ url: String)
    
}

extension NetworkError: LocalizedError {
    public var errorDescription: String? {
        switch self {
        case let .badBaseURL(baseURL):
            return "badBaseURL: \(baseURL)"
        case let .badRequestURL(targetDescription):
            return "badRequestURL: \(targetDescription)"
        case let .badRequestParameters(parameter):
            return "badRequestParameter: \(parameter)"
        case let .jsonDecodingError:
            return "jsonDecodingError"
        case let .unknown(errorMessage):
            return "unkown: \(errorMessage)"
        case let .requestMapping(url):
            return "requestMapping \(url)"
        }
    }
}
