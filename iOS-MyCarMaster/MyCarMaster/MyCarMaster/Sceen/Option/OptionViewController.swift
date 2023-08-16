//
//  OptionViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/15.
//

import UIKit

final class OptionViewController: UIViewController {

    enum Section {
        case option
    }

    private let categoryList = ["전체", "안전", "스타일&퍼포먼스", "차량 보호", "편의"]
    private var optionList: [Option] = []

    private var dataSource: UICollectionViewDiffableDataSource<Section, Option>?
    private func configureDataSource() {
        dataSource = UICollectionViewDiffableDataSource<Section, Option>(
            collectionView: contentView.optionListView
        ) { [weak self] collectionView, indexPath, itemIdentifier in
            guard let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: OptionListCell.reuseIdentifier,
                for: indexPath
            ) as? OptionListCell else {
                fatalError("등록되지 않은 cell 입니다.")
            }
            guard let option = self?.optionList[indexPath.item] else {
                fatalError("존재하지 않는 데이터입니다.")
            }
            cell.configure(with: option)
            return cell
        }
        contentView.optionListView.dataSource = dataSource

        var snapshot = NSDiffableDataSourceSnapshot<Section, Option>()
        snapshot.appendSections([.option])
        snapshot.appendItems(optionList)
        dataSource?.apply(snapshot)
    }

    private var contentView: OptionView {
        return view as? OptionView ?? OptionView(frame: .zero)
    }

    override func loadView() {
        view = OptionView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
#if ONLINE
//        fetchData()
#elseif OFFLINE
        fetchFromDisk()
#endif
        configureDataSource()
    }

    private func configureUI() {
//        contentView.categoryListView.delegate = self
        contentView.categoryListView.dataSource = self
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

extension OptionViewController {
    private func fetchFromDisk() {
        optionList = [
            Option(model: "펠리세이드", category: "편의", name: "알콘 단조 브레이크 & 20인치 휠 패키지", price: 0, ratio: 54, imgURL: nil, summary: "", description: "", tag: "", subOptions: []),
            Option(model: "펠리세이드", category: "편의", name: "2열 통풍 시트", price: 0, ratio: 54, imgURL: nil, summary: "", description: "", tag: "", subOptions: []),
            Option(model: "펠리세이드", category: "편의", name: "듀얼 머플러 패키지", price: 0, ratio: 54, imgURL: nil, summary: "", description: "", tag: "", subOptions: []),
        ]
    }
}

extension OptionViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if collectionView == contentView.optionListView {
            return optionList.count
        } else if collectionView == contentView.categoryListView {
            return categoryList.count
        }
        return 0
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) else { return }
        contentView.optionListView.bounds = cell.frame
    }

    func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {
        if collectionView == contentView.categoryListView {
            guard let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: ButtonCell.reuseIdentifier,
                for: indexPath
            ) as? ButtonCell else {
                fatalError("등록되지 않은 cell입니다.")
            }
            cell.setStyledTitle(categoryList[indexPath.item])
            return cell
        }
        return UICollectionViewCell()
    }
}
