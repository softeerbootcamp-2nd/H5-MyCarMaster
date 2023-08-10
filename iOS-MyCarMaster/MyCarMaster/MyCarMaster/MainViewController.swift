//
//  MainViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/10.
//

import UIKit

final class MainViewController: UIViewController {

    let stepNavigatorView = StepNavigatorView()
    var stepViewController: UIViewController?
    let estimationView = EstimationView()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(stepNavigatorView)
        stepNavigatorView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(estimationView)
        estimationView.translatesAutoresizingMaskIntoConstraints = false

        changeStepTo(TrimViewController())
    }

    private func changeStepTo(_ stepViewController: UIViewController) {
        self.stepViewController?.willMove(toParent: nil)
        self.stepViewController?.view.removeFromSuperview()
        self.stepViewController?.removeFromParent()

        self.stepViewController = stepViewController

        addChild(stepViewController)
        view.addSubview(stepViewController.view)
        stepViewController.view.translatesAutoresizingMaskIntoConstraints = false
        configureLayout()
        stepViewController.didMove(toParent: self)
    }

    private func configureLayout() {
        guard let stepViewController else { return }

        NSLayoutConstraint.activate([
            stepNavigatorView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            stepNavigatorView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stepNavigatorView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            stepNavigatorView.heightAnchor.constraint(equalToConstant: 104),

            estimationView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            estimationView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
            estimationView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            estimationView.heightAnchor.constraint(equalToConstant: 143),

            stepViewController.view.topAnchor.constraint(equalTo: stepNavigatorView.bottomAnchor),
            stepViewController.view.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stepViewController.view.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            stepViewController.view.bottomAnchor.constraint(equalTo: estimationView.topAnchor),
        ])
    }
}

#if canImport(SwiftUI)
import SwiftUI

struct MainViewController_Previews: PreviewProvider {
    static let vc = MainViewController()

    static var previews: some View {

        UIViewControllerPreview {
            vc.estimationView.configure(with: 93896000)
            return vc
        }
    }
}
#endif
