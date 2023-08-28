//
//  QuotationContentOptionState.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import Foundation

struct QuotationContentOptionState {
    let selectedOptions: [Option]
    let consideredOptions: [Option]
    let selectedOptionsTotalPrice: Int
}

protocol QuotationContentOptionStateConvertible {
    var quotationContentOptionState: QuotationContentOptionState { get }
}
