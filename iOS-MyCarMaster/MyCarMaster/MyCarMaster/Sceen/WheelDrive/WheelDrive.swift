//
//  WheelDrive.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

final class WheelDriveView: BasicStepView {
    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "2WD")
    }
}

struct WheelDrive {
    let model: String
    let name: String
    let description: String
    let ratio: Int
    let price: Int
    let imageURL: URL?
}
