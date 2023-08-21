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
        let previewImage = UIImage(named: "CreamyWhitePearl")!
        let bottomAroundImage = UIImage(named: "BottomAround")!
        previewImageView.image = bottomAroundImage.drawAtBottom(ofImage: previewImage, verbose: true)
    }
}
