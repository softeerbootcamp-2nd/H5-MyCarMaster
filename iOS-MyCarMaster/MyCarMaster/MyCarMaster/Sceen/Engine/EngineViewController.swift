//
//  EngineViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import UIKit

import MCMNetwork
import MVIFoundation

final class EngineViewController: UIViewController {

    let engineList = [
        Engine(model: "펠리세이드", name: "가솔린 3.8", ratio: 54, description: "엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다", fuelMin: 8.0, fuelMax: 9.2, power: 295, toque: 36.2, price: 0),
        Engine(model: "펠리세이드", name: "디젤 2.2", ratio: 54, description: "높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다", fuelMin: 11.4, fuelMax: 12.4, power: 202, toque: 45.0, price: 1000000)
    ]

    private var engineView: EngineView {
        return view as? EngineView ?? EngineView()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        view = EngineView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        engineView.setDelegate(self)
        engineView.setDataSource(self)
        engineView.registerCellClass(BasicListCell.self)
    }

    override func didMove(toParent parent: UIViewController?) {
        engineView.updateLayout()
    }
}

extension EngineViewController: UICollectionViewDelegate, UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return engineList.count
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

        let cellState = BasicListCellState(from: engineList[indexPath.row])
        cell.configure(with: cellState)

        // FIXME: 초기값 선택이 동작하지 않음
        if indexPath.row == 0 {
            cell.isSelected = true
            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: .top)
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
