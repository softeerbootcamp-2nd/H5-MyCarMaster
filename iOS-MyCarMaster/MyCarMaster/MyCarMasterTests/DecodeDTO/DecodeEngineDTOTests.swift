//
//  DecodeEngineDTOTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

@testable import MyCarMaster
import XCTest

final class DecodeEngineDTOTests: XCTestCase {

    func testDecodeReal() throws {
        guard let data = fetchData(from: "EngineDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let DTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.engines else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = DTOList.map { Engine($0) }

        let expectedDataList = [
            Engine(
                model: "펠리세이드",
                name: "디젤 2.2 엔진",
                ratio: 60,
                description: "우수한 가속 성능으로 안정적이고 엔진의 진동이 적어 \\n조용한 드라이빙이 가능합니다.",
                fuelMin: 8.0,
                fuelMax: 9.2,
                power: 295,
                toque: 36.2,
                price: 0,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/engine/diesel.png")
            ),
            Engine(
                model: "펠리세이드",
                name: "가솔린 3.8 V6 엔진",
                ratio: 40,
                description: "높은 토크로 파워풀한 드라이빙이 가능하고 연비 효율이 우수합니다.",
                fuelMin: 9.0, fuelMax: 10.2,
                power: 320,
                toque: 47.3,
                price: 1480000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/engine/gasoline.png")
            )
        ]

        XCTAssertEqual(dataList, expectedDataList)
    }
}
