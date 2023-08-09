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
    
    /// Designated request-making method.
    ///
    /// - Parameters:
    ///   - target: Entity, which provides specifications necessary for a `NetworkProvider`.
    ///   - callbackQueue: Callback queue. If nil - queue from provider initializer will be used.
    /// - Returns: `AnyPublisher<NetworkResponse, NetworkError>`
    func requestPublisher(_ target: Target, callbackQueue: DispatchQueue?) -> AnyPublisher<TaskOutput, URLError>
}

public class NetworkProvider<Target: TargetType>: NetworkProviderType {

    /// Closure that defines the endpoints for the provider.
    public typealias EndpointClosure = (Target) -> Endpoint
    
    /// Closure that decides if and what request should be performed.
    public typealias RequestResultClosure = (Result<URLRequest, NetworkError>) -> Void
    
    /// Closure that resolves an `Endpoint` into a `RequestResult`.
    public typealias RequestClosure = (Endpoint, @escaping RequestResultClosure) -> Void
    
    /// A closure responsible for mapping a `TargetType` to an `EndPoint`.
    public let endpointClosure: EndpointClosure
    
    /// A closure deciding if and what request should be performed.
    public let requestClosure: RequestClosure
    
    public let session: URLSession
    
    let callbackQueue: DispatchQueue?
    
    let lock: NSRecursiveLock = NSRecursiveLock()
    
    public init(
        endpointClosure: @escaping EndpointClosure = NetworkProvider.defaultEndpointMapping,
        requestClosure: @escaping RequestClosure = NetworkProvider.defaultRequestMapping,
        callbackQueue: DispatchQueue? = nil,
        session: URLSession = .shared
    ) {
        self.endpointClosure = endpointClosure
        self.requestClosure = requestClosure
        self.callbackQueue = callbackQueue
        self.session = session
    }
    
    // TODO: 오류에 대한 처리가 많이 필요
    public func requestPublisher(_ target: Target, callbackQueue: DispatchQueue? = nil) -> AnyPublisher<TaskOutput, URLError> {
        return Just((session, target))
            .map { ($0.0, endpointClosure($0.1)) }
            .tryMap { try ($0.0, $0.1.urlRequest()) }
            .mapError({ error in
                return URLError.init(.unknown)
            })
            .flatMap { session, urlRequest in
                switch target.task {
                case .requestPlain, .requestData, .requestParameters:
                    return session.dataTaskPublisher(for: urlRequest).eraseToAnyPublisher()
                case .downloadContent:
                    return session.downloadTaskPublisher(for: urlRequest).eraseToAnyPublisher()
                }
            }
            .eraseToAnyPublisher()
    }
}

public extension NetworkProvider {
    final class func defaultEndpointMapping(for target: Target) -> Endpoint {
        var url = target.baseURL
        if !target.path.isEmpty { url = url.appendingPathComponent(target.path) }
        return Endpoint(
            url: url.absoluteString,
            httpMethod: target.httpMethod,
            task: target.task,
            httpHeaderFields: target.headers
        )
    }

    final class func defaultRequestMapping(for endpoint: Endpoint, closure: RequestResultClosure) {
        do {
            let urlRequest = try endpoint.urlRequest()
            closure(.success(urlRequest))
        } catch NetworkError.requestMapping(let url) {
            closure(.failure(NetworkError.requestMapping(url)))
        } catch {
            closure(.failure(NetworkError.unknown(errorMessage: error.localizedDescription)))
        }
    }
}
