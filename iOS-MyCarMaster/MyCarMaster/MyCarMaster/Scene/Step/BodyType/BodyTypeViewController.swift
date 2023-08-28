//
//  BodyTypeViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class BodyTypeViewController: UIViewController {

    typealias ListCellClass = BasicListCell
    typealias DataSource = UICollectionViewDiffableDataSource<Section, BodyType>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, BodyType>

    enum Section {
        case bodyType
    }

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    var selectedBodyType: BodyType?

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

    private var contentView: BodyTypeView<ListCellClass> {
        return view as? BodyTypeView ?? BodyTypeView()
    }

    override func loadView() {
        view = BodyTypeView<ListCellClass>(frame: .zero)
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

extension BodyTypeViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let bodyType = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.bodyTypeDidSelect(bodyType))
        }
    }
}

extension BodyTypeViewController: Reactable {
    func bindState(reactor: BodyTypeReactor) {
        reactor.state.map(\.selectedBodyType)
            .dropFirst()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] bodyType in
                if let bodyType {
                    self?.selectItemFor(bodyType)
                    reactor.action.send(.fetchBodyTypeImage(bodyType.imageURL))
                    return
                }

                if let firstBodyType = self?.dataSource.itemIdentifier(
                    for: .init(item: 0, section: 0)
                ) {
                    reactor.action.send(.bodyTypeDidSelect(firstBodyType))
                    reactor.action.send(.fetchBodyTypeImage(firstBodyType.imageURL))
                }
                return
            }
            .store(in: &cancellables)

        reactor.state.map(\.bodyTypeList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] bodyTypeList in
                var snapshot = Snapshot()
                snapshot.appendSections([.bodyType])
                snapshot.appendItems(bodyTypeList)
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

        reactor.state.compactMap(\.selectedBodyTypeImage)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] image in
                self?.contentView.previewImageView.image = image
            }
            .store(in: &cancellables)
    }
}

extension BodyTypeViewController {
    func selectItemFor(_ bodyType: BodyType) {
        guard let indexPath = dataSource.indexPath(for: bodyType) else { return }
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
