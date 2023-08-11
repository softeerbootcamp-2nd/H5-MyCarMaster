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

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct EngineViewControllerPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UIViewControllerPreview {
            return EngineViewController()
        }
    }
}
#endif
