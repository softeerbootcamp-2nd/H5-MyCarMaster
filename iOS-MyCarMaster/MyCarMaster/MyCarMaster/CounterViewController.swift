//
//  CounterViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/07.
//

import Combine
import UIKit
import MVIFoundation

final class Counter: Reactor {

    struct State {
        var count = 0
        var isLoading: Bool = false
        var alertMessage: String?
    }

    enum Mutation {
        case increaseValue
        case decreaseValue
        case setLoading(Bool)
        case setAlertMessage(String)
    }

    enum Action {
        case decrement
        case increment
    }

    let initialState: State

    init() {
        self.initialState = State(
            count: 0,
            isLoading: false
        )
    }

    func mutate(action: Action) -> AnyPublisher<Mutation, Never> {
        switch action {
        case .increment:
            return [
                Just(Mutation.setLoading(true))
                    .eraseToAnyPublisher(),
                Just(Mutation.increaseValue)
                    .delay(for: .milliseconds(500), scheduler: RunLoop.main)
                    .eraseToAnyPublisher(),
                Just(Mutation.setLoading(false))
                    .eraseToAnyPublisher(),
                Just(Mutation.setAlertMessage("increased!"))
                    .eraseToAnyPublisher()
            ]
                .concatenate()
                .eraseToAnyPublisher()

        case.decrement:
            return [
                Just(Mutation.setLoading(true))
                    .eraseToAnyPublisher(),
                Just(Mutation.decreaseValue)
                    .delay(for: .milliseconds(500), scheduler: RunLoop.main)
                    .eraseToAnyPublisher(),
                Just(Mutation.setLoading(false))
                    .eraseToAnyPublisher(),
                Just(Mutation.setAlertMessage("decreased!"))
                    .eraseToAnyPublisher()
            ]
                .concatenate()
                .eraseToAnyPublisher()
        }
    }

    func reduce(state: State, mutation: Mutation) -> State {
        var state = state
        switch mutation {
        case .increaseValue:
            state.count += 1
        case .decreaseValue:
            state.count -= 1
        case let .setLoading(isLoading):
            state.isLoading = isLoading
        case let .setAlertMessage(message):
            state.alertMessage = message
        }
        return state
    }
}

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
