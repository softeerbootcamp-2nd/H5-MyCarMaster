//
//  ExteriorImageView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/12.
//

import Combine
import UIKit

import MCMNetwork
import MVIFoundation

final class ExteriorViewController: UIViewController {

    typealias ListCellClass = ColorListCell
    typealias DataSource = UICollectionViewDiffableDataSource<Section, Exterior>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, Exterior>

    enum Section {
        case exterior
    }

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    var selectedExterior: Exterior?

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

    private var contentView: ExteriorView<ListCellClass> {
        return view as? ExteriorView ?? ExteriorView()
    }

    override func loadView() {
        view = ExteriorView<ListCellClass>(frame: .zero)
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

extension ExteriorViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        if let exterior = dataSource.itemIdentifier(for: indexPath) {
            reactor?.action.send(.exteriorDidSelect(exterior))
        }
    }
}

extension ExteriorViewController: Reactable {
    func bindState(reactor: ExteriorReactor) {
        reactor.state.map(\.selectedExterior)
            .dropFirst()
            .sink { [weak self] exterior in
                if let exterior {
                    self?.selectItemFor(exterior)
                    return
                }

                if let firstExterior = self?.dataSource.itemIdentifier(
                    for: .init(item: 0, section: 0)
                ) {
                    reactor.action.send(.exteriorDidSelect(firstExterior))
                }
                return
            }
            .store(in: &cancellables)

        reactor.state.map(\.exteriorList)
            .dropFirst()
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] exteriorList in
                var snapshot = Snapshot()
                snapshot.appendSections([.exterior])
                snapshot.appendItems(exteriorList)
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
    }
}

extension ExteriorViewController {
    func selectItemFor(_ exterior: Exterior) {
        guard let indexPath = dataSource.indexPath(for: exterior) else { return }
        DispatchQueue.global().async {
            guard let imageData = try? Data(contentsOf: exterior.coloredImgURL),
                  let image = UIImage(data: imageData)
            else { return }
            DispatchQueue.main.async {
                (self.contentView.previewView as? SpriteRotationView)?.setImage(image)
            }
        }
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
