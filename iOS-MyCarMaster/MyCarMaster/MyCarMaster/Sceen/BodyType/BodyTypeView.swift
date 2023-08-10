//
//  BodyTypeView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

final class BodyTypeView: BasicStepView {
    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "BodyType_7")
    }
}

struct BodyType {
    var model: String
    var name: String
    var summary: String
    var description: String
    var ratio: Int
    var price: Int
    var imageURL: URL?
}
