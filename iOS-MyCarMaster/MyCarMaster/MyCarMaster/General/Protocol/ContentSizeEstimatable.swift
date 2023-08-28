//
//  ContentSizeEstimatable.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import Foundation

protocol ContentSizeEstimatable: AnyObject {
    static var intrinsicContentSize: CGSize { get }
}
