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

    typealias ListCellClass = BasicListCell

    var engineList: [Engine] = []

    private var contentView: EngineView<ListCellClass> {
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
        view = EngineView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()

        let request = URLRequest(url: URL(string: Dependency.serverURL + "engines?trimId=1")!)
        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("Engine:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            if let data,
               case let .engines(engineDTOList) = try? JSONDecoder().decode(RootDTO.self, from: data).result {
                self.engineList = engineDTOList.map { Engine($0) }
                print(self.engineList)
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
            withReuseIdentifier: ListCellClass.reuseIdentifier,
            for: indexPath
        ) as? ListCellClass else {
            fatalError("등록되지 않은 cell입니다.")
        }

        let cellState = engineList[indexPath.row].basicListCellState
        cell.configure(with: cellState)

        // FIXME: 초기값 선택이 동작하지 않음
//        if indexPath.row == 0 {
//            cell.isSelected = true
//            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: .top)
//        }
        return cell
    }
}
