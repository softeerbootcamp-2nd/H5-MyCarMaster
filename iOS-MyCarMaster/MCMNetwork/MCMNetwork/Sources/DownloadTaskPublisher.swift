//
//  DownloadTaskPublisher.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import Combine

public typealias TaskOutput = (data: Data, response: URLResponse)
public protocol TaskPublisher: Publisher where Output == TaskOutput, Failure == URLError { }

extension URLSession.DataTaskPublisher: TaskPublisher { }
extension URLSession.DownloadTaskPublisher: TaskPublisher { }

extension URLSession {
    public struct DownloadTaskPublisher: Publisher {
        public typealias Output = TaskOutput
        public typealias Failure = URLError

        let urlRequest: URLRequest
        let session: URLSession
        let dataReadingOption: Data.ReadingOptions

        public func receive<S>(subscriber: S) where S : Subscriber, Failure == S.Failure, Output == S.Input {
            let subscription = DownloadTaskSubscription(
                urlRequest: urlRequest,
                session: session,
                subscriber: subscriber
            )
            subscriber.receive(subscription: subscription)
        }
    }

    public func downloadTaskPublisher(
        for urlRequest: URLRequest,
        on session: URLSession? = nil,
        dataReadingOption: Data.ReadingOptions = []
    ) -> DownloadTaskPublisher {
        return DownloadTaskPublisher(
            urlRequest: urlRequest,
            session: session ?? self,
            dataReadingOption: dataReadingOption
        )
    }
}

extension URLSession.DownloadTaskPublisher {
    class DownloadTaskSubscription<S: Subscriber>: Subscription
    where S.Input == Output, S.Failure == Failure {

        private let urlRequest: URLRequest
        private let session: URLSession
        private var subscriber: S?
        private let dataReadingOptions: Data.ReadingOptions

        init(urlRequest: URLRequest, session: URLSession, subscriber: S, dataReadingOptions: Data.ReadingOptions = []) {
            self.urlRequest = urlRequest
            self.session = session
            self.subscriber = subscriber
            self.dataReadingOptions = dataReadingOptions
        }

        func request(_ demand: Subscribers.Demand) {
            if demand > .none {
                session.downloadTask(with: urlRequest) { [weak self] url, response, error in
                    guard let self else { return }

                    defer { self.cancel() }

                    if let url, let response {
                        do {
                            let data = try Data(contentsOf: url, options: self.dataReadingOptions)
                            _ = self.subscriber?.receive((data, response))
                        } catch {
                            self.subscriber?.receive(completion: .failure(URLError(URLError.fileDoesNotExist)))
                        }
                        self.subscriber?.receive(completion: .finished)
                    } else if let error = error {
                        self.subscriber?.receive(completion: .failure(URLError(URLError.badServerResponse)))
                    }
                }.resume()
            }
        }

        func cancel() {
            subscriber = nil
        }
    }
}

