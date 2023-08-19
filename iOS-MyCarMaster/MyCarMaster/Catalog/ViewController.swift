//
//  ViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/19.
//

import UIKit

import MCMResource

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .MCM.white
        
        let paddingButton = PaddingButton(vertical: 8, horizontal: 16)
        paddingButton.style = .buttonTitleSmall(nil)
        paddingButton.configureUI(titleColor: .MCM.white, backgroundColor: .MCM.gold3)
        paddingButton.setStyledTitle("Padding Button", for: .normal)
        paddingButton.frame.origin = CGPoint(x: 100, y: 100)
        paddingButton.sizeToFit()
        view.addSubview(paddingButton)
    }
}
