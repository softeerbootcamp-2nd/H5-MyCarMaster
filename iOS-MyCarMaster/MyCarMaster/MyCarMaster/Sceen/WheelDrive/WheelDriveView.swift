//
//  WheelDriveView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

final class WheelDriveView<ListCellClass>: BasicStepView<ListCellClass>
where ListCellClass: UICollectionViewCell & ContentSizeEstimatable & Selectable {

    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "2WD")
    }
}
