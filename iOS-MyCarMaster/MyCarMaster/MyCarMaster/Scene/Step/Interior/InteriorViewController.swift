//
//  InteriorViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/14.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class InteriorViewController: UIViewController {

    typealias ListCellClass = ColorListCell
    typealias DataSource = UICollectionViewDiffableDataSource<Section, Interior>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, Interior>

    enum Section {
        case interior
    }

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    var selectedInterior: Interior?

    private var dataSource: DataSource!
    private func configureDataSource() {
        dataSource = DataSource(
            collectionView: contentView.listView,
            cellProvider: { collectionView, indexPath, itemIdentifier in
                guard let cell = collectionView.dequeueReusableCell(
                    withReuseIdentifier: ListCellClass.reuseIdentifier,
                    for: indexPath
                ) as? ListCellClass else {
                    fatalError("개발자 오류: 등록되지 않은 Cell 입니다.")
                }
                cell.configure(with: itemIdentifier)

                return cell
            })
        contentView.setDataSource(dataSource)
    }

    private var contentView: InteriorView<ListCellClass> {
        return view as? InteriorView ?? InteriorView()
    }

    override func loadView() {
        view = InteriorView<ListCellClass>(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureDataSource()
        configureUI()
        reactor?.action.send(.viewDidLoad)
    }

    private func configureUI() {
        contentView.setDelegate(self)
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

extension InteriorViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let interior = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.interiorDidSelect(interior))
        }
    }
}

extension InteriorViewController: Reactable {
    func bindState(reactor: InteriorReactor) {
        reactor.state.map(\.selectedInterior)
            .dropFirst()
            .sink { [weak self] interior in
                if let interior {
                    self?.selectItemFor(interior)
                    return
                }

                if let firstInterior = self?.dataSource.itemIdentifier(
                    for: .init(item: 0, section: 0)
                ) {
                    reactor.action.send(.interiorDidSelect(firstInterior))
                }
                return
            }
            .store(in: &cancellables)

        reactor.state.map(\.interiorList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] interiorList in
                var snapshot = Snapshot()
                snapshot.appendSections([.interior])
                snapshot.appendItems(interiorList)
                self?.dataSource.apply(snapshot, completion: {
                    reactor.action.send(.dataSourceDidApply)
                })
            }
            .store(in: &cancellables)

        reactor.state.compactMap(\.errorDescription)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] errorDescription in
                let alert = UIAlertController(
                    title: "에러",
                    message: errorDescription,
                    preferredStyle: .alert
                )
                alert.addAction(.init(title: "확인", style: .default))
                print(errorDescription)
                self?.present(alert, animated: false)
            }
            .store(in: &cancellables)
    }
}

extension InteriorViewController {
    func selectItemFor(_ interior: Interior) {
        guard let indexPath = dataSource.indexPath(for: interior) else { return }
        selectItemAt(indexPath)
    }

    func selectItemAt(_ indexPath: IndexPath) {
        guard dataSource?.itemIdentifier(for: indexPath) != nil else {
            print("Error: \(indexPath)에 원소가 없음")
            return
        }
        contentView.listView.selectItem(at: indexPath, animated: true, scrollPosition: [.centeredVertically])
    }
}
