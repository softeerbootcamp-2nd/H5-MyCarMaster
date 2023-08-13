//
//  ExteriorImageView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import UIKit

import MCMNetwork
import MVIFoundation

final class ExteriorViewController: UIViewController {

    typealias ListCellClass = ColorListCell

    var exteriorList: [Exterior] = []

    private var contentView: ExteriorView<ListCellClass> {
        return view as? ExteriorView ?? ExteriorView()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        view = ExteriorView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()

        let request = URLRequest(url: URL(string: Dependency.serverURL + "exterior-colors?trimId=1")!)
        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("Exterior:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            if let data,
               case let .exteriors(exteriorDTOList) = try? JSONDecoder().decode(RootDTO.self, from: data).result {
                DispatchQueue.main.async {
                    self.exteriorList = exteriorDTOList.map { Exterior($0) }
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

extension ExteriorViewController: UICollectionViewDelegate, UICollectionViewDataSource {

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return exteriorList.count
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
        cell.configure(with: exteriorList[indexPath.row])

        // FIXME: 초기값 선택이 동작하지 않음
//        if indexPath.item == 0 {
//            cell.isSelected = true
//            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: .init())
//        }
        return cell
    }
}
