//
//  TrimView.swift
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

    private let trimPreviewImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        return imageView
    }()

    private let trimRecommendLabel: UILabel = {
        let label = UILabel()
        label.style = .titleSmall
        label.setText("어떤 트림을 고를지 고민되나요?")
        return label
    }()

    private let trimRecommendButton: UIButton = {
        let button = UIButton()
        button.style = .bodySmall1
        button.setStyledTitle("내게 맞는 트림 찾기", for: .normal)
        return button
    }()

    private lazy var trimListView: UICollectionView = {
        let listView = UICollectionView()
        listView.collectionViewLayout = createListLayout()
        listView.bounces = false
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
    }

    private func configureLayout() {
        addSubview(trimPreviewImageView)
        trimPreviewImageView.translatesAutoresizingMaskIntoConstraints = false

        let trimRecommendStackView = UIStackView()
        trimRecommendStackView.axis = .horizontal
        trimRecommendStackView.distribution = .fill

        trimRecommendStackView.addArrangedSubview(trimRecommendLabel)
        trimRecommendStackView.addArrangedSubview(trimRecommendButton)

        addSubview(trimRecommendStackView)
        trimRecommendStackView.translatesAutoresizingMaskIntoConstraints = false

        addSubview(trimListView)
        trimListView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            trimPreviewImageView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            trimPreviewImageView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor),
            trimPreviewImageView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor),
            trimPreviewImageView.heightAnchor.constraint(equalTo: trimPreviewImageView.widthAnchor, multiplier: 216/375), // 제시된 크기 (375 * 216)

            trimRecommendStackView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            trimRecommendStackView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: inset),
            trimRecommendStackView.bottomAnchor.constraint(equalTo: trimPreviewImageView.bottomAnchor),
            trimRecommendStackView.heightAnchor.constraint(equalToConstant: 28),

            trimListView.topAnchor.constraint(equalTo: trimPreviewImageView.bottomAnchor, constant: firstSpacing),
            trimListView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            trimListView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: inset),
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
