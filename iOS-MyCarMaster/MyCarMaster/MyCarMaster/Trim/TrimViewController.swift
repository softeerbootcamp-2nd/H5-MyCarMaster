//
//  TrimViewController.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/09.
//

import UIKit
import MVIFoundation
import MCMNetwork

final class TrimViewController: UIViewController {

    private var trimView: TrimView {
        return view as? TrimView ?? TrimView()
    }

    override func loadView() {
        super.loadView()
        view = TrimView(frame: .zero)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
    }
}
