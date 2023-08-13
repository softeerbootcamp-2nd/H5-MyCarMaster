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

    typealias ListCellClass = BasicListCell

    var dataList: [BodyType] = []

    var selectedCellIndexPath: IndexPath = IndexPath(row: 0, section: 0) {
        didSet {
            print(#function, selectedCellIndexPath)
        }
    }

    private var contentView: BodyTypeView<ListCellClass> {
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
        view = BodyTypeView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()

        let request = URLRequest(url: URL(string: Dependency.serverURL + "body-types?modelId=1")!)
        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("BodyType:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            if let data,
               case let .bodyTypes(bodyTypeDTOList) = try? JSONDecoder().decode(RootDTO.self, from: data).result {
                self.dataList = bodyTypeDTOList.map { BodyType($0) }
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

extension BodyTypeViewController: UICollectionViewDelegate, UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return dataList.count
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

        let cellState = dataList[indexPath.row].basicListCellState
        cell.configure(with: cellState)

        // 프리셋을 선택한다.
        if selectedCellIndexPath == indexPath {
            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: [])
        }

        return cell
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let cell = collectionView.cellForItem(at: indexPath) as? ListCellClass else {
            fatalError("알 수 없는 오류가 발생했습니다.")
        }
        guard selectedCellIndexPath != indexPath else { return }
        selectedCellIndexPath = indexPath
    }
}
