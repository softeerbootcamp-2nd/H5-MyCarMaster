//
//  DecodeInteriorDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

@testable import MyCarMaster
import XCTest

final class DecodeInteriorDTOTests: XCTestCase {

    func testDecodeReal() throws {
        guard let data = fetchData(from: "InteriorDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let DTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.interiors else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = DTOList.map { Interior($0) }

        let expectedDataList = [
            Interior(
                model: "펠리세이드",
                id: 1,
                name: "퀼팅천연 (블랙)",
                price: 0,
                ratio: 60,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/quilting-natural-black.png")!,
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/car/quilting-natural-black.png")!
            ),
            Interior(
                model: "펠리세이드",
                id: 2,
                name: "쿨그레이",
                price: 0,
                ratio: 40,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/cool-gray.png")!,
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/car/cool-gray.png")!
            )
        ]

        for (data, expected) in zip(dataList, expectedDataList) {
            XCTAssertEqual(data, expected)
        }
    }
}
