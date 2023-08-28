//
//  TextStyle.swift
//  MCMResource
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit

public struct TextStyle {

    public let font: UIFont?
    public let lineSpacing: CGFloat?
    public let lineHeight: CGFloat?
    public let kern: CGFloat?
    public let alignment: NSTextAlignment?
    public let lineBreakMode: NSLineBreakMode?
    public let textColor: UIColor?

    public init(font: UIFont? = nil,
                lineSpacing: CGFloat? = nil,
                lineHeight: CGFloat? = nil,
                kern: CGFloat? = nil,
                alignment: NSTextAlignment? = nil,
                lineBreakMode: NSLineBreakMode? = nil,
                textColor: UIColor? = nil
    ) {
        self.font = font
        self.lineSpacing = lineSpacing
        self.lineHeight = lineHeight
        self.kern = kern
        self.alignment = alignment
        self.lineBreakMode = lineBreakMode
        self.textColor = textColor
    }

    public var attributes: [NSAttributedString.Key: Any] {
        var attributes: [NSAttributedString.Key: Any] = [:]

        attributes[NSAttributedString.Key.font] = font
        if let kern { attributes[NSAttributedString.Key.kern] = kern }
        if let textColor { attributes[NSAttributedString.Key.foregroundColor] = textColor }

        let paragraphStyle = NSMutableParagraphStyle()
        if let lineSpacing  { paragraphStyle.lineSpacing = lineSpacing }
        if let lineHeight {
            paragraphStyle.minimumLineHeight = lineHeight
            paragraphStyle.maximumLineHeight = lineHeight
            // 지정한 lineHeight내에서 글자를 중앙으로
            if #available(iOS 16.4, *) {
                attributes[NSAttributedString.Key.baselineOffset] = (lineHeight - (font?.lineHeight ?? 0)) / 2
            } else {
                attributes[NSAttributedString.Key.baselineOffset] = (lineHeight - (font?.lineHeight ?? 0)) / 4
            }
        }
        if let alignment { paragraphStyle.alignment = alignment }
        if let lineBreakMode { paragraphStyle.lineBreakMode = lineBreakMode }

        attributes[NSAttributedString.Key.paragraphStyle] = paragraphStyle

        return attributes
    }
}

extension TextStyle {
    public func with(
            font: UIFont?,
            lineSpacing: CGFloat? = nil,
            lineHeight: CGFloat? = nil,
            kern: CGFloat? = nil,
            alignment: NSTextAlignment? = nil,
            lineBreakMode: NSLineBreakMode? = nil,
            textColor: UIColor? = nil
    ) -> TextStyle {
        return TextStyle(
            font: font ?? self.font,
            lineSpacing: lineSpacing ?? self.lineSpacing,
            lineHeight: lineHeight ?? self.lineHeight,
            kern: kern ?? self.kern,
            alignment: alignment ?? self.alignment,
            lineBreakMode: lineBreakMode ?? self.lineBreakMode,
            textColor: textColor ?? self.textColor
        )
    }
    
    /// 지정한 서체에 추가적인 style을 지정해 주고 싶을 때 이 메서드를 사용합니다.
    /// 서체를 구별하는데 꼭 필요한 `font`, `lineHeight`은 변경할 수 없습니다.
    public func with(_ additionalStyle: TextStyle?) -> TextStyle {
        guard let additionalStyle else { return self }
        
        return TextStyle(
            font: self.font,
            lineSpacing: additionalStyle.lineSpacing ?? self.lineSpacing,
            lineHeight: self.lineHeight,
            kern: additionalStyle.kern ?? self.kern,
            alignment: additionalStyle.alignment ?? self.alignment,
            lineBreakMode: additionalStyle.lineBreakMode ?? self.lineBreakMode,
            textColor: additionalStyle.textColor ?? self.textColor
        )
    }
}

