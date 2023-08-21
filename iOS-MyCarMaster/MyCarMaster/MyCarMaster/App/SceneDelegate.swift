//
//  SceneDelegate.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/01.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    var router: Router?

    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        window?.makeKeyAndVisible()

        router = Router(window: window, initialEstimation: .init(trim: nil, engine: nil, wheelDrive: nil, bodyType: nil, exterior: nil, interior: nil, selectedOptions: [], consideredOptions: [], selectedOptionsTotalPrice: 0, totalPrice: 0))
        router?.start()
    }
}
