//
//  Quotation.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import Foundation

struct Quotation: Hashable {
    let trim: Trim
    let engine: Engine
    let wheelDrive: WheelDrive
    let bodyType: BodyType
    let exterior: Exterior
    let interior: Interior
    let option: Option
}
