//
//  ImageCacheManager.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import UIKit

final class ImageCacheManager {

    static let shared = ImageCacheManager()
    private init() { }

    private let cache = NSCache<NSURL, UIImage>()

    func setObject(_ image: UIImage, forKey key: NSURL) {
        cache.setObject(image, forKey: key)
    }

    func object(forKey key: NSURL) -> UIImage? {
        return cache.object(forKey: key)
    }
}
