//
//  QuotationView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/18.
//

import UIKit

final class QuotationView: UIView {

    // MARK: Property
    private let inset: CGFloat = 26

    // MARK: View
    private let scrollView = UIScrollView().then { scrollView in
        scrollView.backgroundColor = .MCM.white
    }

    private let contentView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 20
    }

    private let mainContentView = UIView()

    private let titleLabel = UILabel().then { label in
        label.style = .titleLarge1(.init(alignment: .center))
        label.textColor = .MCM.black
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
    }

    private let subtitleLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.grey3
        label.setText("카마스터 찾기를 통해 구매 상담을 할 수 있어요")
    }

    private let previewImageView = SpriteRotationView()

    private let priceDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.black
        label.setText("예상 가격")
    }

    private let priceLabel = UILabel().then { label in
        label.style = .titleLarge1(.init(alignment: .center))
        label.textColor = .MCM.black
    }

    private let quotationContentView = QuotationContentView()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
        configure(with: "")
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
    }

    private func configureLayout() {
        addSubview(scrollView)
        scrollView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: bottomAnchor),
        ])

        scrollView.addSubview(contentView)
        contentView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentView.topAnchor.constraint(equalTo: scrollView.topAnchor),
            contentView.widthAnchor.constraint(equalTo: scrollView.widthAnchor, constant: -2 * inset),
            contentView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor, constant: inset),
            contentView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor, constant: -inset),

            scrollView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor),
        ])

        configureContentViewLayout()
    }

    private func configureContentViewLayout() {
        [mainContentView, quotationContentView].forEach { subview in
            contentView.addArrangedSubview(subview)
        }

        configureMainContentViewLayout()
    }

    private func configureMainContentViewLayout() {
        [titleLabel, subtitleLabel, previewImageView, priceDescriptionLabel, priceLabel].forEach { subview in
            mainContentView.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: mainContentView.topAnchor, constant: 18),
            titleLabel.centerXAnchor.constraint(equalTo: mainContentView.centerXAnchor),
            titleLabel.widthAnchor.constraint(equalToConstant: 190),

            subtitleLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 8),
            subtitleLabel.centerXAnchor.constraint(equalTo: mainContentView.centerXAnchor),

            previewImageView.topAnchor.constraint(equalTo: subtitleLabel.bottomAnchor, constant: 12),
            previewImageView.leadingAnchor.constraint(equalTo: mainContentView.leadingAnchor),
            previewImageView.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            previewImageView.heightAnchor.constraint(equalTo: previewImageView.widthAnchor, multiplier: 170/292),

            priceDescriptionLabel.topAnchor.constraint(equalTo: previewImageView.bottomAnchor),
            priceDescriptionLabel.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            priceLabel.topAnchor.constraint(equalTo: priceDescriptionLabel.bottomAnchor, constant: 4),
            priceLabel.trailingAnchor.constraint(equalTo: mainContentView.trailingAnchor),
            mainContentView.bottomAnchor.constraint(equalTo: priceLabel.bottomAnchor),
        ])
    }
}

// MARK: - API
extension QuotationView {
    func configure(with quotationConvertible: String) {
        titleLabel.setText("펠리세이드와 함께\n드라이브 떠나볼까요?")
        previewImageView.setImage(UIImage(named: "abyss_sprite")!)
        priceLabel.setText("\(999999999.formatted(style: .currency))")

        let quotation = Quotation(
            trim: Trim(
                model: "펠리세이드",
                name: "Le Blanc",
                ratio: 0,
                description: "인기있는 주요 기능을 포함한 가성비 트림",
                price: 43460000,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/trim/leblanc.png")
            ),
            engine: Engine(
                model: "펠리세이드",
                name: "디젤 2.2 엔진",
                ratio: 60,
                description: "우수한 가속 성능으로 안정적이고 엔진의 진동이 적어 \\n조용한 드라이빙이 가능합니다.",
                fuelMin: 8.0,
                fuelMax: 9.2,
                power: 295,
                toque: 36.2,
                price: 0,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/engine/diesel.png")
            ),
            wheelDrive: WheelDrive(
                model: "펠리세이드",
                name: "2WD",
                description: "엔진 동력이 전후륜 중 한쪽으로만 전달돼 움직입니다. 차체가 가벼워 연료 효율이 높습니다.",
                ratio: 60,
                price: 0,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/wheel-drive/2-wd.png")
            ),
            bodyType: BodyType(
                model: "펠리세이드"
                , name: "7인승",
                description: "8인승 시트에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.",
                ratio: 50,
                price: 0,
                imageURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/body-type/7-seat.png")
            ),
            exterior: Exterior(
                model: "펠리세이드",
                name: "어비스 블랙 펄",
                price: 0,
                ratio: 30,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/abyss.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/exterior-color/car/abyss.png")
            ),
            interior: Interior(
                model: "펠리세이드",
                name: "퀼팅천연 (블랙)",
                price: 0,
                ratio: 60,
                colorImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/quilting-natural-black.png"),
                coloredImgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/interior-color/car/quilting-natural-black.png")
            ),
            selectedOptions: [
                Option(
                    model: "펠리세이드",
                    category: "안전",
                    name: "빌트인 캠(보조배터리 포함)",
                    price: 690000,
                    ratio: 60,
                    imgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/built-in-cam.png"),
                    summary: "빌트인 캠\'을 통해 방금 촬영된 운전 영상을 어플로 바로 확인할 수 있어요.",
                    description: "빌트인 적용된 영상기록장치로, 내비게이션 화면을 통해 영상 확인 및 앱 연동을 통해 영상 확인 및 SNS 공유가 가능합니다.",
                    tag: nil,
                    subOptions: []
                ),
                Option(
                    model: "펠리세이드",
                    category: "안전",
                    name: "빌트인 캠(보조배터리 포함)",
                    price: 690000,
                    ratio: 60,
                    imgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/built-in-cam.png"),
                    summary: "빌트인 캠\'을 통해 방금 촬영된 운전 영상을 어플로 바로 확인할 수 있어요.",
                    description: "빌트인 적용된 영상기록장치로, 내비게이션 화면을 통해 영상 확인 및 앱 연동을 통해 영상 확인 및 SNS 공유가 가능합니다.",
                    tag: nil,
                    subOptions: []
                )
            ],
            consideredOptions: [
                Option(
                    model: "펠리세이드",
                    category: "안전",
                    name: "빌트인 캠(보조배터리 포함)",
                    price: 690000,
                    ratio: 60,
                    imgURL: URL(string: "https://h5-image.s3.ap-northeast-2.amazonaws.com/palisade/option/built-in-cam.png"),
                    summary: "빌트인 캠\'을 통해 방금 촬영된 운전 영상을 어플로 바로 확인할 수 있어요.",
                    description: "빌트인 적용된 영상기록장치로, 내비게이션 화면을 통해 영상 확인 및 앱 연동을 통해 영상 확인 및 SNS 공유가 가능합니다.",
                    tag: nil,
                    subOptions: []
                )
            ],
            selectedOptionsTotalPrice: 600000,
            totalPrice: 99999999
        )
        quotationContentView.configure(with: quotation)
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct QuotationViewPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UIViewPreview {
            let view = QuotationView()
            view.configure(with: "")
            return view
        }
    }
}
#endif
