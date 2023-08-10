//
//  EngineView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

final class EngineView: BasicStepView {
    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "Gasoline")
    }
}

struct Engine {
    let model: String
    let name: String
    let ratio: Int
    let description: String
    let fuelMin: Double
    let fuelMax: Double
    let power: Int
    let toque: Double
    let price: Int
    var imageURL: URL?
}
