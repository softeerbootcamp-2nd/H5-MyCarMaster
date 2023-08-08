//
//  SceneDelegate.swift
//  MVIFoundation
//
//  Created by SEUNGMIN OH on 2023/08/08.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        let viewController = CounterViewController()
        viewController.reactor = Counter()
        window?.rootViewController = viewController
        window?.makeKeyAndVisible()
    }
}
