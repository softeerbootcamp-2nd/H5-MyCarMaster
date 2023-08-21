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

    init(router: Router) {
        self.router = router
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

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

    private let palisadeCell = UIView().then { view in
        view.backgroundColor = .MCM.navyBlue1
        view.layer.borderColor = UIColor.MCM.navyBlue4.cgColor
        view.layer.borderWidth = 1.0
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
        [categoryView, palisadeCell, startButton].forEach { subview in
            view.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            categoryView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: spacing),
            categoryView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding),
            categoryView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding),
            categoryView.heightAnchor.constraint(equalToConstant: 64),

            palisadeCell.topAnchor.constraint(equalTo: categoryView.bottomAnchor, constant: spacing),
            palisadeCell.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding),
            palisadeCell.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding),
            palisadeCell.heightAnchor.constraint(equalTo: palisadeCell.widthAnchor, multiplier: ratio),

            startButton.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding),
            startButton.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding),
            startButton.heightAnchor.constraint(equalToConstant: 44),
            startButton.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: -16),
        ])

        let palisadeImageView = UIImageView().then { imageView in
            imageView.image = UIImage(named: "Palisade")
        }

        let labelStackView = UIStackView().then { stackView in
            stackView.axis = .vertical
        }

        let palisadeTitleLabel = UILabel().then { label in
            label.style = .titleMedium2(nil)
            label.textColor = .MCM.black
            label.setText("펠리세이드")
        }

        let palisadePriceRangeLabel = UILabel().then { label in
            label.style = .bodySmall2(nil)
            label.textColor = .MCM.coolGrey2
            label.setText("3,896만원~")
        }

        let tagLabel = PaddingLabel(hInset: 8).then { label in
            label.style = .buttonTitleSmall(nil)
            label.textColor = .MCM.navyBlue5
            label.backgroundColor = .MCM.navyBlue4
            label.setText("New")
        }

        [palisadeImageView, labelStackView, tagLabel].forEach { subview in
            palisadeCell.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            palisadeImageView.topAnchor.constraint(equalTo: palisadeCell.topAnchor, constant: 8),
            palisadeImageView.bottomAnchor.constraint(equalTo: palisadeCell.bottomAnchor, constant: -8),
            palisadeImageView.leadingAnchor.constraint(equalTo: palisadeCell.leadingAnchor, constant: 8),
            palisadeImageView.widthAnchor.constraint(equalToConstant: 181),

            labelStackView.centerYAnchor.constraint(equalTo: palisadeCell.centerYAnchor),
            labelStackView.leadingAnchor.constraint(equalTo: palisadeImageView.trailingAnchor, constant: 8),

            tagLabel.topAnchor.constraint(equalTo: palisadeCell.topAnchor, constant: 16),
            tagLabel.trailingAnchor.constraint(equalTo: palisadeCell.trailingAnchor, constant: -24),
        ])

        [palisadeTitleLabel, palisadePriceRangeLabel].forEach { subview in
            labelStackView.addArrangedSubview(subview)
        }
    }
}
