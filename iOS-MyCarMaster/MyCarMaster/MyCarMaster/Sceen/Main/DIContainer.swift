//
//  DIContainer.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/20.
//

import UIKit

final class DIContainer {
    static func resolveMainViewController() -> UIViewController {
        let mainViewController = MainViewController()
        let router = Router(entryStep: .trim)
        let reactor = MainReactor(
            initialState: .init(
                isLoading: false,
                currentStepViewController: resolveViewController(for: .trim)
            ),
            router: router
        )
        mainViewController.reactor = reactor
        return mainViewController
    }
    
    static func resolveViewController(for step: Step) -> UIViewController {
        switch step {
        case .trim:
            return TrimViewController()
        case .engine:
            return EngineViewController()
        case .wheelDrive:
            return WheelDriveViewController()
        case .bodyType:
            return BodyTypeViewController()
        case .exterior:
            return ExteriorViewController()
        case .interior:
            return InteriorViewController()
        case .option:
            return OptionViewController()
        case .quotation:
            return QuotationViewController()
        }
    }
}
