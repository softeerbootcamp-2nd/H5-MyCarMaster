//
//  BasicListCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

import MCMResource

final class BasicListCell: UICollectionViewCell {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: UIScreen.main.bounds.width - 16 * 2, height: 118)
    }

    private let leftContainer: UIView = {
        let view = UIView()
        return view
    }()

    private let rightContainer: UIView = {
        let view = UIView()
        return view
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.style = .titleMedium1
        label.textColor = .MCM.black
        return label
    }()

    private var descriptionLabelBottomToLeftContainer: NSLayoutConstraint!

    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium2
        label.textColor = .MCM.grey3
        label.numberOfLines = 0
        label.lineBreakStrategy = .hangulWordPriority
        label.setContentCompressionResistancePriority(.defaultLow, for: .vertical)
        label.setContentHuggingPriority(.defaultLow, for: .horizontal)
        label.setContentHuggingPriority(.defaultLow, for: .vertical)
        return label
    }()

    private lazy var detailButton: UIButton = {
        let button = UIButton()
        button.style = .bodySmall2
        button.setStyledTitle("자세히 보기 >", for: .normal)
        button.setTitleColor(.MCM.black, for: .normal)
        return button
    }()

    private let additoryLabel: UILabel = {
        let label = UILabel()
        label.style = .caption
        label.textColor = .MCM.navyBlue4
        return label
    }()

    private let priceLabel: UILabel = {
        let label = UILabel()
        label.style = .titleMedium2
        label.textColor = .MCM.black
        return label
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        layer.borderWidth = 1.0
        deselect()
    }

    private func configureLayout() {
        [leftContainer, rightContainer].forEach { container in
            contentView.addSubview(container)
            container.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            leftContainer.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            leftContainer.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            leftContainer.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16),

            rightContainer.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 16),
            rightContainer.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            rightContainer.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -16),
        ])

        configureLeftContainer()
        configureRightContainer()

        NSLayoutConstraint.activate([
            leftContainer.trailingAnchor.constraint(equalTo: rightContainer.leadingAnchor),
        ])
    }

    private func configureLeftContainer() {
        [titleLabel, descriptionLabel].forEach { subview in
            leftContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        // swiftlint:disable:next line_length
        descriptionLabelBottomToLeftContainer = descriptionLabel.bottomAnchor.constraint(equalTo: leftContainer.bottomAnchor)

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: leftContainer.topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            titleLabel.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),

            descriptionLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor),
            descriptionLabel.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),
            descriptionLabel.trailingAnchor.constraint(equalTo: leftContainer.trailingAnchor),

            descriptionLabelBottomToLeftContainer
        ])
    }

    private func configureRightContainer() {
        [additoryLabel, priceLabel].forEach { subview in
            rightContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            additoryLabel.topAnchor.constraint(equalTo: rightContainer.topAnchor),
            additoryLabel.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),

            priceLabel.bottomAnchor.constraint(equalTo: rightContainer.bottomAnchor),
            priceLabel.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),

            rightContainer.widthAnchor.constraint(greaterThanOrEqualTo: additoryLabel.widthAnchor, multiplier: 1.0),
        ])
    }

    private func updateConstraintsIfHasDetailButton() {
        leftContainer.addSubview(detailButton)
        detailButton.translatesAutoresizingMaskIntoConstraints = false

        descriptionLabelBottomToLeftContainer.isActive = false
        NSLayoutConstraint.activate([
            detailButton.bottomAnchor.constraint(equalTo: leftContainer.bottomAnchor),
            detailButton.leadingAnchor.constraint(equalTo: leftContainer.leadingAnchor),

            descriptionLabel.bottomAnchor.constraint(equalTo: detailButton.topAnchor, constant: -11),
        ])
    }
}

// MARK: - API
extension BasicListCell {
    func configure(with state: BasicListCellState) {
        titleLabel.setText(state.name)
        descriptionLabel.setText(state.description)
        additoryLabel.setText("\(state.model) 구매자의 \(state.ratio.formatted(style: .percent))가 선택")
        priceLabel.setText("+\(state.price.formatted(style: .currency))")
        if state.hasDetailButton {
            updateConstraintsIfHasDetailButton()
        }
    }

    func select() {
        backgroundColor = .MCM.navyBlue1
        layer.borderColor = UIColor.MCM.navyBlue4.cgColor
    }

    func deselect() {
        backgroundColor = .MCM.grey1
        layer.borderColor = UIColor.MCM.grey2.cgColor
    }
}

struct BasicListCellState {
    var model: String
    var name: String
    var description: String
    var ratio: Int
    var price: Int
    var hasDetailButton: Bool
}

extension BasicListCellState {
    init(from trim: Trim) {
        self.init(model: trim.model, name: trim.name, description: trim.description, ratio: trim.ratio, price: trim.price, hasDetailButton: true)
    }
    init(from engine: Engine) {
        self.init(model: engine.model, name: engine.name, description: engine.description, ratio: engine.ratio, price: engine.price, hasDetailButton: false)
    }
    init(from wheelDrive: WheelDrive) {
        self.init(model: wheelDrive.model, name: wheelDrive.name, description: wheelDrive.description, ratio: wheelDrive.ratio, price: wheelDrive.price, hasDetailButton: false)
struct Trim {
    let model: String
    let name: String
    let ratio: Int
    let description: String
    var price: Int
    var imageURL: URL?
}

struct Engine {
    let model: String
    let name: String
    let ratio: Int
    let description: String
    let fuelMin: Double
    let fuelMax: Double
    let power: Int
    let toque: Double
    let price: Int
    var imageURL: URL?
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct TrimListViewCellPreviews_Previews: PreviewProvider {
    static let trim = Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil)

    static let trimPreview = UIViewPreview {
        let listViewCell = BasicListCell()
        let cellState = BasicListCellState(from: trim)
        listViewCell.configure(with: cellState)
        listViewCell.select()
        return listViewCell
    }
    static var previews: some View {
        Group {
            trimPreview
                .previewDevice("iPhone SE (3rd generation)")
            trimPreview
                .previewDevice("iPhone 14 Pro")
            trimPreview
                .previewDevice("iPhone 14 Pro Max")
        }
    }
}

struct EngineListViewCellPreviews_Previews: PreviewProvider {
    static let engine = Engine(model: "펠리세이드", name: "가솔린 3.8", ratio: 54, description: "엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다", fuelMin: 8.0, fuelMax: 9.2, power: 295, toque: 36.2, price: 0, imageURL: nil)

    static let enginePreview = UIViewPreview {
        let listViewCell = BasicListCell()
        let cellState = BasicListCellState(from: engine)
        listViewCell.configure(with: cellState)
        return listViewCell
    }

    static var previews: some View {
        Group {
            enginePreview
                .previewDevice("iPhone SE (3rd generation)")
            enginePreview
                .previewDevice("iPhone 14 Pro")
            enginePreview
                .previewDevice("iPhone 14 Pro Max")
        }
    }
}
#endif
