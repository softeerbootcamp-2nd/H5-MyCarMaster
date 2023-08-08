//
//  Task.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public enum Task {
    case requestPlain

    case requestData(Data)

    case requestParameters(parameters: [String: Any])

    case downloadContent
}

// 다운로드 된 이미지를 특정 위치에 옮기는 행위가 필요할까?
// 필요가 없다면 아래 내용은 지워도 될 것 같다.
public typealias Destination = (_ temporaryURL: URL, _ response: HTTPURLResponse) -> (destinationURL: URL, options: Options)

public struct Options: OptionSet {
    /// Specifies that intermediate directories for the destination URL should be created.
    public static let createIntermediateDirectories = Options(rawValue: 1 << 0)
    /// Specifies that any previous file at the destination `URL` should be removed.
    public static let removePreviousFile = Options(rawValue: 1 << 1)

    public let rawValue: Int

    public init(rawValue: Int) {
        self.rawValue = rawValue
    }
}
