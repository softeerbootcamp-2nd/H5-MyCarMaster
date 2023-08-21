//
//  TrimViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class TrimViewController: UIViewController {

    typealias ListCellClass = BasicListCell

    var cancellables = Set<AnyCancellable>()

    var dataList: [Trim] = []

    var selectedCellIndexPath: IndexPath = IndexPath(row: 0, section: 0) {
        didSet {
            print(#function, selectedCellIndexPath)
        }
    }

    private var contentView: TrimView<ListCellClass> {
        return view as? TrimView ?? TrimView()
    }

    override func loadView() {
        view = TrimView<ListCellClass>(frame: .zero)
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

extension TrimViewController {
    private func fetchFromDisk() {
        guard let fileURL = Bundle.main.url(forResource: "TrimDTO.json", withExtension: nil) else { return }
        applyData(try? Data(contentsOf: fileURL))
    }

    private func fetchData() {
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

            self.applyData(data)
        }.resume()
    }

    private func applyData(_ data: Data?) {
        if let data,
           let trimDTOList = try? JSONDecoder().decode(RootDTO.self, from: data).result.trims {
            self.dataList = trimDTOList.map { Trim($0) }
            DispatchQueue.main.async {
                self.contentView.listView.reloadData()
            }
        } else {
            print("Decoding Error")
            return
        }
    }
}

extension TrimViewController: UICollectionViewDelegate, UICollectionViewDataSource {

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

        cell.configure(with: dataList[indexPath.row])

        // 프리셋을 선택한다.
        if selectedCellIndexPath == indexPath {
            collectionView.selectItem(at: indexPath, animated: false, scrollPosition: [])
        }

        return cell
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let trim = dataList[indexPath.row]
        reactor?.action.send(.trimDidSelect(trim))
    }
}

extension TrimViewController: Reactable {
    func bindState(reactor: TrimReactor) {
        reactor.state.compactMap(\.selectedTrim)
            .sink { [weak self] trim in
                if let row = self?.dataList.firstIndex(of: trim) {
                    self?.selectedCellIndexPath = IndexPath(row: row, section: 0)
                }
            }
            .store(in: &cancellables)
    }
}
