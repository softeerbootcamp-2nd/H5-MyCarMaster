//
//  ListViewCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

import MCMResource

final class ListViewCell: UICollectionViewCell {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: (window?.windowScene?.screen.bounds.width ?? 375) - 16 * 2, height: 118)
    }

    let titleLabel: UILabel = {
        let label = UILabel()
        label.style = .titleMedium1
        return label
    }()

    let descriptionLabel: UILabel = {
        let label = UILabel()
        label.style = .bodyMedium2
        label.textColor = .MCM.grey3
        label.numberOfLines = 0
        label.setContentCompressionResistancePriority(.defaultLow, for: .horizontal)
        label.setContentCompressionResistancePriority(.defaultLow, for: .vertical)
        label.setContentHuggingPriority(.defaultLow, for: .horizontal)
        return label
    }()

    let additoryLabel: UILabel = {
        let label = UILabel()
        label.style = .caption
        label.textColor = .MCM.navyBlue4
        label.setContentCompressionResistancePriority(.defaultHigh, for: .horizontal)
        return label
    }()

    let priceLabel: UILabel = {
        let label = UILabel()
        label.style = .titleMedium2
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
        layer.borderColor = UIColor.MCM.navyBlue1.cgColor
        layer.borderWidth = 1.0
    }

    private func configureLayout() {
        let contentView: UIStackView = {
            let stackView = UIStackView()
            stackView.axis = .horizontal
            stackView.spacing = 4
            stackView.distribution = .equalSpacing
            stackView.alignment = .fill
            return stackView
        }()

        addSubview(contentView)
        contentView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentView.topAnchor.constraint(equalTo: topAnchor, constant: 16),
            contentView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16),
            contentView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16),
            contentView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -16),
        ])

        // MARK: LeftContainer
        let leftContainerView: UIView = {
            let view = UIView()
            view.backgroundColor = .systemYellow
            return view
        }()
        contentView.addArrangedSubview(leftContainerView)

        leftContainerView.addSubview(titleLabel)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false

        leftContainerView.addSubview(descriptionLabel)
        descriptionLabel.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: leftContainerView.topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: leftContainerView.leadingAnchor),

            descriptionLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 2),
            descriptionLabel.leadingAnchor.constraint(equalTo: leftContainerView.leadingAnchor),
            descriptionLabel.trailingAnchor.constraint(equalTo: leftContainerView.trailingAnchor),
        ])

        // MARK: RightContainer
        let rightContainerView: UIView = {
            let view = UIView()
            view.backgroundColor = .systemBrown
            return view
        }()
        contentView.addArrangedSubview(rightContainerView)

        rightContainerView.addSubview(additoryLabel)
        additoryLabel.translatesAutoresizingMaskIntoConstraints = false

        rightContainerView.addSubview(priceLabel)
        priceLabel.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            rightContainerView.widthAnchor.constraint(greaterThanOrEqualTo: additoryLabel.widthAnchor, multiplier: 1.0),

            additoryLabel.topAnchor.constraint(equalTo: rightContainerView.topAnchor),
            additoryLabel.trailingAnchor.constraint(equalTo: rightContainerView.trailingAnchor),

            priceLabel.bottomAnchor.constraint(equalTo: rightContainerView.bottomAnchor),
            priceLabel.trailingAnchor.constraint(equalTo: rightContainerView.trailingAnchor),
        ])
    }
}

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

extension ListViewCell {
    func configure(with trim: Trim, hasMoreInformation: Bool) {
        titleLabel.setText(trim.name)
        descriptionLabel.setText(trim.description)
        additoryLabel.setText("\(trim.model) 구매자의 \(trim.ratio.formatted(style: .percent))가 선택")
        priceLabel.setText(trim.price.formatted(style: .currency))
    }
    func configure(with engine: Engine, hasMoreInformation: Bool) {
        titleLabel.setText(engine.name)
        descriptionLabel.setText(engine.description)
        additoryLabel.setText("\(engine.model) 구매자의 \(engine.ratio.formatted(style: .percent))가 선택")
        priceLabel.setText(engine.price.formatted(style: .currency))
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct TrimListViewCellPreviews_Previews: PreviewProvider {
    static let trim = Trim(model: "펠리세이드", name: "Exclusive", ratio: 54, description: "실용적인 기본 기능을 갖춘 베이직 트림", price: 40440000, imageURL: nil)

    static let trimPreview = UIViewPreview {
        let listViewCell = ListViewCell()
        listViewCell.configure(with: trim, hasMoreInformation: false)
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
    static let engine = Engine(model: "펠리세이드", name: "가솔린 3.8", ratio: 54, description: "엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다", fuelMin: 8.0, fuelMax: 9.2, power: 295, toque: 36.2, price: 0, imageURL: nil)

    static let enginePreview = UIViewPreview {
        let listViewCell = ListViewCell()
        listViewCell.configure(with: engine, hasMoreInformation: false)
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
