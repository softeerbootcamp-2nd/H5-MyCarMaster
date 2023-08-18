//
//  DecodeExteriorDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

@testable import MyCarMaster
import XCTest

final class DecodeExteriorDTOTests: XCTestCase {

    func testDecodeReal() throws {
        guard let data = fetchData(from: "ExteriorDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let DTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.exteriors else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = DTOList.map { Exterior($0) }

        let expectedDataList = [
            Exterior(
                model: "펠리세이드",
                name: "어비스 블랙 펄",
                price: 0,
                ratio: 30,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/abyss.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/abyss.png")
            ),
            Exterior(
                model: "펠리세이드",
                name: "쉬버링 실버 메탈릭",
                price: 0,
                ratio: 20,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/silver.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/silver.png")),
            Exterior(
                model: "펠리세이드",
                name: "문라이트 블루 펄",
                price: 0,
                ratio: 10,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/blue.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/blue.png")),
            Exterior(
                model: "펠리세이드",
                name: "가이아 브라운 펄",
                price: 0,
                ratio: 5,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/brown.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/brown.png")
            ),
            Exterior(
                model: "펠리세이드",
                name: "그라파이트 그레이 메탈릭", price: 0,
                ratio: 5,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/gray.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/gray.png")),
            Exterior(
                model: "펠리세이드",
                name: "크리미 화이트 펄", price: 100000,
                ratio: 30,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/white.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/white.png")
            )
        ]

        XCTAssertEqual(dataList, expectedDataList)
    }
}
