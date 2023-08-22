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
    typealias DataSource = UICollectionViewDiffableDataSource<Section, Trim>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, Trim>

    enum Section {
        case trim
    }

    //  MARK: Property
    var cancellables = Set<AnyCancellable>()

    private var dataSource: DataSource!
    func configureDataSource() {
        dataSource = DataSource(collectionView: contentView.listView, cellProvider: { [weak self] collectionView, indexPath, itemIdentifier in
            guard let cell = collectionView.dequeueReusableCell(
                withReuseIdentifier: ListCellClass.reuseIdentifier,
                for: indexPath
            ) as? ListCellClass else {
                fatalError("개발자 오류: 등록되지 않은 Cell 입니다.")
            }
            cell.configure(with: itemIdentifier)

            if let selectedTrim = self?.selectedTrim {
                if selectedTrim == itemIdentifier {
                    collectionView.selectItem(at: indexPath, animated: false, scrollPosition: [])
                    // 원래는 delegate에서 처리해줘야하나, 버그로 인해 delegate로 메시지가 전달되지 않아, 여기에서 처리함.
                    self?.reactor?.action.send(.trimDidSelect(itemIdentifier))
                }
            } else if indexPath.row == 0 {
                collectionView.selectItem(at: indexPath, animated: false, scrollPosition: [])
                self?.reactor?.action.send(.trimDidSelect(itemIdentifier))
            }
            return cell
        })
        contentView.setDataSource(dataSource)
    }

    var selectedTrim: Trim?

    private var contentView: TrimView<ListCellClass> {
        return view as? TrimView ?? TrimView()
    }

    override func loadView() {
        view = TrimView<ListCellClass>(frame: .zero)
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

extension TrimViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let trim = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.trimDidSelect(trim))
        }
    }
}

extension TrimViewController: Reactable {
    func bindState(reactor: TrimReactor) {
        reactor.state.compactMap(\.selectedTrim)
            .sink { [weak self] trim in
                self?.selectedTrim = trim
            }
            .store(in: &cancellables)

        reactor.state
            .map(\.trimList)
            .dropFirst()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] trimList in
                var snapshot = Snapshot()
                snapshot.appendSections([.trim])
                snapshot.appendItems(trimList)
                self?.dataSource.apply(snapshot)
            }
            .store(in: &cancellables)
    }
}
