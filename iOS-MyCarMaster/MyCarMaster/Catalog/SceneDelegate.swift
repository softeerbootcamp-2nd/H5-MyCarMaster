//
//  SceneDelegate.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowSchene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowSchene)
        window?.rootViewController = ViewController()
        window?.makeKeyAndVisible()
    }
}
