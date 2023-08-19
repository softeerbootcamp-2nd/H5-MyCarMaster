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

    override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
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
