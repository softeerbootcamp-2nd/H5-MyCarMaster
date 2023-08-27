//
//  ModelSelectionViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import UIKit

import MCMResource

final class ModelSelectionViewController: UIViewController {

    // MARK: Property
    private let padding: CGFloat = 16
    private let spacing: CGFloat = 24
    private let ratio: CGFloat = 113 / 343

    weak var router: Router?
    weak var estimationManager: EstimationManager?

    init(router: Router, estimaitonManager: EstimationManager) {
        self.router = router
        self.estimationManager = estimaitonManager
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: Mock Data
    private let palisade = Model(id: 1, name: "Palisade", imageURL: nil)

    // MARK: View
    private let categoryView = UIView().then { view in
        let label = UILabel().then { label in
            label.style = .bodyMedium1(nil)
            label.setText("SUV")
            label.textColor = .MCM.black
        }

        let imageView = UIImageView().then { imageView in
            imageView.image = UIImage(systemName: "chevron.down")
            imageView.tintColor = .MCM.black
        }

        [label, imageView].forEach { subview in
            view.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            label.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 16),

            imageView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            imageView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            imageView.widthAnchor.constraint(equalToConstant: 18),
            imageView.heightAnchor.constraint(equalToConstant: 18),
        ])

        view.layer.borderColor = UIColor.MCM.grey2.cgColor
        view.layer.borderWidth = 1.0
    }

    private let cellStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
        stackView.spacing = 12
        stackView.alignment = .fill
    }

    private let palisadeCell = ModelCell().then { cell in
        // swiftlint:disable:next force_unwrapping
        cell.configure(modelImage: UIImage(named: "Palisade")!, title: "팰리세이드", priceRange: 3896, tagTitle: "New", tagImage: nil)
        cell.selectedStyle()
    }

    private let venewCell = ModelCell().then { cell in
        // swiftlint:disable:next force_unwrapping
        cell.configure(modelImage: UIImage(named: "Venue")!, title: "베뉴", priceRange: 2145, tagTitle: nil, tagImage: nil)
        cell.unselectedStyle()
    }

    private let konaCell = ModelCell().then { cell in
        // swiftlint:disable:next force_unwrapping line_length
        cell.configure(modelImage: UIImage(named: "Kona")!, title: "디 올 뉴 코나", priceRange: 2486, tagTitle: nil, tagImage: UIImage(named: "NLine")!)
        cell.unselectedStyle()
    }

    private let startButton = UIButton().then { button in
        button.style = .bodyMedium1(nil)
        button.setTitleColor(.MCM.white, for: .normal)
        button.setStyledTitle("선택 완료", for: .normal)
        button.backgroundColor = .MCM.navyBlue5
    }

    @objc
    private func nextButtonDidTap() {
        router?.showStepContainer()
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        configureUI()
        configureLayout()
        selectPalisadeModel()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)

        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationItem.largeTitleDisplayMode = .always
        navigationController?.setLargeTitleStyle(.headlineSmall(.init(textColor: .MCM.black)))
        navigationItem.title = "모델을 선택해주세요"
    }

    private func configureUI() {
        view.backgroundColor = .MCM.white
        startButton.addTarget(self, action: #selector(nextButtonDidTap), for: .touchUpInside)
    }

    private func configureLayout() {
        [categoryView, cellStackView, startButton].forEach { subview in
            view.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            categoryView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: spacing),
            categoryView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding),
            categoryView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding),
            categoryView.heightAnchor.constraint(equalToConstant: 64),

            cellStackView.topAnchor.constraint(equalTo: categoryView.bottomAnchor, constant: spacing),
            cellStackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding),
            cellStackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding),
            cellStackView.heightAnchor.constraint(equalToConstant: 363),

            startButton.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding),
            startButton.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding),
            startButton.heightAnchor.constraint(equalToConstant: 44),
            startButton.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -16),
        ])

        [palisadeCell, venewCell, konaCell].forEach { cell in
            cellStackView.addArrangedSubview(cell)
        }
    }
}

// MARK: Interaction
extension ModelSelectionViewController {
    private func selectPalisadeModel() {
        estimationManager?.update(\Estimation.model, value: palisade)
    }
}

final class ModelCell: UICollectionViewCell {

    override var intrinsicContentSize: CGSize {
        return CGSize(width: 343, height: 113)
    }

    private let modelImageView = UIImageView()

    private let labelStackView = UIStackView().then { stackView in
        stackView.axis = .vertical
    }

    private let modelTitleLabel = UILabel().then { label in
        label.style = .titleMedium2(nil)
        label.textColor = .MCM.black
    }

    private let modelPriceRangeLabel = UILabel().then { label in
        label.style = .bodySmall2(nil)
        label.textColor = .MCM.coolGrey2
    }

    private let tagLabel = PaddingLabel(hInset: 8).then { label in
        label.style = .buttonTitleSmall(nil)
        label.textColor = .MCM.navyBlue5
        label.backgroundColor = .MCM.navyBlue4
    }

    private let tagImageView = UIImageView()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
    }

    private func configureLayout() {
        [modelImageView, labelStackView, tagLabel, tagImageView].forEach { subview in
            addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            modelImageView.topAnchor.constraint(equalTo: topAnchor, constant: 8),
            modelImageView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -8),
            modelImageView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 8),
            modelImageView.widthAnchor.constraint(equalToConstant: 181),

            labelStackView.centerYAnchor.constraint(equalTo: centerYAnchor),
            labelStackView.leadingAnchor.constraint(equalTo: modelImageView.trailingAnchor, constant: 8),

            tagLabel.topAnchor.constraint(equalTo: topAnchor, constant: 16),
            tagLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -24),

            tagImageView.topAnchor.constraint(equalTo: topAnchor, constant: 16),
            tagImageView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -24),
            tagImageView.widthAnchor.constraint(equalToConstant: 66),
            tagImageView.heightAnchor.constraint(equalToConstant: 18),
        ])

        [modelTitleLabel, modelPriceRangeLabel].forEach { subview in
            labelStackView.addArrangedSubview(subview)
        }
    }
}

extension ModelCell {
    func configure(modelImage: UIImage, title: String, priceRange: Int, tagTitle: String?, tagImage: UIImage?) {
        modelImageView.image = modelImage
        modelTitleLabel.setText(title)
        modelPriceRangeLabel.setText("\(priceRange)만원~")
        tagLabel.isHidden = true
        tagImageView.isHidden = true
        if let tagTitle {
            tagLabel.setText(tagTitle)
            tagLabel.isHidden = false
        } else {
            tagImageView.image = tagImage
            tagImageView.isHidden = false
        }
    }

    func selectedStyle() {
        backgroundColor = .MCM.navyBlue1
        layer.borderColor = UIColor.MCM.navyBlue4.cgColor
        layer.borderWidth = 1.0
    }

    func unselectedStyle() {
        backgroundColor = .MCM.grey1
        layer.borderColor = UIColor.MCM.coolGrey1.cgColor
        layer.borderWidth = 1.0
    }
}
