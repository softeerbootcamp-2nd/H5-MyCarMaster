//
//  EngineViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class EngineViewController: UIViewController {

    typealias ListCellClass = BasicListCell
    typealias DataSource = UICollectionViewDiffableDataSource<Section, Engine>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, Engine>

    enum Section {
        case engine
    }

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    var selectedEngine: Engine?

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

    private var contentView: EngineView<ListCellClass> {
        return view as? EngineView ?? EngineView()
    }

    override func loadView() {
        view = EngineView<ListCellClass>(frame: .zero)
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

extension EngineViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let engine = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.engineDidSelect(engine))
        }
    }
}

extension EngineViewController: Reactable {
    func bindState(reactor: EngineReactor) {
        reactor.state.map(\.selectedEngine)
            .dropFirst()
            .sink { [weak self] engine in
                if let engine {
                    self?.selectItemFor(engine)
                    return
                }

                if let firstEngine = self?.dataSource.itemIdentifier(
                    for: .init(item: 0, section: 0)
                ) {
                    reactor.action.send(.engineDidSelect(firstEngine))
                }
                return
            }
            .store(in: &cancellables)

        reactor.state.map(\.engineList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] engineList in
                var snapshot = Snapshot()
                snapshot.appendSections([.engine])
                snapshot.appendItems(engineList)
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

extension EngineViewController {
    func selectItemFor(_ engine: Engine) {
        guard let indexPath = dataSource.indexPath(for: engine) else { return }
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
