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
