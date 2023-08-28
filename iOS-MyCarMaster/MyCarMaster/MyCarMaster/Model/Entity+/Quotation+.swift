//
//  Quotation+.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import Foundation

extension Quotation: QuotationContentOptionStateConvertible {
    var quotationContentOptionState: QuotationContentOptionState {
        .init(
            selectedOptions: self.selectedOptions,
            consideredOptions: self.consideredOptions,
            selectedOptionsTotalPrice: self.selectedOptionsTotalPrice
        )
    }
}

extension Quotation {
    init(_ estimation: Estimation) {
        guard let model = estimation.model,
              let trim = estimation.trim,
              let engine = estimation.engine,
              let wheelDrive = estimation.wheelDrive,
              let bodyType = estimation.bodyType,
              let exterior = estimation.exterior,
              let interior = estimation.interior
        else {
            fatalError("정상적인 과정이라면 일어날 수 없는 오류")
        }

        self.model = model
        self.trim = trim
        self.engine = engine
        self.wheelDrive = wheelDrive
        self.bodyType = bodyType
        self.exterior = exterior
        self.interior = interior
        self.selectedOptions = Array(estimation.selectedOptions)
        self.consideredOptions = Array(estimation.consideredOptions)
        self.selectedOptionsTotalPrice = estimation.selectedOptionsTotalPrice
        self.totalPrice = estimation.totalPrice
    }
}
