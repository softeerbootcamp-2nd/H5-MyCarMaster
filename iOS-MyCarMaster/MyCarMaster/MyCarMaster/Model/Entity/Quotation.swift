//
//  Quotation.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import Foundation

struct Quotation: Hashable {
    var model: Model?
    var trim: Trim?
    var engine: Engine?
    var wheelDrive: WheelDrive?
    var bodyType: BodyType?
    var exterior: Exterior?
    var interior: Interior?
    var selectedOptions: Set<Option>?
    var consideredOptions: Set<Option>?
    var selectedOptionsTotalPrice: Int
    var totalPrice: Int
}
