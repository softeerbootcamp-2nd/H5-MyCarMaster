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

//    private let basicOptionView = UIView().then { view in
//        let label = UILabel().then { label in
//            label.style = .bodyMedium1
//            label.textColor = .MCM.navyBlue5
//            label.setText("원래 가지고 있는 옵션이 궁금하신가요?")
//        }
//
//        let button = UIButton().then { button in
//            button.style = .buttonTitleSmall
//            button.setStyledTitle("기본 포함 옵션", for: .normal)
//            button.setTitleColor(UIColor.MCM.navyBlue5, for: .normal)
//            button.backgroundColor = .MCM.white
//            button.layer.borderColor = UIColor.MCM.navyBlue3.cgColor
//            button.layer.borderWidth = 1
//        }
//
//        view.backgroundColor = .MCM.navyBlue1
//        view.layer.borderWidth = 1.0
//        view.layer.borderColor = UIColor.MCM.navyBlue2.cgColor
//
//        view.addSubview(label)
//        view.addSubview(button)
//
//        label.translatesAutoresizingMaskIntoConstraints = false
//        label.topAnchor.constraint(equalTo: view.topAnchor, constant: 8).isActive = true
//        label.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16).isActive = true
//        label.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -8).isActive = true
//
//        button.translatesAutoresizingMaskIntoConstraints = false
//        button.topAnchor.constraint(equalTo: view.topAnchor, constant: 8).isActive = true
//        button.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16).isActive = true
//        button.bottomAnchor.constraint(equalTo: view.bottomAnchor, constant: -8).isActive = true
//        button.widthAnchor.constraint(equalToConstant: 78).isActive = true
//    }

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
//        collectionView.bounces = false
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
