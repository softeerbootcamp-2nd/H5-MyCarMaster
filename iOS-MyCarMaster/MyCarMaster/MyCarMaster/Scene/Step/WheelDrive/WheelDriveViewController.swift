//
//  WheelDriveViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/11.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class WheelDriveViewController: UIViewController {

    typealias ListCellClass = BasicListCell
    typealias DataSource = UICollectionViewDiffableDataSource<Section, WheelDrive>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, WheelDrive>

    enum Section {
        case wheelDrive
    }

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    var selectedWheelDrive: WheelDrive?

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

    private var contentView: WheelDriveView<ListCellClass> {
        return view as? WheelDriveView ?? WheelDriveView()
    }

    override func loadView() {
        view = WheelDriveView<ListCellClass>(frame: .zero)
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

extension WheelDriveViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let wheelDrive = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.wheelDriveDidSelect(wheelDrive))
        }
    }
}

extension WheelDriveViewController: Reactable {
    func bindState(reactor: WheelDriveReactor) {
        reactor.state.map(\.selectedWheelDrive)
            .dropFirst()
            .sink { [weak self] wheelDrive in
                if let wheelDrive {
                    self?.selectItemFor(wheelDrive)
                    return
                }

                if let firstWheelDrive = self?.dataSource.itemIdentifier(
                    for: .init(item: 0, section: 0)
                ) {
                    reactor.action.send(.wheelDriveDidSelect(firstWheelDrive))
                }
                return
            }
            .store(in: &cancellables)

        reactor.state.map(\.wheelDriveList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] wheelDriveList in
                var snapshot = Snapshot()
                snapshot.appendSections([.wheelDrive])
                snapshot.appendItems(wheelDriveList)
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

extension WheelDriveViewController {
    func selectItemFor(_ wheelDrive: WheelDrive) {
        guard let indexPath = dataSource.indexPath(for: wheelDrive) else { return }
        guard let imageData = try? Data(contentsOf: wheelDrive.imageURL)
        else { return }
        contentView.previewImageView.image = UIImage(data: imageData)
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
