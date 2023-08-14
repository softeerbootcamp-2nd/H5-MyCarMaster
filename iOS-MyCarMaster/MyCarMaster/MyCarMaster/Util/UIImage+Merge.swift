//
//  UIImage+Merge.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import UIKit

extension UIImage {

    enum Merge {
        static var totalCost: CFAbsoluteTime = .zero
    }

    /// 두 개의 Image를 합성한다. 크기가 다를 경우 바닥면을 기준으로 합쳐진다.
    /// - Parameters:
    ///   - baseImage: 두 개의 Image중, 가장 아래에 먼저 그려지는 Image
    ///   - interpolationQuality: 효과가 있는것은 정확히 모르겠지만, quality가 작을 수록 부담이 적다.
    ///   - verbose: true일 경우, 합성에 걸린 시간을 ms단위로 출력한다.
    /// - Returns: 합성된 Image
    ///
    /// Interpolation quality is a graphics state parameter that provides a hint for the level of quality
    ///  to use for image interpolation (for example, when scaling the image).
    ///   Not all contexts support all interpolation quality levels.
    ///
    /// 만약 여러개의 이미지를 합성하는 비용을 계산하고 싶다면 아래와 같이 작성하자.
    /// ```swift
    /// UIImage.Merge.totalMergingCost = 0
    /// for image in images {
    ///     image.drawAtBottom(ofImage: baseImage, verbose: true)
    /// }
    /// ```
    func drawAtBottom(
        ofImage baseImage: UIImage,
        interpolationQuality: CGInterpolationQuality = .low,
        verbose: Bool = false
    ) -> UIImage {
        let start = CFAbsoluteTimeGetCurrent()
        let container = CGRect(origin: .zero,
                               size: CGSize(width: max(self.size.width, baseImage.size.width),
                                            height: max(self.size.height, baseImage.size.height)))
        UIGraphicsBeginImageContext(container.size)
        // swiftlint:disable:next force_unwrapping
        UIGraphicsGetCurrentContext()!.interpolationQuality = interpolationQuality

        let centerX = container.width / 2

        let baseImageScale = container.size.width / baseImage.size.width
        let baseImageSize = baseImage.size.multiply(by: baseImageScale)

        baseImage.draw(in: CGRect(origin: CGPoint(x: centerX - baseImageSize.width / 2,
                                                  y: container.maxY - baseImageSize.height),
                                  size: baseImageSize))

        let topImageScale = container.size.width / self.size.width
        let topImageSize = self.size.multiply(by: topImageScale)

        self.draw(in: CGRect(origin: CGPoint(x: centerX - topImageSize.width / 2,
                                             y: container.maxY - topImageSize.height),
                             size: topImageSize))
        // swiftlint:disable:next force_unwrapping
        let image = UIGraphicsGetImageFromCurrentImageContext()!
        UIGraphicsEndImageContext()

        let cost = CFAbsoluteTimeGetCurrent() - start
        UIImage.Merge.totalCost += cost

        if verbose {
            NSLog("Merging two image cost: %.6fms", cost * 1000)
            NSLog("Total merging cost: %.6fms", UIImage.Merge.totalCost * 1000)
        }
        return image
    }
}
