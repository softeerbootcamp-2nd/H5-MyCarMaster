//
//  ConcatenateMany.swift
//  CombineExtension
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Combine

public extension Collection where Element: Publisher {
    func concatenate() -> AnyPublisher<Element.Output, Element.Failure> {
        self.reduce(Empty().eraseToAnyPublisher()) { partialResult, other in
            return Publishers.Concatenate(prefix: partialResult, suffix: other)
                .eraseToAnyPublisher()
        }
    }
}
