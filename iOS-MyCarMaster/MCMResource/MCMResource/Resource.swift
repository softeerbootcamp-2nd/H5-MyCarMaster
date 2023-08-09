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
        case displaySmall
        /// Hyundai Sans Head KR / 500(Medium) / Size: 32 / LineHeight 40.0 / Kern: -3%
        case headlineLarge
        /// Hyundai Sans Head KR / 500(Medium) / Size: 28 / LineHeight 36.0 / Kern: -3%
        case headlineMedium
        /// Hyundai Sans Head KR / 500(Medium) / Size: 24 / LineHeight 32.0 / Kern: -3%
        case headlineSmall
        /// Hyundai Sans Text KR / 700(Bold) / Size: 22 / LineHeight 28.0 / Kern: -3%
        case titleLarge1
        /// Hyundai Sans Text KR / 700(Bold) / Size: 20 / LineHeight 28.0 / Kern: -3%
        case titleLarge2
        /// Hyundai Sans Text KR / 500(Medium) / Size: 22 / LineHeight 32.0 / Kern: -3%
        case titleMedium1
        /// Hyundai Sans Text KR / 700(Bold) / Size: 16 / LineHeight 24.0 / Kern: -3%
        case titleMedium2
        /// Hyundai Sans Text KR / 700(Bold) / Size: 14 / LineHeight 20.0 / Kern: -3%
        case titleSmall
        /// Hyundai Sans Text KR / 500(Medium) / Size: 16 / LineHeight 24.0 / Kern: -3%
        case bodyLarge1
        /// Hyundai Sans Text KR / 500(Medium) / Size: 14 / LineHeight 20.0 / Kern: -3%
        case bodyMedium1
        /// Hyundai Sans Text KR / 500(Medium) / Size: 12 / LineHeight 16.0 / Kern: -3%
        case bodySmall1
        /// Hyundai Sans Text KR / 400(Regular) / Size: 16 / LineHeight 24.0 / Kern: -3%
        case bodyLarge2
        /// Hyundai Sans Text KR / 400(Regular) / Size: 14 / LineHeight 20.0 / Kern: -3%
        case bodyMedium2
        /// Hyundai Sans Text KR / 400(Regular) / Size: 13 / LineHeight 16.0 / Kern: -3%
        case bodySmall2
        /// Hyundai Sans Text KR / 400(Regular) / Size: 9 / LineHeight 16.0 / Kern: -3%
        case caption

        var textStyle: TextStyle {
            let defaultStyle = TextStyle(kern: -0.03)

            switch self {
            case .displaySmall: return defaultStyle.with(font: Head.medium.font(size: 36), lineHeight: 44)
            case .headlineLarge: return defaultStyle.with(font: Head.medium.font(size: 32), lineHeight: 40)
            case .headlineMedium: return defaultStyle.with(font: Head.medium.font(size: 28), lineHeight: 36)
            case .headlineSmall: return defaultStyle.with(font: Head.medium.font(size: 24), lineHeight: 32)
            case .titleLarge1: return defaultStyle.with(font: Text.bold.font(size: 22), lineHeight: 28)
            case .titleLarge2: return defaultStyle.with(font: Text.bold.font(size: 20), lineHeight: 28)
            case .titleMedium1: return defaultStyle.with(font: Text.medium.font(size: 22), lineHeight: 32)
            case .titleMedium2: return defaultStyle.with(font: Text.bold.font(size: 16), lineHeight: 24)
            case .titleSmall: return defaultStyle.with(font: Text.bold.font(size: 14), lineHeight: 20)
            case .bodyLarge1: return defaultStyle.with(font: Text.medium.font(size: 16), lineHeight: 24)
            case .bodyMedium1: return defaultStyle.with(font: Text.medium.font(size: 14), lineHeight: 20)
            case .bodySmall1: return defaultStyle.with(font: Text.medium.font(size: 12), lineHeight: 16)
            case .bodyLarge2: return defaultStyle.with(font: Text.regular.font(size: 16), lineHeight: 24)
            case .bodyMedium2: return defaultStyle.with(font: Text.regular.font(size: 14), lineHeight: 20)
            case .bodySmall2: return defaultStyle.with(font: Text.regular.font(size: 13), lineHeight: 16)
            case .caption: return defaultStyle.with(font: Text.regular.font(size: 9), lineHeight: 16)
            }
        }
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

extension Resource {
    private static let bundle = Bundle(identifier: "com.minios.MCMResource")!
}
