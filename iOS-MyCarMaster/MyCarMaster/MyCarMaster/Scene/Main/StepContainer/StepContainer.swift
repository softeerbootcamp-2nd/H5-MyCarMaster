//
//  StepContainer.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import Combine
import UIKit

import MVIFoundation

final class StepContainer: UIViewController {

    // MARK: Property
    var cancellables = Set<AnyCancellable>()

    // MARK: View
    private let navigationTitleButton = UIButton().then { button in
        button.style = .titleLarge2(nil)
        button.setStyledTitle("Palisade", for: .normal)
        button.setTitleColor(.MCM.black, for: .normal)
        button.setImage(UIImage(systemName: "chevron.down")!, for: .normal)
        button.tintColor = .MCM.black
        button.semanticContentAttribute = .forceRightToLeft
        button.imageEdgeInsets = UIEdgeInsets(top: 0, left: 8, bottom: 0, right: 0)
    }

    private let progressView = StepProgressView().then { progressView in
        progressView.layer.zPosition = 1
    }

    private var stepViewController: UIViewController?
    private let stepBottomViewController: StepBottomViewController
    private var stepBottomView: UIView {
        return stepBottomViewController.view
    }

    init(stepBottomViewController: StepBottomViewController) {
        self.stepBottomViewController = stepBottomViewController
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        configureUI()
        configureLayout()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.navigationBar.prefersLargeTitles = false
        navigationController?.navigationItem.largeTitleDisplayMode = .never
        navigationController?.navigationBar.tintColor = .MCM.black
    }

    private func configureViewController() {
        addChild(stepBottomViewController)
        stepBottomViewController.didMove(toParent: self)
    }

    private func configureUI() {
        view.backgroundColor = .MCM.white
        navigationItem.titleView = navigationTitleButton
        stepBottomView.layer.zPosition = 1
    }

    private func configureLayout() {
        [progressView, stepBottomView].forEach { subview in
            view.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            progressView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            progressView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            progressView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            progressView.heightAnchor.constraint(equalToConstant: 48),

            stepBottomView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stepBottomView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
            stepBottomView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            stepBottomView.heightAnchor.constraint(equalToConstant: 143),
        ])
    }

    private func updateStepViewLayout() {
        guard let stepViewController = self.stepViewController,
        let stepView = stepViewController.view else { return }

        view.addSubview(stepView)
        stepView.translatesAutoresizingMaskIntoConstraints = false

        NSLayoutConstraint.activate([
            stepView.topAnchor.constraint(equalTo: progressView.bottomAnchor),
            stepView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stepView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            stepView.bottomAnchor.constraint(equalTo: stepBottomView.topAnchor),
        ])
    }

    private func changeStepViewControllerTo(_ stepViewController: UIViewController) {
        self.stepViewController?.willMove(toParent: nil)
        self.stepViewController?.view.removeFromSuperview()
        self.stepViewController?.removeFromParent()

        self.stepViewController = stepViewController
        addChild(stepViewController)
        updateStepViewLayout()
        stepViewController.didMove(toParent: self)
    }
}

extension StepContainer: Reactable {
    func bindState(reactor: StepContainerReactor) {
        reactor.state.map(\.currentStepViewController)
            .removeDuplicates()
            .sink { [weak self] currentViewController in
                self?.changeStepViewControllerTo(currentViewController)
            }
            .store(in: &cancellables)

        reactor.state.map(\.currentStep)
            .removeDuplicates()
            .sink { [weak self] step in
                self?.progressView.updateProgress(with: step)
            }
            .store(in: &cancellables)

        reactor.state.map(\.isLoading)
            .removeDuplicates()
            .sink { isLoading in
                print("isLoading: \(isLoading)")
            }
            .store(in: &cancellables)
    }
}
