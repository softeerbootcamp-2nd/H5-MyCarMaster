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

    typealias ListCellClass = BasicListCell

    var dummyWheelDriveList = [
        WheelDrive(model: "펠리세이드", name: "2WD", description: "엔진 동력이 전후륜 중 한쪽으로만 전달돼 움직입니다. 차체가 가벼워 연료 효율이 높습니다.", ratio: 54, price: 0, imageURL: nil),
        WheelDrive(model: "펠리세이드", name: "4WD", description: "전자식 상시 4륜 구동 시스템으로 환경에 맞춰 구동력을 자동 배분해 안정성을 높입니다.", ratio: 54, price: 1000000, imageURL: nil),
    ]

    var wheelDriveList: [WheelDrive] = []

    private var contentView: WheelDriveView<ListCellClass> {
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
        view = WheelDriveView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()

        let request = URLRequest(url: URL(string: Dependency.serverURL + "wheel-drives?trimId=1&engineId=1")!)
        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("WheelDrive:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            if let data,
               case let .wheelDrives(wheelDriveDTOList) = try? JSONDecoder().decode(RootDTO.self, from: data).result {
                self.wheelDriveList = wheelDriveDTOList.map { WheelDrive($0) }
                DispatchQueue.main.async {
                    self.contentView.listView.reloadData()
                }
            } else {
                print("Decoding Error")
                return
            }
        }.resume()
    }

    private func configureUI() {
        contentView.setDelegate(self)
        contentView.setDataSource(self)
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

extension WheelDriveViewController: UICollectionViewDelegate, UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        NSLog("wheelDriveList: %d개", wheelDriveList.count)
        return wheelDriveList.count
    }

    func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {

        guard let cell = collectionView.dequeueReusableCell(
            withReuseIdentifier: ListCellClass.reuseIdentifier,
            for: indexPath
        ) as? ListCellClass else {
            fatalError("등록되지 않은 cell입니다.")
        }

        let cellState = wheelDriveList[indexPath.row].basicListCellState
        cell.configure(with: cellState)

        // FIXME: 초기값 선택이 동작하지 않음
//        if indexPath.item == 0 {
//            cell.isSelected = true
//            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: .top)
//        }
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? ListCellClass else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        cell.select()
    }

    func collectionView(_ collectionView: UICollectionView, didDeselectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? ListCellClass else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        cell.deselect()
    }
}
