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

    typealias ListCellClass = BasicListCell

    let dummyTrimList = [
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림실용적인 기본 기능을 갖춘 베이직 트림실용적인 기본 기능을 갖춘 베이직 트림실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
    ]
    var trimList: [Trim] = []

    private var trimView: TrimView<ListCellClass> {
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
        view = TrimView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()

        let request = URLRequest(url: URL(string: Dependency.serverURL + "trims?modelId=1")!)
        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("Trim:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            if let data,
               case let .trims(trimDTOList) = try? JSONDecoder().decode(RootDTO.self, from: data).result {
                self.trimList = trimDTOList.map { Trim($0) }
                DispatchQueue.main.async {
                    self.trimView.listView.reloadData()
                }
            } else {
                print("Decoding Error")
                return
            }
        }.resume()
    }

    private func configureUI() {
        trimView.setDelegate(self)
        trimView.setDataSource(self)
    }

    override func didMove(toParent parent: UIViewController?) {
        trimView.updateLayout()
    }
}

extension TrimViewController: UICollectionViewDelegate, UICollectionViewDataSource {

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
            withReuseIdentifier: ListCellClass.reuseIdentifier,
            for: indexPath
        ) as? ListCellClass else {
            fatalError("등록되지 않은 cell입니다.")
        }

        let cellState = trimList[indexPath.row].basicListCellState
        cell.configure(with: cellState)

        // FIXME: 초기값 선택이 동작하지 않음
//        if indexPath.row == 0 {
//            cell.isSelected = true
//            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: .top)
//        }
        return cell
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? ListCellClass else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        DispatchQueue.main.async {
            cell.select()
        }
    }

    func collectionView(_ collectionView: UICollectionView, didDeselectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? ListCellClass else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        DispatchQueue.main.async {
            cell.deselect()
        }
    }
}
