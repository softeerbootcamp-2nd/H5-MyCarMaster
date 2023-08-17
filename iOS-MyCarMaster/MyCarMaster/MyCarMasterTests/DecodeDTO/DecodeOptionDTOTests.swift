//
//  DecodeOptionDTO.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/17.
//

@testable import MyCarMaster
import XCTest

final class DecodeOptionDTOTests: XCTestCase {

    override func setUpWithError() throws {

        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testDecodingSimpleOptionDTO() throws {
        let data = """
        {
            "code": 2000,
            "message": "요청에 성공했습니다.",
            "result": {
                "options": [
                    {
                        "id": 90,
                        "category": "SAFE",
                        "name": "빌트인 캠(보조배터리 포함)",
                        "price": 690000,
                        "ratio": 60,
                        "imgUrl": "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/built-in-cam.png",
                        "summary": "빌트인 캠'을 통해 방금 촬영된 운전 영상을 어플로 바로 확인할 수 있어요.",
                        "description": "빌트인 적용된 영상기록장치로, 내비게이션 화면을 통해 영상 확인 및 앱 연동을 통해 영상 확인 및 SNS 공유가 가능합니다.",
                        "tag": null,
                        "subOptions": [
                            {
                                "name": "후방 주차 충돌방지 보조",
                                "imgUrl": "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/rear-parking-collision-prevention-assistance.png",
                                "description": "주차 또는 출차 시 저속 후진 중 후방카메라와 센서로 정후면에 위치한 보행자 및 장애물과의 충돌이 예상되면 운전자에게 경고하고 차량의 제동을 제어하여 충돌방지를 보조합니다."
                            },
                        ]
                    },

                ],
                "exclusiveTags": [
                    "N Performance"
                ]
            }
        }
        """.data(using: .utf8)
        guard let data else {
            XCTFail("Invalid Data")
            return
        }

        guard let optionDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.options else {
            XCTFail("Decoding Main Error")
            return
        }

        let dataList = optionDTOList.map { Option($0) }
        print(dataList)
    }

    func testDecodingComplexOptionDTO() throws {
        let data = """
        {
            "code": 2000,
            "message": "요청에 성공하셨습니다.",
            "result": {
                "options": [
                    {
                        "id": 125,
                        "category": "SAFE",
                        "name": "주차보조 시스템 1",
                        "price": 1090000,
                        "ratio": 45,
                        "imgUrl": "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/rear-collision-alert.png",
                        "summary": null,
                        "description": null,
                        "tag": null,
                        "subOptions": [
                            {
                                "name": "후측방 충돌 경고(주행)",
                                "imgUrl": "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/rear-collision-alert.png",
                                "description": "차로 변경을 위하여 방향지시등 스위치 조작 시, 후측방 충돌 위험이 감지되면 경고를 해줍니다."
                            },
                            {
                                "name": "후측방 충돌방지 보조(전진 출차)",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "평행 주차상태에서 전진 출차 중, 후측방 차량과 충돌 위험이 감지되면 자동으로 제동을 도와줍니다."
                            },
                            {
                                "name": "안전 하차 보조",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "정차 후 탑승자가 차에서 내리려고 도어를 열 때, 후측방에서 접근하는 차량이 감지되면 경고를 해줍니다. 또한 전자식 차일드 락이 작동하여 문이 열리지 않도록 도와줍니다."
                            },
                            {
                                "name": "후방 교차 충돌방지 보조",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "후진 출차 시 후방 교차 차량을 감지하여 운전자에게 경고하고 필요 시에는 브레이크 제어를 통해 후방 교차 충돌방지를 보조합니다."
                            },
                            {
                                "name": "후방 주차 충돌방지 보조",
                                "imgUrl": "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/rear-parking-collision-prevention-assistance.png",
                                "description": "주차 또는 출차 시 저속 후진 중 후방카메라와 센서로 정후면에 위치한 보행자 및 장애물과의 충돌이 예상되면 운전자에게 경고하고 차량의 제동을 제어하여 충돌방지를 보조합니다."
                            },
                            {
                                "name": "원격 스마트 주차 보조",
                                "imgUrl": "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/remote-smart-parking-assistance.png",
                                "description": "주차 보조 기능을 활성화 한 후 주차공간을 발견하게 되면 차량 내 안내에 따라 하차한 다음, 스마트키의 작동 버튼을 누르고만 있으면 차가 스스로 주차합니다. 직각주차 및 평행주차 모두 가능하며, 운전자 탑승 시에도 차량 내부의 작동 버튼을 누르고 있으면 자동 주차 보조를 지원합니다."
                            },
                            {
                                "name": "서라운드 뷰 모니터",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "차량 앞/뒤/좌/우 360도 모든 상황을 AVN화면을 통해 볼 수 있는 장치로 고화질 카메라 및 디지털 영상 전송 방식을 적용하여 영상 경계선 없이 선명하고 깨끗한 화질을 제공합니다."
                            }
                        ]
                    },
                    {
                        "id": 126,
                        "category": "SAFE",
                        "name": "컴포트 1",
                        "price": 790000,
                        "ratio": 40,
                        "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                        "summary": null,
                        "description": null,
                        "tag": null,
                        "subOptions": [
                            {
                                "name": "천연가죽 시트(블랙)",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "팰리세이드는 옵션에 따라 인조가죽, 가죽, 나파가죽, 퀼팅 나파가죽 시트를 선택할 수 있습니다. - 인조가죽 시트 : 합성섬유를 이용하여 가죽의 질감을 구현한 인조가죽으로 제작된 시트입니다. - 가죽 시트 : 실제 가죽으로 제작되어 편안하며 고급스러운 착좌감을 제공합니다. - 나파가죽 시트 : 가죽 표면을 코팅처리하여 가죽의 내구성은 높이면서도 부드러운 감촉을 선사하는 시트입니다."
                            },
                            {
                                "name": "운전석 전동시트(10way, 4way 럼버서포트, 쿠션 익스텐션, 자세 메모리 시스템)",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "운전석의 시트 포지션을 조정하여 운전자의 체형에 맞는 편안한 자세를 유지할 수 있도록 돕는 기능입니다. - 10way 전동시트 : 운전석 좌하단에 위치한 조작부로 8방향으로 조절 기능(시트백 기울기, 시트 앞/뒤 이동, 쿠션부 앞/뒤 높이 조절)과 허리 지지대 조절 기능을 전동 방식으로 조절합니다. - 4way 럼버서포트 : 허리 지지대 조절 기능을 4 방향으로 조절합니다. - 쿠션 익스텐션 : 운전자의 허벅지 길이에 맞게 시트 하단부를 조절하는 쿠션"
                            },
                            {
                                "name": "전동식 틸트 & 텔레스코픽 스티어링 휠",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "조절 스위치를 이용하여 스티어링 휠의 높낮이와 전/후 위치를 조절할 수 있습니다."
                            }
                        ]
                    },
                    {
                        "id": 127,
                        "category": "CONVENIENCE",
                        "name": "컨비니언스",
                        "price": 840000,
                        "ratio": 60,
                        "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                        "summary": null,
                        "description": null,
                        "tag": null,
                        "subOptions": [
                            {
                                "name": "발수 도어(1열)",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "빗물 맺힘이나 서리, 성에 등을 막아주는 발수 적용 유리를 앞도어에 장착하여 운전자의 시계를 확보합니다."
                            },
                            {
                                "name": "스마트 파워 테일게이트",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "스위치 및 스마트키 버튼으로 테일게이트 개방이 가능하며, 설정을 통해 개폐 속도 조절 및 열림 높이 조절이 가능합니다."
                            },
                            {
                                "name": "스마트폰 무선충전",
                                "imgUrl": "https://h5-test-bucket.s3.ap-northeast-2.amazonaws.com/1.png",
                                "description": "중앙 콘솔에 휴대폰 무선 충전을 위한 시스템이 적용되어 있습니다. 또한 엔진에 시동을 끈 후 충전 패드에 휴대폰이 놓여있는 상태에서 운전석 또는 동승석 도어를 열면 게시판에 \'휴대폰이 무선 충전기에 있습니다\'라는 경고문 및 경고음(음성안내 적용 차량)으로 알려줍니다.\\n* 무선 충전 시스템은 Qi를 지원하는 휴대폰 한 대를 대상으로 무선충전을 지원하며, Qi를 지원하는 휴대폰은 휴대폰 제조사를 통해 확인 가능합니다."
                            }
                        ]
                    }
                ],
                "exclusiveTags": [
                    "N Performance"
                ]
            }
        }
        """.data(using: .utf8)

        guard let data else {
            XCTFail("Invalid Data")
            return
        }

        guard let optionDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.options else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = optionDTOList.map { Option($0) }
    }

    func testDecodingRealOptionDTO() throws {
        guard let data = fetchData(from: "OptionDTO") else {
            XCTFail("Invalid Data")
            return
        }

        guard let optionDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.options else {
            XCTFail("Decoding Error")
            return
        }

        let dataList = optionDTOList.map { Option($0) }
    }
}