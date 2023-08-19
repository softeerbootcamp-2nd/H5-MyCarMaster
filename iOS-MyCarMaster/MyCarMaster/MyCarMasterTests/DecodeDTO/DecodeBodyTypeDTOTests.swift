//
//  DecodeBodyTypeDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

@testable import MyCarMaster
import XCTest

final class DecodeBodyTypeDTOTests: XCTestCase {

    func testDecodeReal() throws {
        guard let data = fetchData(from: "BodyTypeDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let DTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.bodyTypes else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = DTOList.map { BodyType($0) }

        let expectedDataList = [
            BodyType(
                model: "펠리세이드"
                , name: "7인승",
                description: "8인승 시트에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.",
                ratio: 50,
                price: 0,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/body-type/7-seat.png")
            ),
            BodyType(
                model: "펠리세이드",
                name: "8인승",
                description: "1열 2명, 2열 3명, 3열 3명이 탑승할 수 있는 구조로, 많은 인원이 탑승할 수 있습니다.",
                ratio: 50,
                price: 1000000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/body-type/8-seat.png")
            )
        ]

        XCTAssertEqual(dataList, expectedDataList)
    }
}
