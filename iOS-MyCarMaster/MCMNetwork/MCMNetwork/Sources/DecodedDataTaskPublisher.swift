//
//  DecodedDataTaskPublisher.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import Combine

extension URLSession {
    struct DecodedDataTaskPublisher<Output: Decodable>: Publisher {
        typealias Failure = Error

        let urlRequest: URLRequest
        let session: URLSession

        func receive<S>(subscriber: S) where S : Subscriber, Failure == S.Failure, Output == S.Input {
            let subscription = DecodedDataTaskSubscription(urlRequest: urlRequest, session: session, subscriber: subscriber)
            subscriber.receive(subscription: subscription)
        }
    }

    func decodedDataTaskPublisher<Output: Decodable>(for urlRequest: URLRequest, on session: URLSession? = nil) -> DecodedDataTaskPublisher<Output> {
        return DecodedDataTaskPublisher<Output>(urlRequest: urlRequest, session: session ?? self)
    }
}

extension URLSession.DecodedDataTaskPublisher {
    class DecodedDataTaskSubscription<Output: Decodable, S: Subscriber>: Subscription
    where S.Input == Output, S.Failure == Error {

        private let urlRequest: URLRequest
        private let session: URLSession
        private var subscriber: S?

        init(urlRequest: URLRequest, session: URLSession, subscriber: S) {
            self.urlRequest = urlRequest
            self.session = session
            self.subscriber = subscriber
        }

        func request(_ demand: Subscribers.Demand) {
            if demand > 0 {
                session.dataTask(with: urlRequest) { [weak self] data, response, error in
                    defer { self?.cancel() }

                    if let data = data {
                        do {
                            let result = try JSONDecoder().decode(Output.self, from: data)
                            _ = self?.subscriber?.receive(result)
                            self?.subscriber?.receive(completion: .finished)
                        } catch {
                            self?.subscriber?.receive(completion: .failure(error))
                        }
                    } else if let error = error {
                        self?.subscriber?.receive(completion: .failure(error))
                    }
                }.resume()
            }
        }

        func cancel() {
            subscriber = nil
        }
    }
}
