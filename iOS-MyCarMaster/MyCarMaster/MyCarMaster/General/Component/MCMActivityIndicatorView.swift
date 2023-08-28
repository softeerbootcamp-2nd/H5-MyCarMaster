//
//  MCMActivityIndicatorView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/28.
//

import UIKit

extension UIViewController {
    func showIndicator() {
        let activityIndicator = MCMActivityIndicator.shared
        view.addSubview(activityIndicator)

        MCMActivityIndicator.centerXAnchor = activityIndicator.centerXAnchor.constraint(equalTo: view.centerXAnchor)
        MCMActivityIndicator.centerYAnchor = activityIndicator.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        activityIndicator.startAnimating()
    }

    func hideIndicator() {
        let activityIndicator = MCMActivityIndicator.shared
        activityIndicator.removeConstraints([
            MCMActivityIndicator.centerXAnchor,
            MCMActivityIndicator.centerYAnchor,
        ])
        activityIndicator.removeFromSuperview()
        MCMActivityIndicator.centerXAnchor = nil
        MCMActivityIndicator.centerYAnchor = nil
    }
}

final class MCMActivityIndicator: UIImageView {

    static let shared = MCMActivityIndicator()

    static var centerXAnchor: NSLayoutConstraint!
    static var centerYAnchor: NSLayoutConstraint!

    private init() {
        super.init(frame: .zero)
        self.image = UIImage(named: "Loading")
        translatesAutoresizingMaskIntoConstraints = false
        layer.zPosition = 100

        NSLayoutConstraint.activate([
            heightAnchor.constraint(equalToConstant: 60),
            widthAnchor.constraint(equalToConstant: 60),
        ])
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func startAnimating() {
        isHidden = false

        MCMActivityIndicator.centerXAnchor.isActive = true
        MCMActivityIndicator.centerYAnchor.isActive = true

        rotate()
    }

    override func stopAnimating() {
        isHidden = true
        removeRotation()
    }

    private func rotate() {
        let rotation = CABasicAnimation(keyPath: "transform.rotation.z")
        rotation.toValue = NSNumber(value: Double.pi * 2)
        rotation.duration = 1
        rotation.isCumulative = true
        rotation.repeatCount = Float.greatestFiniteMagnitude
        layer.add(rotation, forKey: "rotationAnimation")
    }

    private func removeRotation() {
        layer.removeAnimation(forKey: "rotationAnimation")
    }
}
