//
//  ViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

import MCMResource

class ViewController: UIViewController {
    
    lazy var componentButton = UIButton().then { button in
        button.setTitle("컴포넌트 카탈로그", for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.layer.borderColor = UIColor.black.cgColor
        button.layer.borderWidth = 1.0
        button.addTarget(self, action: #selector(showComponentView), for: .touchUpInside)
    }
    
    lazy var compressionButton = UIButton().then { button in
        button.setTitle("이미지 압축 비교", for: .normal)
        button.setTitleColor(.black, for: .normal)
        button.layer.borderColor = UIColor.black.cgColor
        button.layer.borderWidth = 1.0
        button.addTarget(self, action: #selector(showCompressionView), for: .touchUpInside)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .MCM.white
        
        let stackView = UIStackView(arrangedSubviews: [
            componentButton,
            compressionButton,
        ]).then { stackView in
            stackView.axis = .vertical
            stackView.spacing = 20
            stackView.alignment = .fill
            stackView.distribution = .equalCentering
            stackView.translatesAutoresizingMaskIntoConstraints = false
        }
        
        view.addSubview(stackView)
        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            stackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
            stackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20),
//            stackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
        ])
    }
    
    @objc
    func showComponentView() {
        let viewController = ComponentViewController()
        navigationController?.pushViewController(viewController, animated: false)
    }
    @objc
    func showCompressionView() {
        let viewController = CompressionViewController()
        navigationController?.pushViewController(viewController, animated: false)
    }
}
