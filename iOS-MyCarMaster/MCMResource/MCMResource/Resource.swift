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

    public enum Color {
        public static let black = RawColor.black.color as UIColor
        public static let white = RawColor.white.color as UIColor
        public static let coolGrey1 = RawColor.coolGrey1.color as UIColor
        public static let coolGrey2 = RawColor.coolGrey2.color as UIColor
        public static let grey1 = RawColor.grey1.color as UIColor
        public static let grey2 = RawColor.grey2.color as UIColor
        public static let grey3 = RawColor.grey3.color as UIColor
        public static let navyBlue1 = RawColor.navyBlue1.color as UIColor
        public static let navyBlue2 = RawColor.navyBlue2.color as UIColor
        public static let navyBlue3 = RawColor.navyBlue3.color as UIColor
        public static let navyBlue4 = RawColor.navyBlue4.color as UIColor
        public static let navyBlue5 = RawColor.navyBlue5.color as UIColor
        public static let gold1 = RawColor.gold1.color as UIColor
        public static let gold2 = RawColor.gold2.color as UIColor
    }
}
