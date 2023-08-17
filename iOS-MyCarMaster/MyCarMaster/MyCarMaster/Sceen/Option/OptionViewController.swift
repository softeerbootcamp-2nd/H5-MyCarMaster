//
//  OptionViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/15.
//

import UIKit

import MCMNetwork

final class OptionViewController: UIViewController {

    enum Section {
        case option
    }

    private let categoryList = ["전체", "안전", "스타일&퍼포먼스", "차량 보호", "편의"]

    private var dataSource: UICollectionViewDiffableDataSource<Section, Option>?
    private func configureDataSource() {
        dataSource = UICollectionViewDiffableDataSource<Section, Option>(
            collectionView: contentView.optionListView
        ) { collectionView, indexPath, option in
            guard let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: OptionListCell.reuseIdentifier,
                for: indexPath
            ) as? OptionListCell else {
                fatalError("등록되지 않은 cell 입니다.")
            }
            cell.configure(with: option)
            return cell
        }
        contentView.optionListView.dataSource = dataSource

        var snapshot = NSDiffableDataSourceSnapshot<Section, Option>()
        snapshot.appendSections([.option])
        snapshot.appendItems([])
        dataSource?.apply(snapshot)
    }

    private var contentView: OptionView {
        return view as? OptionView ?? OptionView(frame: .zero)
    }

    override func loadView() {
        view = OptionView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
#if ONLINE
        fetchData()
#elseif OFFLINE
        fetchFromDisk()
#endif
        configureDataSource()
    }

    private func configureUI() {
//        contentView.categoryListView.delegate = self
        contentView.categoryListView.dataSource = self
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

extension OptionViewController {
    private func fetchFromDisk() {
        guard let fileURL = Bundle.main.url(forResource: "OptionDTO.json", withExtension: nil) else {
            print("Invalid Data")
            return
        }

        applyData(try? Data(contentsOf: fileURL))
    }

    private func fetchData() {
        let request = URLRequest(url: URL(string: Dependency.serverURL + "options?trimId=1&engineId=1&wheelDriveId=1&bodyTypeId=1&interiorColorId=1")!)

        URLSession.shared.dataTask(with: request) { data, response, error in
            guard error == nil else {
                print(error?.localizedDescription)
                return
            }

            guard let response = response as? HTTPURLResponse else {
                print("Sever Error")
                return
            }

            print("Option:", response.statusCode)
            guard 200..<300 ~= response.statusCode else {
                return
            }

            self.applyData(data)
        }.resume()
    }

    private func applyData(_ data: Data?) {
        if let data,
           let optionDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.options {
            let dataList = optionDTOList.map { Option($0) }

            var snapshot = NSDiffableDataSourceSnapshot<Section, Option>()
            snapshot.appendSections([.option])
            snapshot.appendItems(dataList)
            DispatchQueue.main.async { [weak self] in
                self?.dataSource?.apply(snapshot)
            }
        } else {
            print("Decoding Error")
            return
        }
    }
}

extension OptionViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        if collectionView == contentView.categoryListView {
            return categoryList.count
        }
        return 0
    }

// MARK: CollectionViewDataSource
extension OptionViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        guard collectionView == contentView.categoryListView else { return 0 }
        
        return categoryList.count
    }

    func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {
        guard collectionView == contentView.categoryListView else { return UICollectionViewCell() }
        
        guard let cell = collectionView.dequeueReusableCell(
            withReuseIdentifier: ButtonCell.reuseIdentifier,
            for: indexPath
        ) as? ButtonCell else {
            fatalError("등록되지 않은 cell입니다.")
        }
        cell.setStyledTitle(categoryList[indexPath.item])
        return cell
    }
}
