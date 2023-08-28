//
//  Dependency.swift
//  MCMNetwork
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import Foundation

public enum Dependency {

    public static let serverURL = valueFrom(plist: "Dependency", forKey: "serverURL") as! String
}

extension Dependency {

    private static let bundle = Bundle.main

    private static func valueFrom(plist: String, forKey key: String) -> Any? {
        guard let plistPath = bundle.path(forResource: plist, ofType: "plist") else { fatalError("\(plist).plist가 존재하지 않습니다.") }

        do {
            if let xml = FileManager.default.contents(atPath: plistPath),
               let plistData = try PropertyListSerialization.propertyList(from: xml, format: nil) as? [String: Any] {
                return plistData[key]
            }
        } catch {
            fatalError("\(plist).plist를 읽는 도중 에러가 발생했습니다: \(error)")
        }

        return nil
    }
}
