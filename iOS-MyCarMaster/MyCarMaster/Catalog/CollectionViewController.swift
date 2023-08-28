//
//  CollectionViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/23.
//

import UIKit

final class Cell: UICollectionViewCell {
    override var isSelected: Bool {
        didSet {
            debugTextView.text += "\ncell(\(indexPath!)) - property(isSelected): \(isSelected)"
        }
    }
    var debugTextView: UITextView!
    var indexPath: IndexPath!
}

final class CollectionViewController: UIViewController {
    let collectionView = UICollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())
    let debugTextView = UITextView()
    
    let data = Array(0..<10)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .white
        
        view.addSubview(collectionView)
        collectionView.frame = view.bounds
        collectionView.dataSource = self
        collectionView.delegate = self
        collectionView.register(Cell.self, forCellWithReuseIdentifier: "cell")
        
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width: UIScreen.main.bounds.width, height: 40) // 각 셀의 크기 설정
        collectionView.setCollectionViewLayout(layout, animated: true)
        
        
        view.addSubview(debugTextView)
        debugTextView.frame = CGRect(x: 0, y: collectionView.frame.maxY - 230, width: collectionView.frame.width, height: 200)
        
        collectionView.reloadData()
        
        debugTextView.text += "3초뒤 index:3 셀 선택"
        DispatchQueue.main.asyncAfter(deadline: .now() + 3) { [self] in
            let indexPath = IndexPath(item: 3, section: 0)
            debugTextView.text += "\ncollectionView.selectItem(at:\(indexPath),anmimated:,scrollPosition:)"
            collectionView.selectItem(at: .init(item: 3, section: 0), animated: false, scrollPosition: [])
        }
    }
}

extension CollectionViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return data.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cell", for: indexPath) as! Cell
        let label = UILabel(frame: cell.contentView.bounds)
        label.textAlignment = .center
        label.text = "\(data[indexPath.item])"
        cell.contentView.addSubview(label)
        cell.debugTextView = debugTextView
        cell.indexPath = indexPath
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        debugTextView.text += "\ncell(\(indexPath)) - \(#function)"
        return true
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        debugTextView.text += "\ncell(\(indexPath)) - \(#function)"
        let cell = collectionView.cellForItem(at: indexPath)!
        cell.layer.borderColor = UIColor.systemPink.cgColor
        cell.layer.borderWidth = 3.0
    }
    
    func collectionView(_ collectionView: UICollectionView, didDeselectItemAt indexPath: IndexPath) {
        debugTextView.text += "\ncell(\(indexPath)) - \(#function)"
        let cell = collectionView.cellForItem(at: indexPath)!
        cell.layer.borderColor = nil
        cell.layer.borderWidth = 0
    }
}
