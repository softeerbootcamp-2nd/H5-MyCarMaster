//
//  ViewController.swift
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

    public func dumpRepositories(query: String) {
        fetchRepository(query: query) { repositories in
            repositories.items.forEach { repository in
                print(repository.name, repository.score)
            }
        }
    }

    private func fetchData(query: String, completion: @escaping (Data) -> Void) {
        githubProvider.requestPlain(.repo(query: query))
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

    private func fetchRepository(query: String, completion: @escaping  (GithubRepository) -> Void) {
        githubProvider.fetch(.repo(query: query), forType: GithubRepository.self)
            .sink { completion in
                switch completion {
                case let .failure(error):
                    print(error)
                case .finished:
                    print("finished")
                }
            } receiveValue: { response in
                completion(response)
            }
            .store(in: &cancellables)
    }
}

