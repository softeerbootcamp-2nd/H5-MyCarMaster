//
//  DecodeTrimDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/17.
//

@testable import MyCarMaster
import XCTest

final class DecodeTrimDTOTests: XCTestCase {

    func testDecodeReal() throws {
        guard let data = fetchData(from: "TrimDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let DTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.trims else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = DTOList.map { Trim($0) }

        let expectedDataList = [
            Trim(
                model: "펠리세이드",
                name: "Le Blanc",
                ratio: 0,
                description: "인기있는 주요 기능을 포함한 가성비 트림",
                price: 43460000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/trim/leblanc.png")
            ),
            Trim(
                model: "펠리세이드",
                name: "Exclusive",
                ratio: 10,
                description: "실용적인 기본 기능을 갖춘 베이직 트림",
                price: 40440000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/trim/exclusive.png")
            ),
            Trim(
                model: "펠리세이드",
                name: "Prestige",
                ratio: 15,
                description: "고급스러운 기능을 갖춘 프리미엄 트림",
                price: 47720000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/trim/prestige.png")
            ),
            Trim(
                model: "펠리세이드",
                name: "Calligraphy",
                ratio: 75,
                description: "최고만을 원하는 고객을 위한 최상급 트림",
                price: 52540000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/trim/calligraphy.png")
            )
        ]

        XCTAssertEqual(dataList, expectedDataList)
    }
}
