//
//  OptionViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/15.
//

import UIKit

final class OptionViewController: UIViewController {

    private let categoryList = ["전체", "안전", "스타일&퍼포먼스", "차량 보호", "편의"]
    private var optionList: [Option] = []

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
    }

    private func configureUI() {
//        contentView.categoryListView.delegate = self
        contentView.categoryListView.dataSource = self
        contentView.optionListView.dataSource = self
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

extension OptionViewController {
    private func fetchFromDisk() {
        optionList = [
            Option(model: "펠리세이드", category: "편의", name: "알콘 단조 브레이크 & 20인치 휠 패키지", price: 0, ratio: 54, imgURL: nil, summary: "", description: "", tag: "", subOptions: [])
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

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        if collectionView == contentView.optionListView {
            guard let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: OptionListCell.reuseIdentifier,
                for: indexPath
            ) as? OptionListCell else {
                fatalError("등록되지 않은 cell입니다.")
            }
            cell.configure(with: optionList[indexPath.row])
            return cell
        } else if collectionView == contentView.categoryListView {
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
