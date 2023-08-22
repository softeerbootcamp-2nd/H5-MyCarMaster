//
//  ComponentViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/22.
//

import UIKit

final class ComponentViewController: UIViewController {
    
    let paddingButton1 = PaddingButton(vertical: 2, horizontal: 4).then { button in
        button.style = .buttonTitleSmall(nil)
        button.configureUI(titleColor: .MCM.white, backgroundColor: .MCM.gold3)
        button.setStyledTitle("Padding Button inset: (2, 4)", for: .normal)
    }
    
    let paddingButton2 = PaddingButton(vertical: 8, horizontal: 16).then { button in
        button.style = .buttonTitleSmall(nil)
        button.configureUI(titleColor: .MCM.white, backgroundColor: .MCM.gold3)
        button.setStyledTitle("Padding Button inset: (8, 16)", for: .normal)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .white
        
        let stackView = UIStackView(arrangedSubviews: [
            paddingButton1,
            paddingButton2,
        ]).then { stackView in
            stackView.axis = .vertical
            stackView.spacing = 20
            stackView.alignment = .center
            stackView.distribution = .equalCentering
            stackView.translatesAutoresizingMaskIntoConstraints = false
        }
        
        view.addSubview(stackView)
        NSLayoutConstraint.activate([
            stackView.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            stackView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            stackView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
//            stackView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor),
        ])
        
    }
}
