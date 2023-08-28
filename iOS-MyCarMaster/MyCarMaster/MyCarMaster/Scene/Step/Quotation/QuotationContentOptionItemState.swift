//
//  QuotationContentOptionItemState.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import Foundation

struct QuotationContentOptionItemState {
    let title: String
    let price: Int
}

protocol QuotationContentOptionItemStateConvertible {
    var quotationContentOptionItemState: QuotationContentOptionItemState { get }
}
