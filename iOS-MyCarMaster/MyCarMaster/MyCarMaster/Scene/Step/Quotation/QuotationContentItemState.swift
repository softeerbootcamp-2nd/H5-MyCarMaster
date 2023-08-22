//
//  QuotationContentItemState.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import Foundation

struct QuotationContentItemState {
    let titleDescription: String
    let title: String
    let imageURL: URL?
    let price: Int
    let isAdditionalPrice: Bool
}

protocol QuotationContentItemStateConvertible {
    var quotationContentItemState: QuotationContentItemState { get }
}
