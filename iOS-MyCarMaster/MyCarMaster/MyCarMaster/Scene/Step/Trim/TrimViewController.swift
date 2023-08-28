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

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    private var dataSource: DataSource!
    func configureDataSource() {
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
    func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        guard let trim = dataSource.itemIdentifier(for: indexPath) else {
            return false
        }
        reactor?.action.send(.shouldSelectTrim(trim))
        return false
    }

    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let trim = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.trimDidSelect(trim))
        }
    }
}

extension TrimViewController: Reactable {
    func bindState(reactor: TrimReactor) {
        reactor.state.map(\.selectedTrim)
            .dropFirst()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] trim in
                // 견적에 선택된 트림이 있을 때, 그 트림을 선택상태로 만들어준다.
                if let trim {
                    self?.selectItemFor(trim)
                    reactor.action.send(.fetchTrimImage(trim.imageURL))
                    return
                }

                // 없을 때에는 0번째 트림을 견적서에 추가한다.
                if let firstTrim = self?.dataSource?.itemIdentifier(for: IndexPath(item: 0, section: 0)) {
                    reactor.action.send(.trimDidSelect(firstTrim))
                    reactor.action.send(.fetchTrimImage(firstTrim.imageURL))
                }
                return
            }
            .store(in: &cancellables)

        reactor.state.map(\.trimList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] trimList in
                var snapshot = Snapshot()
                snapshot.appendSections([.trim])
                snapshot.appendItems(trimList)
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

        reactor.state.compactMap(\.showSelectionAlert)
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] trim in
                let alert = UIAlertController(
                    title: "트림을 변경하시겠습니까?",
                    message: "현재까지의 변경사항은 저장되지 않습니다.",
                    preferredStyle: .alert
                )
                alert.addAction(.init(title: "취소", style: .cancel))
                alert.addAction(.init(title: "확인", style: .default, handler: { _ in
                    reactor.action.send(.resetAndSelectTrim(trim))
                }))
                self?.present(alert, animated: false)
            }
            .store(in: &cancellables)

        reactor.state.map(\.isLoading)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] isLoading in
                if isLoading {
                    self?.showIndicator()
                } else {
                    self?.hideIndicator()
                }
            }
            .store(in: &cancellables)

        reactor.state.compactMap(\.selectedTrimImage)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] image in
                self?.contentView.previewImageView.image = image
            }
            .store(in: &cancellables)
    }
}

extension TrimViewController {
    func selectItemFor(_ trim: Trim) {
        guard let indexPath = dataSource.indexPath(for: trim) else { return }
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
