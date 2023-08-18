//
//  Resource.swift
//  MCMResource
//
//  Created by SEUNGMIN OH on 2023/08/03.
//

import UIKit

public enum Resource {

    public enum Lottie {
        public static var launchScreenPath = bundle.path(forResource: "launchScreen", ofType: "json")!
    }

    public enum Typeface {
        typealias Head = RawFont.HyundaiSansHeadKR
        typealias Text = RawFont.HyundaiSansTextKR

        static let `default` = Self.bodyMedium1

        /// Hyundai Sans Head KR / 500(Medium) / Size: 36 / LineHeight 44.0 / Kern: -3%
        case displaySmall(TextStyle?)
        /// Hyundai Sans Head KR / 500(Medium) / Size: 32 / LineHeight 40.0 / Kern: -3%
        case headlineLarge(TextStyle?)
        /// Hyundai Sans Head KR / 500(Medium) / Size: 28 / LineHeight 36.0 / Kern: -3%
        case headlineMedium(TextStyle?)
        /// Hyundai Sans Head KR / 500(Medium) / Size: 24 / LineHeight 32.0 / Kern: -3%
        case headlineSmall(TextStyle?)
        /// Hyundai Sans Text KR / 700(Bold) / Size: 22 / LineHeight 28.0 / Kern: -3%
        case titleLarge1(TextStyle?)
        /// Hyundai Sans Text KR / 700(Bold) / Size: 20 / LineHeight 28.0 / Kern: -3%
        case titleLarge2(TextStyle?)
        /// Hyundai Sans Text KR / 500(Medium) / Size: 22 / LineHeight 32.0 / Kern: -3%
        case titleMedium1(TextStyle?)
        /// Hyundai Sans Text KR / 700(Bold) / Size: 16 / LineHeight 24.0 / Kern: -3%
        case titleMedium2(TextStyle?)
        /// Hyundai Sans Text KR / 700(Bold) / Size: 14 / LineHeight 20.0 / Kern: -3%
        case titleSmall(TextStyle?)
        /// Hyundai Sans Text KR / 500(Medium) / Size: 16 / LineHeight 24.0 / Kern: -3%
        case bodyLarge1(TextStyle?)
        /// Hyundai Sans Text KR / 500(Medium) / Size: 14 / LineHeight 20.0 / Kern: -3%
        case bodyMedium1(TextStyle?)
        /// Hyundai Sans Text KR / 500(Medium) / Size: 12 / LineHeight 16.0 / Kern: -3%
        case bodySmall1(TextStyle?)
        /// Hyundai Sans Text KR / 400(Regular) / Size: 16 / LineHeight 24.0 / Kern: -3%
        case bodyLarge2(TextStyle?)
        /// Hyundai Sans Text KR / 400(Regular) / Size: 14 / LineHeight 20.0 / Kern: -3%
        case bodyMedium2(TextStyle?)
        /// Hyundai Sans Text KR / 400(Regular) / Size: 13 / LineHeight 16.0 / Kern: -3%
        case bodySmall2(TextStyle?)
        /// Hyundai Sans Text KR / 400(Regular) / Size: 9 / LineHeight 16.0 / Kern: -3%
        case caption(TextStyle?)
        /// Hyundai Sans Text KR / 500(Medium) / Size: 10 / LineHeight 16.0 / Kern: -3%
        case buttonTitleSmall(TextStyle?)

        var textStyle: TextStyle {
            let defaultStyle = TextStyle(kern: -0.03)

            switch self {
            case let .displaySmall(additionalStyle): return defaultStyle.with(font: Head.medium.font(size: 36), lineHeight: 44).with(additionalStyle)
            case let .headlineLarge(additionalStyle): return defaultStyle.with(font: Head.medium.font(size: 32), lineHeight: 40).with(additionalStyle)
            case let .headlineMedium(additionalStyle): return defaultStyle.with(font: Head.medium.font(size: 28), lineHeight: 36).with(additionalStyle)
            case let .headlineSmall(additionalStyle): return defaultStyle.with(font: Head.medium.font(size: 24), lineHeight: 32).with(additionalStyle)
            case let .titleLarge1(additionalStyle): return defaultStyle.with(font: Text.bold.font(size: 22), lineHeight: 28).with(additionalStyle)
            case let .titleLarge2(additionalStyle): return defaultStyle.with(font: Text.bold.font(size: 20), lineHeight: 28).with(additionalStyle)
            case let .titleMedium1(additionalStyle): return defaultStyle.with(font: Text.medium.font(size: 22), lineHeight: 32).with(additionalStyle)
            case let .titleMedium2(additionalStyle): return defaultStyle.with(font: Text.bold.font(size: 16), lineHeight: 24).with(additionalStyle)
            case let .titleSmall(additionalStyle): return defaultStyle.with(font: Text.bold.font(size: 14), lineHeight: 20).with(additionalStyle)
            case let .bodyLarge1(additionalStyle): return defaultStyle.with(font: Text.medium.font(size: 16), lineHeight: 24).with(additionalStyle)
            case let .bodyMedium1(additionalStyle): return defaultStyle.with(font: Text.medium.font(size: 14), lineHeight: 20).with(additionalStyle)
            case let .bodySmall1(additionalStyle): return defaultStyle.with(font: Text.medium.font(size: 12), lineHeight: 16).with(additionalStyle)
            case let .bodyLarge2(additionalStyle): return defaultStyle.with(font: Text.regular.font(size: 16), lineHeight: 24).with(additionalStyle)
            case let .bodyMedium2(additionalStyle): return defaultStyle.with(font: Text.regular.font(size: 14), lineHeight: 20).with(additionalStyle)
            case let .bodySmall2(additionalStyle): return defaultStyle.with(font: Text.regular.font(size: 13), lineHeight: 16).with(additionalStyle)
            case let .caption(additionalStyle): return defaultStyle.with(font: Text.regular.font(size: 9), lineHeight: 16).with(additionalStyle)
            case let .buttonTitleSmall(additionalStyle): return defaultStyle.with(font: Text.medium.font(size: 10), lineHeight: 16).with(additionalStyle)
            }
        }
    }

    public enum Color {
        /// #222222
        public static let black = RawColor.black.color as UIColor
        /// #FFFFFF
        public static let white = RawColor.white.color as UIColor
        /// #C5C9D2
        public static let coolGrey1 = RawColor.coolGrey1.color as UIColor
        /// #81899E
        public static let coolGrey2 = RawColor.coolGrey2.color as UIColor
        /// #F6F6F6
        public static let grey1 = RawColor.grey1.color as UIColor
        /// #DDDDDD
        public static let grey2 = RawColor.grey2.color as UIColor
        /// #7B7B7B
        public static let grey3 = RawColor.grey3.color as UIColor
        /// #E7ECF9
        public static let navyBlue1 = RawColor.navyBlue1.color as UIColor
        /// #C6D2F0
        public static let navyBlue2 = RawColor.navyBlue2.color as UIColor
        /// #D2D9Ec
        public static let navyBlue3 = RawColor.navyBlue3.color as UIColor
        /// #96A9DC
        public static let navyBlue4 = RawColor.navyBlue4.color as UIColor
        /// #1A3276
        public static let navyBlue5 = RawColor.navyBlue5.color as UIColor
        /// #FAF2ED
        public static let gold1 = RawColor.gold1.color as UIColor
        /// #F0DCCF
        public static let gold2 = RawColor.gold2.color as UIColor
        /// #9B6D54
        public static let gold3 = RawColor.gold3.color as UIColor
        /// #007FA8
        public static let activeBlue = RawColor.activeBlue.color as UIColor
    }
}

extension Resource {
    private static let bundle = Bundle(identifier: "com.minios.MCMResource")!
}
