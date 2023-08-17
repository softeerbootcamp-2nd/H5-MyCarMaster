//
//  FetchFromBundle.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/17.
//

import Foundation

func fetchData(from jsonFileID: String) -> Data? {
    guard let fileURL = Bundle.main.url(forResource: jsonFileID, withExtension: "json") else { return nil }
    return try? Data(contentsOf: fileURL)
}
