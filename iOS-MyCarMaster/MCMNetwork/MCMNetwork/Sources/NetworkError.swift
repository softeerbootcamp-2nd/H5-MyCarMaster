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
