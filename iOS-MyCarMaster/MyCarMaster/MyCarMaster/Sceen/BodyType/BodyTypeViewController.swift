//
//  BodyTypeViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

import MCMNetwork
import MVIFoundation

final class BodyTypeViewController: UIViewController {

    let bodyTypeList = [
        BodyType(model: "펠리세이드", name: "7인승", summary: "", description: "8인승 시트에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.", ratio: 54, price: 0, imageURL: nil),
        BodyType(model: "펠리세이드", name: "4WD", summary: "", description: "1열 2명, 2열 3명, 3열 3명이 탑승할 수 있는 구조로, 많은 인원이 탑승할 수 있습니다", ratio: 54, price: 1000000, imageURL: nil),
    ]

    private var bodyTypeView: BodyTypeView {
        return view as? BodyTypeView ?? BodyTypeView()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        view = BodyTypeView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        bodyTypeView.setDelegate(self)
        bodyTypeView.setDataSource(self)
        bodyTypeView.registerCellClass(BasicListCell.self)
    }

    override func didMove(toParent parent: UIViewController?) {
        bodyTypeView.updateLayout()
    }
}

extension BodyTypeViewController: UICollectionViewDelegate, UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return bodyTypeList.count
    }

    func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {

        guard let cell = collectionView.dequeueReusableCell(
            withReuseIdentifier: BasicListCell.reuseIdentifier,
            for: indexPath
        ) as? BasicListCell else {
            fatalError("등록되지 않은 cell입니다.")
        }

        let cellState = BasicListCellState(from: bodyTypeList[indexPath.row])
        cell.configure(with: cellState)

        // FIXME: 초기값 선택이 동작하지 않음
        if indexPath.item == 0 {
            cell.isSelected = true
            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: .init())
        }
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? BasicListCell else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        cell.select()
    }

    func collectionView(_ collectionView: UICollectionView, didDeselectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? BasicListCell else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        cell.deselect()
    }
}
