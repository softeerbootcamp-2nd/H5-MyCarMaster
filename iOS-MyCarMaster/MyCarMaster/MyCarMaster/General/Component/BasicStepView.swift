//
//  BasicStepView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

class BasicStepView<ListCellClass>: UIView
where ListCellClass: UICollectionViewCell & ContentSizeEstimatable & CellStyleSelectable {

    let inset: CGFloat = 16
    let firstSpacing: CGFloat = 16
    let listSpacing: CGFloat = 12

    var previewView = UIView()

    var previewImageView = UIImageView().then { imageView in
        imageView.contentMode = .scaleAspectFit
    }

    private var collectionViewCellEstimatedHeight: CGFloat = ListCellClass.intrinsicContentSize.height

    lazy var listView: UICollectionView = {
        let listView = UICollectionView(frame: .zero, collectionViewLayout: createListLayout())
        listView.backgroundColor = .MCM.white
        listView.bounces = false
        listView.showsVerticalScrollIndicator = false
        listView.contentInset = .init(top: 0, left: 0, bottom: 20, right: 0)
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
        listView.register(ListCellClass.self, forCellWithReuseIdentifier: ListCellClass.reuseIdentifier)
    }

    func configureLayout() {
        [previewView, listView].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        previewView.addSubview(previewImageView)
        previewImageView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            previewImageView.topAnchor.constraint(equalTo: topAnchor),
            previewImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: inset),
            previewImageView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -inset),
            previewImageView.heightAnchor.constraint(equalTo: widthAnchor, multiplier: 216/375),

            listView.topAnchor.constraint(equalTo: previewImageView.bottomAnchor, constant: firstSpacing),
            listView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: inset),
            listView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -inset),
            listView.bottomAnchor.constraint(equalTo: bottomAnchor),

            previewImageView.topAnchor.constraint(equalTo: previewView.topAnchor),
            previewImageView.leadingAnchor.constraint(equalTo: previewView.leadingAnchor),
            previewImageView.trailingAnchor.constraint(equalTo: previewView.trailingAnchor),
            previewImageView.bottomAnchor.constraint(equalTo: previewView.bottomAnchor),
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
}
