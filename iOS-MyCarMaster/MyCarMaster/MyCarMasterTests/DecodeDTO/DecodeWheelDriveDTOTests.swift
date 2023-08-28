//
//  DecodeWheelDriveDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

@testable import MyCarMaster
import XCTest

final class DecodeWheelDriveDTOTests: XCTestCase {

    func testDecodeReal() throws {
        guard let data = fetchData(from: "WheelDriveDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let DTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.wheelDrives else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = DTOList.map { WheelDrive($0) }

        let expectedDataList = [
            WheelDrive(
                model: "펠리세이드",
                id: 1,
                name: "2WD",
                description: "엔진 동력이 전후륜 중 한쪽으로만 전달돼 움직입니다. 차체가 가벼워 연료 효율이 높습니다.",
                ratio: 60,
                price: 0,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/wheel-drive/2-wd.png")!
            ),
            WheelDrive(
                model: "펠리세이드",
                id: 2,
                name: "4WD",
                description: "전자식 상시 4륜 구동 시스템으로 환경에 맞춰 구동력을 자동배분해 안전성을 높입니다.",
                ratio: 40,
                price: 2370000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/wheel-drive/4-wd.png")!
            )
        ]

        for (data, expected) in zip(dataList, expectedDataList) {
            XCTAssertEqual(data, expected)
        }
    }
}
