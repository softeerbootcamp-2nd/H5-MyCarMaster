//
//  SpriteViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/23.
//

import UIKit

class SpriteViewController: UIViewController {

    let imageCount = 60
    let sensitive = 3.0
    let imageName = "abyss_sprite"
    
    lazy var rotationView = SpriteRotationView(
        imageCount: imageCount,
        sensitive: sensitive,
        image: UIImage(named: imageName)!
    )

    lazy var descriptionLabel = UILabel().then { label in
        label.text = """
        imageCount: \(imageCount)
        sensitive: \(sensitive)
        imageName: \(imageName)
        """
        label.sizeToFit()
        label.numberOfLines = 0
        label.layer.borderWidth = 1.0
        label.layer.borderColor = UIColor.black.cgColor
    }
    
    let boundsButton = UIButton().then { button in
        button.setTitle("bounds", for: .normal)
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.black.cgColor
    }
    
    let resetButton = UIButton().then { button in
        button.setTitle("reset", for: .normal)
        button.layer.borderWidth = 1.0
        button.layer.borderColor = UIColor.black.cgColor
    }
    
    let debugLabel = UILabel().then { label in
        label.numberOfLines = 10
        label.layer.borderWidth = 1.0
        label.layer.borderColor = UIColor.black.cgColor
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .systemGray
        
        let stackView = UIStackView(arrangedSubviews: [
            boundsButton,
            resetButton
        ]).then { stackView in
            stackView.axis = .horizontal
            stackView.spacing = 5
            stackView.distribution = .fillProportionally
        }
        
        [descriptionLabel, rotationView, stackView, debugLabel].forEach { subview in
            view.addSubview(subview)
            subview.translatesAutoresizingMaskIntoConstraints = false
        }
        
        NSLayoutConstraint.activate([
            rotationView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            rotationView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            rotationView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            
            descriptionLabel.bottomAnchor.constraint(equalTo: rotationView.topAnchor, constant: -10),
            descriptionLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            
            stackView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
            stackView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20),
            stackView.topAnchor.constraint(equalTo: rotationView.bottomAnchor, constant: 10),
            
            debugLabel.topAnchor.constraint(equalTo: stackView.bottomAnchor, constant: 10),
            debugLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
            debugLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -20),
        ])

        boundsButton.addTarget(self, action: #selector(showRotationViewBounds), for: .touchUpInside)
        resetButton.addTarget(self, action: #selector(resetRotationView), for: .touchUpInside)
    }

    @objc
    func showRotationViewBounds() {
        print(rotationView.description)
    }
    
    @objc
    func resetRotationView() {
        rotationView.bounds.origin.y = 0
    }
}
