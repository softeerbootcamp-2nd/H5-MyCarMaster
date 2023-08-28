//
//  GithubSearchRepository.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import UIKit
import Combine

public final class GithubSearchRepository {

    let githubProvider = NetworkProvider<GithubTarget>()
    var cancellables = Set<AnyCancellable>()

    public init() {

    }

    public func dumpData(query: String) {
        fetchData(query: query) { data in
            print(String(data: data, encoding: .utf8))
        }
    }

    public func dumpImage(completion: @escaping (UIImage?) -> Void) {
        fetchImageData { data in
            completion(UIImage(data: data))
        }
    }

    private func fetchData(query: String, completion: @escaping (Data) -> Void) {
        githubProvider.requestPublisher(.repo(query: query))
            .sink { completion in
                switch completion {
                case let .failure(error):
                    print(error)
                case .finished:
                    print("finished")
                }
            } receiveValue: { response in
                print("received", response.data.count)
                completion(response.data)
            }
            .store(in: &cancellables)
    }

    private func fetchImageData(completion: @escaping  (Data) -> Void) {
        githubProvider.requestPublisher(.userImage)
            .sink { completion in
                switch completion {
                case let .failure(error):
                    print(error)
                case .finished:
                    print("finished")
                }
            } receiveValue: { response in
                completion(response.data)
            }
            .store(in: &cancellables)
    }
}

