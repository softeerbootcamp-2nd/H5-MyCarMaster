//
//  CompressionViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/22.
//

import UIKit

final class CompressionViewController: UIViewController {
    let width = UIScreen.main.bounds.width
    lazy var height = width / 2
    
    lazy var originImageView = UIImageView().then { imageView in
        imageView.image = UIImage(named: "img_original")
    }
    let originLabel = UILabel().then { label in
        label.text = "Origin - 270kb"
        label.textColor = .black
    }
    
    lazy var secondImageView = UIImageView().then { imageView in
        imageView.image = UIImage(named: "img_91")
    }
    let secondLabel = UILabel().then { label in
        label.text = "91kb"
        label.textColor = .black
    }
    
    lazy var thirdImageView = UIImageView().then { imageView in
        imageView.image = UIImage(named: "img_61")
    }
    let thirdLabel = UILabel().then { label in
        label.text = "61kb"
        label.textColor = .black
    }
    
    lazy var fourthImageView = UIImageView().then { imageView in
        imageView.image = UIImage(named: "img_35")
    }
    let fourthLabel = UILabel().then { label in
        label.text = "35kb"
        label.textColor = .black
    }
    
    lazy var scrollView = UIScrollView().then { scrollView in
        scrollView.contentInset = .init(top: 0, left: 0, bottom: 100, right: 0)
    }
    
    lazy var stackView = UIStackView(arrangedSubviews: [
        originImageView, originLabel,
        secondImageView, secondLabel,
        thirdImageView, thirdLabel,
        fourthImageView, fourthLabel
    ]).then { stackView in
        stackView.axis = .vertical
        stackView.alignment = .center
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        
        view.addSubview(scrollView)
        scrollView.addSubview(stackView)
        
        [scrollView, stackView, originImageView, secondImageView, thirdImageView, fourthImageView].forEach { view in
            view.translatesAutoresizingMaskIntoConstraints = false
        }
        
        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: view.topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: view.bottomAnchor),
            
            stackView.topAnchor.constraint(equalTo: scrollView.contentLayoutGuide.topAnchor),
            stackView.leadingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.leadingAnchor),
            stackView.trailingAnchor.constraint(equalTo: scrollView.contentLayoutGuide.trailingAnchor),
            stackView.widthAnchor.constraint(equalTo: scrollView.widthAnchor, multiplier: 1.0),
            scrollView.contentLayoutGuide.heightAnchor.constraint(equalTo: stackView.heightAnchor, multiplier: 1.0),
            
            originImageView.heightAnchor.constraint(equalTo: originImageView.widthAnchor, multiplier: 1/2),
            secondImageView.heightAnchor.constraint(equalTo: secondImageView.widthAnchor, multiplier: 1/2),
            thirdImageView.heightAnchor.constraint(equalTo: thirdImageView.widthAnchor, multiplier: 1/2),
            fourthImageView.heightAnchor.constraint(equalTo: fourthImageView.widthAnchor, multiplier: 1/2),
        ])
    }
}
