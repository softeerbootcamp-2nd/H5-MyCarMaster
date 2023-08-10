//
//  WheelDriveViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

import MCMNetwork
import MVIFoundation

final class WheelDriveViewController: UIViewController {

    let wheelDriveList = [
        WheelDrive(model: "펠리세이드", name: "2WD", description: "엔진 동력이 전후륜 중 한쪽으로만 전달돼 움직입니다. 차체가 가벼워 연료 효율이 높습니다.", ratio: 54, price: 0, imageURL: nil),
        WheelDrive(model: "펠리세이드", name: "4WD", description: "전자식 상시 4륜 구동 시스템으로 환경에 맞춰 구동력을 자동 배분해 안정성을 높입니다.", ratio: 54, price: 1000000, imageURL: nil),
    ]

    private var wheelDriveView: WheelDriveView {
        return view as? WheelDriveView ?? WheelDriveView()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        view = WheelDriveView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        wheelDriveView.setDataSource(self)
        wheelDriveView.registerCellClass(BasicListCell.self)
    }

    override func didMove(toParent parent: UIViewController?) {
        wheelDriveView.updateLayout()
    }
}

extension WheelDriveViewController: UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return wheelDriveList.count
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

        let cellState = BasicListCellState(from: wheelDriveList[indexPath.row])
        cell.configure(with: cellState)
        return cell
    }
}
