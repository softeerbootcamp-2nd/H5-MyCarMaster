//
//  Endpoint.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public class Endpoint {

    public let url: String
    public let httpMethod: HTTPMethod
    public let task: Task
    public let httpHeaderFields: [String: String]?

    public init(
        url: String,
        httpMethod: HTTPMethod,
        task: Task,
        httpHeaderFields: [String: String]?
    ) {
        self.url = url
        self.httpMethod = httpMethod
        self.task = task
        self.httpHeaderFields = httpHeaderFields
    }
}

extension Endpoint {
    func urlRequest() throws -> URLRequest {
        guard let requestURL = URL(string: url) else {
            throw NetworkError.requestMapping(url)
        }

        var request = URLRequest(url: requestURL)
        request.httpMethod = httpMethod.rawValue
        request.allHTTPHeaderFields = httpHeaderFields

        switch task {
        case .requestPlain, .downloadContent:
            return request
        case let.requestParameters(parameters):
            if var urlComponent = URLComponents(url: requestURL, resolvingAgainstBaseURL: false) {
                urlComponent.queryItems = try parameters.map { (key, value) in
                    guard let value = value as? String else {
                        throw NetworkError.badRequestParameters("\(key): \(value)")
                    }
                    return URLQueryItem(name: key, value: value)
                }
                request.url = urlComponent.url
            }
            return request
        case let .requestData(data):
            request.httpBody = data
            return request
        }
    }
}

/// Required for using `Endpoint` as a key type in a `Dictionary`
extension Endpoint: Equatable, Hashable {
    public func hash(into hasher: inout Hasher) {
        if let request = try? urlRequest() {
            hasher.combine(request)
        } else {
            hasher.combine(url)
        }
    }

    /// Note: If both Endpoints fail to produce a URLRequest the comparison will
    /// fall back to comparing each Endpoint's hashValue.
    public static func == (lhs: Endpoint, rhs: Endpoint) -> Bool {

        let lhsRequest = try? lhs.urlRequest()
        let rhsRequest = try? rhs.urlRequest()
        if lhsRequest != nil, rhsRequest == nil { return false }
        if lhsRequest == nil, rhsRequest != nil { return false }
        if lhsRequest == nil, rhsRequest == nil { return lhs.hashValue == rhs.hashValue }
        return lhsRequest == rhsRequest
    }
}
