//
//  TrimViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit

import MCMNetwork
import MVIFoundation

final class TrimViewController: UIViewController {

    let trimList = [
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림실용적인 기본 기능을 갖춘 베이직 트림실용적인 기본 기능을 갖춘 베이직 트림실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
    ]

    private var trimView: TrimView {
        return view as? TrimView ?? TrimView()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        view = TrimView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        trimView.setDataSource(self)
        trimView.registerCellClass(BasicListCell.self)
    }

    override func didMove(toParent parent: UIViewController?) {
        trimView.updateLayout()
    }
}

extension TrimViewController: UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return trimList.count
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

        let cellState = BasicListCellState(from: trimList[indexPath.row])
        cell.configure(with: cellState)
        return cell
    }
}
