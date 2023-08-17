//
//  DecodeTrimDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/17.
//

@testable import MyCarMaster
import XCTest

final class DecodeTrimDTOTests: XCTestCase {

    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() throws {
        guard let data = fetchData(from: "TrimDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let trimDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.trims else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = trimDTOList.map { Trim($0) }
        print(dataList)
    }

    func testPerformanceExample() throws {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }
}
