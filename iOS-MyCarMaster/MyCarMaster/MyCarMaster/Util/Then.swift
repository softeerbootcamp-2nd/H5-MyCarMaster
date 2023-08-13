//
//  Then.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import UIKit

protocol Then { }

extension Then where Self: AnyObject {

    @inlinable
    func then(_ block: (Self) throws -> Void) rethrows -> Self {
        try block(self)
        return self
    }
}

extension NSObject: Then { }
