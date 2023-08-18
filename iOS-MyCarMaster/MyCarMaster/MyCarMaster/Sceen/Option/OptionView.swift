//
//  OptionView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/15.
//

import UIKit

import MCMResource

final class OptionView: UIView {

    private let previewImageView = UIImageView().then { imageView in
        imageView.contentMode = .scaleAspectFit
        imageView.backgroundColor = .MCM.grey2
    }

    lazy var categoryListView = UICollectionView(
        frame: .zero,
        collectionViewLayout: createCatgoryListViewLayout()
    ).then { collectionView in
        collectionView.backgroundColor = .MCM.white
        collectionView.bounces = false
        collectionView.register(ButtonCell.self, forCellWithReuseIdentifier: ButtonCell.reuseIdentifier)
    }

    private func createCatgoryListViewLayout() -> UICollectionViewLayout {
        let itemSize = NSCollectionLayoutSize(
            widthDimension: .estimated(44),
            heightDimension: .fractionalHeight(1.0)
        )
        let item = NSCollectionLayoutItem(layoutSize: itemSize)

        let groupSize = NSCollectionLayoutSize(
            widthDimension: .estimated(44),
            heightDimension: .fractionalHeight(1.0)
        )
        let group = NSCollectionLayoutGroup.horizontal(layoutSize: groupSize, subitems: [item])

        let section = NSCollectionLayoutSection(group: group)
        section.orthogonalScrollingBehavior = .continuous
        section.interGroupSpacing = 12

        return UICollectionViewCompositionalLayout(section: section)
    }

    lazy var optionListView = UICollectionView(
        frame: .zero,
        collectionViewLayout: createOptionListViewLayout()
    ).then { collectionView in
        collectionView.backgroundColor = .MCM.white
        collectionView.register(OptionListCell.self, forCellWithReuseIdentifier: OptionListCell.reuseIdentifier)
    }

    private func createOptionListViewLayout() -> UICollectionViewLayout {
        let layoutSize = NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(1.0),
            heightDimension: .estimated(137)
        )
        let item = NSCollectionLayoutItem(layoutSize: layoutSize)
        let group = NSCollectionLayoutGroup.horizontal(layoutSize: layoutSize, subitems: [item])

        let section = NSCollectionLayoutSection(group: group)
        section.interGroupSpacing = 12

        return UICollectionViewCompositionalLayout(section: section)
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        backgroundColor = .MCM.white
    }

    private func configureLayout() {
        [previewImageView, categoryListView, optionListView].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            previewImageView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            previewImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
            previewImageView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16),
            previewImageView.heightAnchor.constraint(equalTo: previewImageView.widthAnchor, multiplier: 216 / 375),

            categoryListView.topAnchor.constraint(equalTo: previewImageView.bottomAnchor, constant: 12),
            categoryListView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
            categoryListView.trailingAnchor.constraint(equalTo: trailingAnchor),
            categoryListView.heightAnchor.constraint(equalToConstant: 32),

            optionListView.topAnchor.constraint(equalTo: categoryListView.bottomAnchor, constant: 12),
            optionListView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
            optionListView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16),
            optionListView.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor)
        ])
    }
}

// MARK: - API
extension OptionView {
    func updateLayout() {
        configureLayout()
    }

    func configurePreviewImage(with image: UIImage?) {
        previewImageView.image = image
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct OptionViewPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UIViewPreview {
            return OptionView()
        }
        .frame(width: UIScreen.main.bounds.width, height: 300)
    }
}
#endif
