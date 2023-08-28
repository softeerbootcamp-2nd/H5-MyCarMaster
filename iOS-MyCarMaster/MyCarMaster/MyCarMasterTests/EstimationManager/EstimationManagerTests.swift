//
//  EstimationManagerTests.swift
//  MyCarMasterTests
//
//  Created by SEUNGMIN OH on 2023/08/22.
//

@testable import MyCarMaster
import XCTest

final class EstimationManagerTests: XCTestCase {

    var estimationManager: EstimationManageable!
    
    override func setUpWithError() throws {
        let initialEstimation = Estimation(selectedOptions: [], consideredOptions: [], selectedOptionsTotalPrice: 0, totalPrice: 0)
        estimationManager = EstimationManager(estimation: initialEstimation)
    }

    override func tearDownWithError() throws {
        estimationManager = nil
    }

    func testUpdateModel() throws {
        let model = Model(id: 0, name: "Palisade", imageURL: nil)
        
        estimationManager.update(\.model, value: model)
        
        let expectedModel = model
        let expectedTotalPrice = 0
        
        XCTAssertEqual(estimationManager.estimation.model, expectedModel)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
    }
    
    func testUpdateTrim() throws {
        let trimPrice = 10000
        let trim = Trim(model: "", id: 1, name: "", ratio: 0, description: "", price: trimPrice, imageURL: URL(fileURLWithPath: ""))
        
        XCTAssertEqual(estimationManager.estimation.trim, nil)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
        
        estimationManager.update(\.trim, value: trim)
        
        let expectedTrim = trim
        let expectedTotalPrice = trimPrice
        
        XCTAssertEqual(estimationManager.estimation.trim, expectedTrim)
        XCTAssertEqual(estimationManager.estimation.totalPrice, trimPrice)
    }

    func testUpdateOptionOnlySelect() throws {
        let option1 = Option(model: "", id: 1, category: "", name: "", price: 10000, ratio: 0, imgURL: nil, summary: nil, description: nil, tag: nil, subOptions: [])
        
        let option2 = Option(model: "", id: 1, category: "", name: "", price: 1000, ratio: 0, imgURL: nil, summary: nil, description: nil, tag: nil, subOptions: [])
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, [])
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
        
        estimationManager.update(\.selectedOptions, value: option1)
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, Set([option1]))
        XCTAssertEqual(estimationManager.estimation.selectedOptionsTotalPrice, 10000)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 10000)
        
        estimationManager.update(\.selectedOptions, value: option2)
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, Set([option1, option2]))
        XCTAssertEqual(estimationManager.estimation.selectedOptionsTotalPrice, 11000)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 11000)
    }
    
    func testUpdateOptionSelectAndConsider() throws {
        let option1 = Option(model: "", id: 1, category: "", name: "", price: 10000, ratio: 0, imgURL: nil, summary: nil, description: nil, tag: nil, subOptions: [])
        
        // 초기 상태
        XCTAssertEqual(estimationManager.estimation.selectedOptions, [])
        XCTAssertEqual(estimationManager.estimation.consideredOptions, [])
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
        
        // option1 선택
        estimationManager.update(\.selectedOptions, value: option1)
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, Set([option1]))
        XCTAssertEqual(estimationManager.estimation.consideredOptions, [])
        XCTAssertEqual(estimationManager.estimation.selectedOptionsTotalPrice, 10000)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 10000)
        
        // option1 고민해보기
        estimationManager.update(\.consideredOptions, value: option1)
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, [])
        XCTAssertEqual(estimationManager.estimation.consideredOptions, Set([option1]))
        XCTAssertEqual(estimationManager.estimation.selectedOptionsTotalPrice, 0)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
    }
    
    func testRemoveOption() throws {
        let option1 = Option(model: "", id: 1, category: "", name: "", price: 10000, ratio: 0, imgURL: nil, summary: nil, description: nil, tag: nil, subOptions: [])
        
        // 초기 상태
        XCTAssertEqual(estimationManager.estimation.selectedOptions, [])
        XCTAssertEqual(estimationManager.estimation.consideredOptions, [])
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
        
        // option1 선택
        estimationManager.update(\.selectedOptions, value: option1)
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, Set([option1]))
        XCTAssertEqual(estimationManager.estimation.consideredOptions, [])
        XCTAssertEqual(estimationManager.estimation.selectedOptionsTotalPrice, 10000)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 10000)
        
        // option1 제거
        estimationManager.remove(value: option1)
        
        XCTAssertEqual(estimationManager.estimation.selectedOptions, [])
        XCTAssertEqual(estimationManager.estimation.consideredOptions, [])
        XCTAssertEqual(estimationManager.estimation.selectedOptionsTotalPrice, 0)
        XCTAssertEqual(estimationManager.estimation.totalPrice, 0)
    }
}
