//
//  ImageUrlReplacingTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

@testable import MyCarMaster
import XCTest

final class ImageUrlReplacingTests: XCTestCase {
   
    func testReplacing() throws {
        let origin = "https://beta.my-car-master.shop/images/palisade/trim/calligraphy.png"
        let expected = "https://beta.my-car-master.shop/images/palisade/trim/calligraphy_ios.png"
        
        XCTAssertEqual(origin.iOS, expected)
    }
}
