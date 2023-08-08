//
//  NetworkProvider.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation
import Combine

public protocol NetworkProviderType: AnyObject {

    associatedtype Target: TargetType

    func requestPlain(_ target: Target, callbackQueue: DispatchQueue?, configuration: URLSessionConfiguration) -> AnyPublisher<NetworkResponse, NetworkError>

    func requestData(_ target: Target, data: Data, callbackQueue: DispatchQueue?, configuration: URLSessionConfiguration) -> AnyPublisher<NetworkResponse, NetworkError>

    func fetch<T: Decodable>(_ target: Target, forType type: T.Type, data: Data?, callbackQueue: DispatchQueue?, configuration: URLSessionConfiguration) -> AnyPublisher<T, NetworkError>
}

public final class NetworkProvider<Target: TargetType>: NetworkProviderType {

    public init() {

    }

    public func requestPlain(
        _ target: Target,
        callbackQueue: DispatchQueue? = nil,
        configuration: URLSessionConfiguration = .default
    ) -> AnyPublisher<NetworkResponse, NetworkError> {
        let requestModel = RequestModel(target: target)
        return request(requestModel: requestModel, callbackQueue: callbackQueue, configuration: configuration)
    }

    public func requestData(
        _ target: Target,
        data: Data,
        callbackQueue: DispatchQueue? = nil,
        configuration: URLSessionConfiguration = .default
    ) -> AnyPublisher<NetworkResponse, NetworkError> {
        let requestModel = RequestModel(target: target, requestBody: data)
        return request(requestModel: requestModel, callbackQueue: callbackQueue, configuration: configuration)
    }

    public func fetch<T: Decodable>(
        _ target: Target,
        forType type: T.Type,
        data: Data? = nil,
        callbackQueue: DispatchQueue? = nil,
        configuration: URLSessionConfiguration = .default
    ) -> AnyPublisher<T, NetworkError> {
        var requestModel: RequestModel
        if let data = data { requestModel = RequestModel(target: target, requestBody: data) }
        else { requestModel = RequestModel(target: target) }

        return URLSession(configuration: configuration)
            .dataTaskPublisher(for: requestModel.urlRequest()!)
            .tryMap { (data, response) -> Data in
                guard response is HTTPURLResponse else {
                    throw NetworkError.unknown(errorMessage: "HTTPURLResponse conversion Error")
                }
                return data
            }
            .decode(type: type, decoder: JSONDecoder()) // FIXME: 디코딩에러남 계속...
            .mapError { error in
                if let networkError = error as? NetworkError {
                    return networkError
                }
                if let decodeError = error as? DecodingError {
                    return NetworkError.jsonDecodingError
                }
                return NetworkError.unknown(errorMessage: error.localizedDescription)
            }
            .eraseToAnyPublisher()
    }

    private func request(
        requestModel: RequestModel,
        callbackQueue: DispatchQueue? = nil,
        configuration: URLSessionConfiguration = .default
    ) -> AnyPublisher<NetworkResponse, NetworkError> {
        let sessionConfig = configuration

        return URLSession(configuration: configuration)
            .dataTaskPublisher(for: requestModel.urlRequest()!) // TODO: urlRequest 생성함수 오류 응대응
            .tryMap { output in
                guard let response = output.response as? HTTPURLResponse else {
                    throw NetworkError.unknown(errorMessage: "HTTPURLResponse conversion Error")
                }
                return NetworkResponse(
                    statusCode: response.statusCode,
                    data: output.data,
                    response: response
                )
            }
            .mapError { error in
                if let networkError = error as? NetworkError {
                    return networkError
                }
                return NetworkError.unknown(errorMessage: error.localizedDescription)
            }
            .eraseToAnyPublisher()
    }
}
