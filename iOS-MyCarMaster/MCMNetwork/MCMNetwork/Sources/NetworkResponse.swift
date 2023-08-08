//
//  NetworkResponse.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public final class NetworkResponse: Equatable {

    public let statusCode: Int
    public let data: Data
    public let response: HTTPURLResponse?

    public init(statusCode: Int, data: Data, response: HTTPURLResponse? = nil) {
        self.statusCode = statusCode
        self.data = data
        self.response = response
    }

    public static func == (lhs: NetworkResponse, rhs: NetworkResponse) -> Bool {
        return lhs.statusCode == rhs.statusCode
            && lhs.data == rhs.data
            && lhs.response == rhs.response
    }
}

extension NetworkResponse: CustomStringConvertible {

    public var description: String {
        "Status Code: \(statusCode), Data Length: \(data.count)"
    }
}
