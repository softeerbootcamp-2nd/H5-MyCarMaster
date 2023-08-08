//
//  RequestModel.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public struct RequestModel {

    let target: TargetType
    let body: Data?
    let requestTimeout: Float?

    public init(
        target: TargetType,
        requestBody: Data? = nil,
        requestTimeout: Float? = nil
    ) {
        self.target = target
        self.body = requestBody
        self.requestTimeout = requestTimeout
    }

    func urlRequest() -> URLRequest? {
        guard let url = target.requestURL() else {
            return nil
        }

        var request = URLRequest(url: url)
        request.httpMethod = target.httpMethod.rawValue
        request.httpBody = body
        for header in target.headers {
            request.addValue(header.value, forHTTPHeaderField: header.key)
        }
        return request
    }
}
