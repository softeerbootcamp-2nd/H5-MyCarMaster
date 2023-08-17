//
//  BodyTypeView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

final class BodyTypeView<ListCellClass>: BasicStepView<ListCellClass>
where ListCellClass: UICollectionViewCell & ContentSizeEstimatable & CellStyleSelectable {

    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "BodyType_7")
    }
}
