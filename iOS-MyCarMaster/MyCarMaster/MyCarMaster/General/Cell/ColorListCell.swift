//
//  ColorListCell.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/13.
//

import UIKit

final class ColorListCell: UICollectionViewCell, CellStyleSelectable, ContentSizeEstimatable {

    static var intrinsicContentSize: CGSize {
        return CGSize(width: UIScreen.main.bounds.width - 16 * 2, height: 70)
    }

    override var isSelected: Bool {
        willSet {
            if newValue {
                selectedStyle()
            } else {
                unselectedStyle()
            }
        }
    }

    private let colorImageView = UIImageView().then { imageView in
        imageView.clipsToBounds = true
        imageView.contentMode = .scaleAspectFill
    }

    private let rightContainer = UIView()

    private let titleLabel = UILabel().then { label in
        label.style = .bodyMedium1(nil)
        label.textColor = .MCM.black
    }

    private let additoryLabel = UILabel().then { label in
        label.style = .caption(nil)
        label.textColor = .MCM.navyBlue4
    }

    private let priceLabel = UILabel().then { label in
        label.style = .bodySmall1(nil)
        label.textColor = .MCM.black
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
        unselectedStyle()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        layer.borderWidth = 1.0
    }

    private func configureLayout() {
        [colorImageView, rightContainer].forEach { subview in
            contentView.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        let imageInset: CGFloat = 6
        let inset: CGFloat = 12
        NSLayoutConstraint.activate([
            colorImageView.topAnchor.constraint(equalTo: contentView.topAnchor, constant: imageInset),
            colorImageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: imageInset),
            colorImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -imageInset),
            colorImageView.heightAnchor.constraint(equalToConstant: Self.intrinsicContentSize.height - 2 * imageInset),
            colorImageView.widthAnchor.constraint(equalTo: colorImageView.heightAnchor, multiplier: 16 / 9),

            rightContainer.topAnchor.constraint(equalTo: contentView.topAnchor, constant: inset),
            rightContainer.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -inset),
            rightContainer.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -inset),
            rightContainer.leadingAnchor.constraint(equalTo: colorImageView.trailingAnchor, constant: inset),
        ])

        configureRightContainer()
    }

    private func configureRightContainer() {
        [titleLabel, additoryLabel, priceLabel].forEach { subview in
            rightContainer.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: rightContainer.topAnchor),
            titleLabel.leadingAnchor.constraint(equalTo: rightContainer.leadingAnchor),

            additoryLabel.leadingAnchor.constraint(equalTo: rightContainer.leadingAnchor),
            additoryLabel.bottomAnchor.constraint(equalTo: rightContainer.bottomAnchor),

            priceLabel.trailingAnchor.constraint(equalTo: rightContainer.trailingAnchor),
            priceLabel.bottomAnchor.constraint(equalTo: rightContainer.bottomAnchor),
        ])
    }
}

// MARK: - API
extension ColorListCell {
    func configure(with stateConvertible: ColorListCellStateConvertible) {
        let state = stateConvertible.colorListCellState
//        if let colorImageURL = state.colorImageURL {
            // Image가 메모리에 캐시됨
//            colorImageView.image = UIImage(contentsOfFile: colorImageURL.absoluteString)
//        } else {
            // TODO: EmptyView
            colorImageView.image = UIImage(named: "A2B")
//        }
        titleLabel.setText(state.titleName)
        additoryLabel.setText("\(state.model) 구매자의 \(state.ratio.formatted(style: .percent))가 선택")
        priceLabel.setText("+\(state.price.formatted(style: .currency))")
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct ColorListCellPreviews_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            let exterior = Exterior(model: "펠리세이드", name: "어비스블랙펄", price: 10000000, ratio: 54, colorImgURL: nil, coloredImgURL: nil)
            UIViewPreview {
                let cell = ColorListCell()
                cell.configure(with: exterior)
                return cell
            }
            UIViewPreview {
                let cell = ColorListCell()
                cell.configure(with: exterior)
                cell.selectedStyle()
                return cell
            }
            UIViewPreview {
                let cell = ColorListCell()
                cell.configure(with: exterior)
                return cell
            }
            UIViewPreview {
                let cell = ColorListCell()
                cell.configure(with: exterior)
                return cell
            }
        }
    }
}
#endif
