//
//  InteriorViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import UIKit

import MCMNetwork
import MVIFoundation

final class InteriorViewController: UIViewController {

    typealias ListCellClass = ColorListCell

    var dataList: [Interior] = []

    var selectedCellIndexPath: IndexPath = IndexPath(row: 0, section: 0) {
        didSet {
            print(#function, selectedCellIndexPath)
        }
    }

    private var contentView: InteriorView<ListCellClass> {
        return view as? InteriorView ?? InteriorView()
    }

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func loadView() {
        super.loadView()
        view = InteriorView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
#if ONLINE
        fetchData()
#elseif OFFLINE
        fetchFromDisk()
#endif
    }

    private func configureUI() {
        contentView.setDelegate(self)
        contentView.setDataSource(self)
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

extension InteriorViewController {
    private func fetchFromDisk() {
        guard let fileURL = Bundle.main.url(forResource: "InteriorDTO.json", withExtension: nil) else { return }
        applyData(try? Data(contentsOf: fileURL))
    }

    private func fetchData() {
        let request = URLRequest(url: URL(string: Dependency.serverURL + "interior-colors?trimId=1&exteriorColorId=1")!)

        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("Interior:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            self.applyData(data)
        }.resume()
    }

    private func applyData(_ data: Data?) {
        if let data,
           let interiorDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.interiors {
            self.dataList = interiorDTOList.map { Interior($0) }
            DispatchQueue.main.async {
                self.contentView.listView.reloadData()
            }
        } else {
            print("Decoding Error")
            return
        }
    }
}

extension InteriorViewController: UICollectionViewDelegate, UICollectionViewDataSource {

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

        let cellState = dataList[indexPath.row]
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
