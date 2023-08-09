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

    public init(font: UIFont? = nil,
                lineSpacing: CGFloat? = nil,
                lineHeight: CGFloat? = nil,
                kern: CGFloat? = nil,
                alignment: NSTextAlignment? = nil,
                lineBreakMode: NSLineBreakMode? = nil
    ) {
        self.font = font
        self.lineSpacing = lineSpacing
        self.lineHeight = lineHeight
        self.kern = kern
        self.alignment = alignment
        self.lineBreakMode = lineBreakMode
    }

    public var attributes: [NSAttributedString.Key: Any] {
        var attributes: [NSAttributedString.Key: Any] = [:]

        attributes[NSAttributedString.Key.font] = font
        if let kern { attributes[NSAttributedString.Key.kern] = kern }

        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.setParagraphStyle(.default)
        if let lineSpacing  { paragraphStyle.lineSpacing = lineSpacing }
        if let lineHeight {
            paragraphStyle.minimumLineHeight = lineHeight
            paragraphStyle.maximumLineHeight = lineHeight
            // 지정한 lineHeight내에서 글자를 중앙으로
            attributes[NSAttributedString.Key.baselineOffset] = (lineHeight - (font?.lineHeight ?? 0)) / 4
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
            lineBreakMode: NSLineBreakMode? = nil
    ) -> TextStyle {
        return TextStyle(
            font: font ?? self.font,
            lineSpacing: lineSpacing ?? self.lineSpacing,
            lineHeight: lineHeight ?? self.lineHeight,
            kern: kern ?? self.kern,
            alignment: alignment ?? self.alignment,
            lineBreakMode: lineBreakMode ?? self.lineBreakMode
        )
    }
}

