//
//  TrimViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit
import MVIFoundation
import MCMNetwork

final class TrimViewController: UIViewController {

    let trimList = [
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
        Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil),
    ]

    private var trimView: TrimView {
        return view as? TrimView ?? TrimView()
    }

    override func loadView() {
        super.loadView()
        view = TrimView(frame: .zero)
        additionalSafeAreaInsets = .init(top: 104, left: 0, bottom: 164, right: 0) // Navigation, Bottom
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        trimView.setDataSource(self)
        trimView.registerCellClass(ListViewCell.self)
    }
}

extension TrimViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return trimList.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ListViewCell.reuseIdentifier, for: indexPath) as? ListViewCell else {
            fatalError("등록되지 않은 cell입니다.")
        }
        cell.configure(with: trimList[indexPath.row], hasMoreInformation: false)
        return cell
    }
}

#if canImport(SwiftUI)
import SwiftUI

struct TrimViewController_Previews: PreviewProvider {
    static var previews: some View {
        UIViewControllerPreview {
            return TrimViewController()
        }
    }
}
#endif
