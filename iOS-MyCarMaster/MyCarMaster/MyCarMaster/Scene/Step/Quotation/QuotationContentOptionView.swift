//
//  QuotationContentOptionView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

final class QuotationContentOptionView: UIView {

    // MARK: View
    private let contentStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 18
    }

    private let titleStackView = UIStackView().then { stackView in
        stackView.axis = .horizontal
        stackView.alignment = .center
    }

    private let titleDecriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.textColor = .MCM.coolGrey2
        label.setText("옵션")
        label.sizeToFit()
    }

    private let modifyButton = UIButton().then { button in
        button.style = .bodyMedium2(nil)
        button.setTitleColor(.MCM.coolGrey2, for: .normal)
        button.setStyledTitle("변경하기 >", for: .normal)
    }

    private let selectedOptionDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.setText("추가 옵션")
        label.textColor = .MCM.black
    }

    private let selectedOptionStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 12
        stackView.alignment = .center
        stackView.distribution = .fill
    }

    private let consideredOptionDescriptionLabel = UILabel().then { label in
        label.style = .bodyMedium2(nil)
        label.setText("고민 옵션")
        label.textColor = .MCM.black
    }

    private let consideredOptionStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 12
        stackView.alignment = .center
        stackView.distribution = .fill
    }

    private let totalPriceStackView = UIStackView().then { stackView in
        stackView.axis = .horizontal
    }

    private let totalPriceDescriptionLabel = UILabel().then { label in
        label.style = .titleLarge2(nil)
        label.setText("추가 옵션 가격")
        label.textColor = .MCM.black
    }

    private let totalPriceLabel = UILabel().then { label in
        label.style = .titleLarge2(nil)
        label.textColor = .MCM.black
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        backgroundColor = .MCM.white
    }

    private func configureLayout() {
        addSubview(contentStackView)
        contentStackView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            contentStackView.topAnchor.constraint(equalTo: topAnchor),
            contentStackView.leadingAnchor.constraint(equalTo: leadingAnchor),
            contentStackView.trailingAnchor.constraint(equalTo: trailingAnchor),
            contentStackView.bottomAnchor.constraint(equalTo: bottomAnchor),
        ])

        configureContantStackViewLayout()
    }

    private func configureContantStackViewLayout() {
        [
            titleStackView,
            selectedOptionDescriptionLabel, selectedOptionStackView,
            consideredOptionDescriptionLabel, consideredOptionStackView,
            totalPriceStackView
        ].forEach { subview in
            contentStackView.addArrangedSubview(subview)
        }

        [titleDecriptionLabel, modifyButton].forEach { subview in
            titleStackView.addArrangedSubview(subview)
        }

        [totalPriceDescriptionLabel, totalPriceLabel].forEach { subview in
            totalPriceStackView.addArrangedSubview(subview)
        }
    }
}

extension QuotationContentOptionView {
    func configure(with stateConvertible: QuotationContentOptionStateConvertible) {
        let state = stateConvertible.quotationContentOptionState

        selectedOptionStackView.removeAllArrangedSubviews()
        state.selectedOptions.forEach { option in
            let optionItemView = QuotationContentOptionItemView()
            optionItemView.configure(with: option)
            optionItemView.considerButtonStyle()
            selectedOptionStackView.addArrangedSubview(optionItemView)
        }

        consideredOptionStackView.removeAllArrangedSubviews()
        state.consideredOptions.forEach { option in
            let optionItemView = QuotationContentOptionItemView()
            optionItemView.configure(with: option)
            optionItemView.selectButtonStyle()
            consideredOptionStackView.addArrangedSubview(optionItemView)
        }

        totalPriceLabel.setText("+\(state.selectedOptionsTotalPrice.formatted(style: .currency))")
    }
}

#if canImport(SwiftUI) && DEBUG
import SwiftUI

struct QuotationContentOptionPreviews_Previews: PreviewProvider {
    static var previews: some View {
        UIViewPreview {
            let view = QuotationContentOptionView()
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
            view.configure(with: quotation)
            return view
        }
    }
}
#endif
