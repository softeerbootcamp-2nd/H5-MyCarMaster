//
//  StepBottomViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/21.
//

import Combine
import UIKit

import MVIFoundation

final class StepBottomViewController: UIViewController {

    var cancellables = Set<AnyCancellable>()

    private var stepBottomView: StepBottomView {
        return view as? StepBottomView ?? StepBottomView()
    }

    override func loadView() {
        view = StepBottomView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        bindAction()
    }

    private func bindAction() {
        stepBottomView.addTarget(self, \.leftButton, action: #selector(leftButtonDidTap), for: .touchUpInside)
        stepBottomView.addTarget(self, \.rightButton, action: #selector(rightButtonDidTap), for: .touchUpInside)
        stepBottomView.addTarget(self, \.summaryButton, action: #selector(summaryButtonDidTap), for: .touchUpInside)
    }
}

extension StepBottomViewController {
    @objc
    private func leftButtonDidTap() {
        reactor?.action.send(.leftButtonDidTap)
    }

    @objc
    private func rightButtonDidTap() {
        reactor?.action.send(.rightButtonDidTap)
    }

    @objc
    private func summaryButtonDidTap() {
        reactor?.action.send(.summaryButtonDidTap)
    }
}

extension StepBottomViewController: Reactable {
    func bindState(reactor: StepBottomReactor) {
        reactor.state.map(\.leftButtonTitle)
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] title in
                self?.stepBottomView.updateLeftButtonTitle(title)
            }
            .store(in: &cancellables)

        reactor.state.map(\.rightButtonTitle)
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] title in
                self?.stepBottomView.updateRightButtonTitle(title)
            }
            .store(in: &cancellables)

        reactor.state.map(\.totalPrice)
            .removeDuplicates()
            .receive(on: DispatchQueue.main)
            .sink { [weak self] totalPrice in
                self?.stepBottomView.updateTotalPrice(totalPrice)
            }
            .store(in: &cancellables)
    }
}
