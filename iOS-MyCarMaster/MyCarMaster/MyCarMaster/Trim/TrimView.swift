//
//  View.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit

import MCMResource

final class TrimView: UIView {

    private let inset: CGFloat = 16
    private let firstSpacing: CGFloat = 16
    private let listSpacing: CGFloat = 12

    private let previewImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        imageView.image = UIImage(named: "Exclusive")
        return imageView
    }()

    private let recommendLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium1
        label.setText("어떤 트림을 고를지 고민되나요?")
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
        button.style = .bodySmall1
        button.layer.borderColor = UIColor.MCM.navyBlue1.cgColor
        button.layer.borderWidth = 1.0
        button.setStyledTitle("내게 맞는 트림 찾기", for: .normal)
        return button
    }()

    private lazy var trimListView: UICollectionView = {
        let listView = UICollectionView(frame: .zero, collectionViewLayout: createListLayout())
        listView.bounces = false
        listView.backgroundColor = .systemGray
        listView.indicatorStyle = .white
        return listView
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        backgroundColor = .white
    }

    private func configureLayout() {
        addSubview(previewImageView)
        previewImageView.translatesAutoresizingMaskIntoConstraints = false

        let recommendStackView = UIStackView()
        recommendStackView.axis = .horizontal
        recommendStackView.alignment = .fill
        recommendStackView.distribution = .equalSpacing

        recommendStackView.addArrangedSubview(recommendLabel)
        recommendStackView.addArrangedSubview(recommendButton)

        addSubview(recommendStackView)
        recommendStackView.translatesAutoresizingMaskIntoConstraints = false

        addSubview(trimListView)
        trimListView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            previewImageView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            previewImageView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            previewImageView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -inset),
            previewImageView.heightAnchor.constraint(equalTo: previewImageView.widthAnchor, multiplier: 216/375), // 제시된 크기 (375 * 216)

            recommendStackView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            recommendStackView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -inset),
            recommendStackView.bottomAnchor.constraint(equalTo: previewImageView.bottomAnchor),
            //            RecommendStackView.heightAnchor.constraint(equalToConstant: 28),

            trimListView.topAnchor.constraint(equalTo: previewImageView.bottomAnchor, constant: firstSpacing),
            trimListView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            trimListView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -inset),
            trimListView.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor)
        ])
    }

    private func createListLayout() -> UICollectionViewLayout {
        let configuration = UICollectionLayoutListConfiguration(appearance: .grouped)
        let layout = UICollectionViewCompositionalLayout.list(using: configuration)
        layout.configuration.interSectionSpacing = listSpacing
        return layout
    }
}

// MARK: - API
extension TrimView {
    func setDelegate(_ delegator: UICollectionViewDelegate) {
        trimListView.delegate = delegator
    }

    func setDataSource(_ dataSource: UICollectionViewDataSource) {
        trimListView.dataSource = dataSource
    }

    func registerCellClass(_ cellClass: UICollectionViewCell.Type) {
        trimListView.register(cellClass, forCellWithReuseIdentifier: cellClass.reuseIdentifier)
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct ViewPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UIViewPreview {
            return TrimView()
        }
    }
}
#endif
