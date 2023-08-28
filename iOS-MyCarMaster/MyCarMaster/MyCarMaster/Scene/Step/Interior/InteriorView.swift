//
//  InteriorView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import UIKit

final class InteriorView<ListCellClass>: BasicStepView<ListCellClass>
where ListCellClass: UICollectionViewCell & ContentSizeEstimatable & CellStyleSelectable {

    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "InteriorBlack")
    }
}
