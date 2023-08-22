//
//  QuotationViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/18.
//

import UIKit

final class QuotationViewController: UIViewController {

    private var contentView: QuotationView {
        return view as? QuotationView ?? QuotationView()
    }

    override func loadView() {
        view = QuotationView()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
    }
}
