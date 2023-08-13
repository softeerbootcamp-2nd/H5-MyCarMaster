//
//  BasicStepView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

class BasicStepView: UIView {

    let inset: CGFloat = 16
    let firstSpacing: CGFloat = 16
    let listSpacing: CGFloat = 12

    let previewView: UIView = {
        let view = UIView()
        view.backgroundColor = .MCM.white
        return view
    }()

    let previewImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        return imageView
    }()

    private var collectionViewCellEstimatedHeight: CGFloat = 0

    lazy var listView: UICollectionView = {
        let listView = UICollectionView(frame: .zero, collectionViewLayout: createListLayout())
        listView.backgroundColor = .MCM.white
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

    func configureUI() {
        backgroundColor = .white
    }

    func configureLayout() {
        [previewView, listView].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            previewView.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            previewView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            previewView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -inset),
            previewView.heightAnchor.constraint(equalTo: widthAnchor, multiplier: 216/375),

            listView.topAnchor.constraint(equalTo: previewView.bottomAnchor, constant: firstSpacing),
            listView.leadingAnchor.constraint(equalTo: safeAreaLayoutGuide.leadingAnchor, constant: inset),
            listView.trailingAnchor.constraint(equalTo: safeAreaLayoutGuide.trailingAnchor, constant: -inset),
            listView.bottomAnchor.constraint(equalTo: safeAreaLayoutGuide.bottomAnchor)
        ])

        previewView.addSubview(previewImageView)
        previewImageView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            previewImageView.centerXAnchor.constraint(equalTo: previewView.centerXAnchor),
            previewImageView.centerYAnchor.constraint(equalTo: previewView.centerYAnchor),
            previewImageView.widthAnchor.constraint(lessThanOrEqualTo: previewView.widthAnchor, multiplier: 1.0),
            previewImageView.heightAnchor.constraint(lessThanOrEqualTo: previewView.heightAnchor, multiplier: 1.0),
        ])
    }
}

extension BasicStepView {
    private func createListLayout() -> UICollectionViewLayout {
        let layoutSize = NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(1.0),
            heightDimension: .estimated(collectionViewCellEstimatedHeight)
        )
        let item = NSCollectionLayoutItem(layoutSize: layoutSize)
        let group = NSCollectionLayoutGroup.horizontal(layoutSize: layoutSize, subitems: [item])

        let section = NSCollectionLayoutSection(group: group)
        section.interGroupSpacing = listSpacing

        let layout = UICollectionViewCompositionalLayout(section: section)
        return layout
    }
}

// MARK: - API
extension BasicStepView {
    func setDelegate(_ delegator: UICollectionViewDelegate) {
        listView.delegate = delegator
    }

    func setDataSource(_ dataSource: UICollectionViewDataSource) {
        listView.dataSource = dataSource
    }

    func updateLayout() {
        configureLayout()
    }

    func configureListView<CellClass>(withCellClass cellClass: CellClass.Type)
    where CellClass: Reusable & ContentSizeEstimatable {
        collectionViewCellEstimatedHeight = cellClass.intrinsicContentSize.height
        listView.register(cellClass, forCellWithReuseIdentifier: cellClass.reuseIdentifier)
    }

    func registerCellClass(_ cellClass: UICollectionViewCell.Type) {
        listView.register(cellClass, forCellWithReuseIdentifier: cellClass.reuseIdentifier)
    }
}
