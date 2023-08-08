//
//  CounterViewController.swift
//  MVIFoundation
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import UIKit
import Combine

class CounterViewController: UIViewController {

    var cancellables = Set<AnyCancellable>()

    let decreaseButton: UIButton = {
        let button = UIButton()
        button.setTitleColor(.black, for: [])
        button.setTitle("-", for: [])

        return button
    }()

    let valueLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 32, weight: .bold)
        label.textColor = .black
        return label
    }()

    let increaseButton: UIButton = {
        let button = UIButton()
        button.setTitleColor(.black, for: [])
        button.setTitle("+", for: [])

        return button
    }()

    let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false

        stackView.axis = .horizontal
        stackView.alignment = .center
        stackView.distribution = .equalSpacing

        return stackView
    }()

    lazy var indicator: UIActivityIndicatorView = {
        let indicator = UIActivityIndicatorView()

        indicator.frame = CGRectMake(0, 0, 50, 50)
        indicator.center = view.center

        indicator.color = .systemRed
        indicator.hidesWhenStopped = true
        indicator.style = .large

        indicator.stopAnimating()

        return indicator
    }()

    override func viewDidLoad() {
        super.viewDidLoad()

        view.backgroundColor = .white

        stackView.addArrangedSubview(decreaseButton)
        stackView.addArrangedSubview(valueLabel)
        stackView.addArrangedSubview(increaseButton)

        view.addSubview(stackView)

        view.addSubview(indicator)

        NSLayoutConstraint.activate([
            stackView.leadingAnchor.constraint(equalTo: view.layoutMarginsGuide.leadingAnchor),
            stackView.trailingAnchor.constraint(equalTo: view.layoutMarginsGuide.trailingAnchor),
            stackView.topAnchor.constraint(greaterThanOrEqualTo: view.topAnchor),
            stackView.bottomAnchor.constraint(lessThanOrEqualTo: view.bottomAnchor),
            stackView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
        ])

        decreaseButton.addTarget(self, action: #selector(decreaseButtonAction), for: .touchUpInside)
        increaseButton.addTarget(self, action: #selector(increaseButtonAction), for: .touchUpInside)
    }
}

extension CounterViewController: Reactable {

    func bindState(reactor: Counter) {
        reactor.state
            .map(\.count)
            .map { value in
                String(format: "%i", value)
            }
            .assign(to: \.text, on: valueLabel)
            .store(in: &cancellables)

        reactor.state
            .map(\.isLoading)
            .filter { $0 == false }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] _ in
                self?.indicator.stopAnimating()
            }
            .store(in: &cancellables)

        reactor.state
            .map(\.isLoading)
            .filter { $0 == true }
            .receive(on: DispatchQueue.main)
            .sink { [weak self] _ in
                self?.indicator.startAnimating()
            }
            .store(in: &cancellables)

        reactor.state
            .compactMap(\.alertMessage)
            .sink { [weak self] message in
                let alertController = UIAlertController(
                    title: nil,
                    message: message,
                    preferredStyle: .alert
                )
                alertController.addAction(UIAlertAction(
                    title: "OK",
                    style: .cancel
                ))
                self?.present(alertController, animated: false)
            }
            .store(in: &cancellables)
    }

    @objc private func decreaseButtonAction() {
        reactor?.action.send(.decrement)
    }

    @objc private func increaseButtonAction() {
        reactor?.action.send(.increment)
    }
}
