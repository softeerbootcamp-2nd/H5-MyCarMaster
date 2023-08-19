//
//  View.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit

import MCMResource

final class TrimView<ListCellClass>: BasicStepView<ListCellClass>
where ListCellClass: UICollectionViewCell & CellStyleSelectable & ContentSizeEstimatable {

    private let recommendLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium1(nil)
        label.setText("어떤 트림을 고를지 고민되나요?")
        label.textColor = .MCM.black
        return label
    }()

    private let recommendButton: UIButton = {
        let button = UIButton()
        if #available(iOS 15.0, *) {
            var config = UIButton.Configuration.plain()
            config.contentInsets = .init(top: 4, leading: 12, bottom: 4, trailing: 12)
            config.baseForegroundColor = .MCM.black
            button.configuration = config
        } else {
            button.contentEdgeInsets = UIEdgeInsets(top: 4, left: 12, bottom: 4, right: 12)
            button.setTitleColor(.MCM.black, for: .normal)
        }
        button.style = .bodySmall1(nil)
        button.layer.borderColor = UIColor.MCM.coolGrey1.cgColor
        button.layer.borderWidth = 1.0
        button.setStyledTitle("내게 맞는 트림 찾기", for: .normal)
        return button
    }()

    override func configureUI() {
        super.configureUI()
        previewImageView.image = UIImage(named: "Exclusive")
    }

    override func configureLayout() {
        super.configureLayout()

        let recommendStackView = UIStackView()
        recommendStackView.axis = .horizontal
        recommendStackView.alignment = .fill
        recommendStackView.distribution = .equalSpacing

        recommendStackView.addArrangedSubview(recommendLabel)
        recommendStackView.addArrangedSubview(recommendButton)

        addSubview(recommendStackView)
        recommendStackView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            recommendStackView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            recommendStackView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -inset),
            recommendStackView.bottomAnchor.constraint(equalTo: previewImageView.bottomAnchor),
        ])
    }
}
