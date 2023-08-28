//
//  OptionViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/15.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class OptionViewController: UIViewController {

    typealias DataSource = UICollectionViewDiffableDataSource<Section, Option>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, Option>

    enum Section {
        case option
    }

    private let categoryList = ["전체", "안전", "스타일&퍼포먼스", "차량 보호", "편의"]

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    var selectedIndexPaths = Set<IndexPath>()
    var consideredIndexPaths = Set<IndexPath>()

    private var dataSource: DataSource!
    private func configureDataSource() {
        dataSource = DataSource(
            collectionView: contentView.optionListView,
            cellProvider: { [weak self] collectionView, indexPath, option in
                guard let cell = collectionView.dequeueReusableCell(
                    withReuseIdentifier: OptionListCell.reuseIdentifier,
                    for: indexPath
                ) as? OptionListCell else {
                    fatalError("개발자 오류: 등록되지 않은 cell 입니다.")
                }
                cell.configure(with: option)

                if self?.selectedIndexPaths.contains(indexPath) == Optional(true) {
                    cell.selectedStyle()
                } else if self?.consideredIndexPaths.contains(indexPath) == Optional(true) {
                    cell.consideredStyle()
                } else {
                    cell.unselectedStyle()
                }

                return cell
            })
        contentView.optionListView.dataSource = dataSource
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
        configureDataSource()
        addCellEventObservers()
        reactor?.action.send(.viewDidLoad)
    }

    private func configureUI() {
//        contentView.categoryListView.delegate = self
        contentView.categoryListView.dataSource = self
        contentView.optionListView.delegate = self
    }

    override func didMove(toParent parent: UIViewController?) {
        contentView.updateLayout()
    }
}

// OptionListCell의 버튼 이벤트가 reponder chain을 타고 이곳으로 넘어 올 것이다.
extension OptionViewController {
    private func addCellEventObservers() {
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(selectButtonDidTap),
            name: OptionListCell.selectButtonDidTap,
            object: nil
        )
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(considerButtonDidTap),
            name: OptionListCell.considerButtonDidTap,
            object: nil
        )
        NotificationCenter.default.addObserver(
            self,
            selector: #selector(unselectButtonDidTap),
            name: OptionListCell.unselectButtonDidTap,
            object: nil
        )
    }

    @objc
    func selectButtonDidTap(_ sender: Notification) {
        guard let cell = sender.userInfo?["cell"] as? OptionListCell,
              let indexPath = contentView.optionListView.indexPath(for: cell),
              let option = dataSource.itemIdentifier(for: indexPath)
        else { return }
        selectedIndexPaths.insert(indexPath)
        consideredIndexPaths.remove(indexPath)
        reactor?.action.send(.optionDidSelect(option))
    }

    @objc
    func unselectButtonDidTap(_ sender: Notification) {
        guard let cell = sender.userInfo?["cell"] as? OptionListCell,
              let indexPath = contentView.optionListView.indexPath(for: cell),
              let option = dataSource.itemIdentifier(for: indexPath)
        else { return }
        selectedIndexPaths.remove(indexPath)
        consideredIndexPaths.remove(indexPath)
        reactor?.action.send(.optionDidUnselect(option))
    }

    @objc
    func considerButtonDidTap(_ sender: Notification) {
        guard let cell = sender.userInfo?["cell"] as? OptionListCell,
              let indexPath = contentView.optionListView.indexPath(for: cell),
              let option = dataSource.itemIdentifier(for: indexPath)
        else { return }
        selectedIndexPaths.remove(indexPath)
        consideredIndexPaths.insert(indexPath)
        reactor?.action.send(.optionDidConsider(option))
    }
}

// MARK: CollectionViewDelegate
extension OptionViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let option = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.optionDidSelect(option))
        }
    }
}

// MARK: CollectionViewDataSource
extension OptionViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
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

extension OptionViewController: Reactable {
    func bindState(reactor: OptionReactor) {
        reactor.state.map(\.selectedOptions)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] selectedOptions in
                for selectedOption in selectedOptions {
                    self?.selectedStyleFor(selectedOption)
                }
            }
            .store(in: &cancellables)

        reactor.state.map(\.consideredOptions)
            .receive(on: DispatchQueue.main)
            .sink { [weak self] consideredOptions in
                for considredOption in consideredOptions {
                    self?.consideredStyleFor(considredOption)
                }
            }
            .store(in: &cancellables)

        reactor.state.map(\.optionList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] optionList in
                var snapshot = Snapshot()
                snapshot.appendSections([.option])
                snapshot.appendItems(optionList)
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

extension OptionViewController {
    func selectedStyleFor(_ option: Option) {
        guard let indexPath = dataSource.indexPath(for: option) else { return }
        guard let cell = contentView.optionListView.cellForItem(at: indexPath) as? OptionListCell else { return }
        selectedIndexPaths.insert(indexPath)
        cell.selectedStyle()
    }

    func unselectedStyleFor(_ option: Option) {
        guard let indexPath = dataSource.indexPath(for: option) else { return }
        guard let cell = contentView.optionListView.cellForItem(at: indexPath) as? OptionListCell else { return }
        cell.unselectedStyle()
    }

    func consideredStyleFor(_ option: Option) {
        guard let indexPath = dataSource.indexPath(for: option) else { return }
        guard let cell = contentView.optionListView.cellForItem(at: indexPath) as? OptionListCell else { return }
        consideredIndexPaths.insert(indexPath)
        cell.consideredStyle()
    }
}
