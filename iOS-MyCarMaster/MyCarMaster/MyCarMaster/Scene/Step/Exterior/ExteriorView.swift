//
//  ExteriorView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import UIKit

final class ExteriorView<ListCellClass>: BasicStepView<ListCellClass>
where ListCellClass: UICollectionViewCell & ContentSizeEstimatable & CellStyleSelectable {

    override func configureUI() {
        super.configureUI()
        previewView = SpriteRotationView(image: UIImage(named: "silver_sprite")!)    }
}
