//
//  Resource.swift
//  MCMResource
//
//  Created by SEUNGMIN OH on 2023/08/03.
//

import UIKit

public enum Resource {
    private static let bundle = Bundle(identifier: "com.minios.MCMResource")!

    public enum Lottie {
        public static var launchScreenPath = bundle.path(forResource: "launchScreen", ofType: "json")!
    }

    public enum Font {
        
    }
}
