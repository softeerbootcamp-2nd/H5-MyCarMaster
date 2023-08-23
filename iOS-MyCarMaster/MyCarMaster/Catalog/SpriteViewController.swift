//
//  SpriteViewController.swift
//  Catalog
//
//  Created by SEUNGMIN OH on 2023/08/23.
//

import UIKit
import SpriteKit

class RotationView: UIView {
    
    private let spriteImage = UIImage(named: "sprite")!
    
    lazy var imageView = UIImageView().then { imageView in
        imageView.image = spriteImage
        imageView.contentMode = .scaleAspectFill
    }
    
    var imageSize: CGSize {
        return CGSize(width: imageView.frame.size.width, height: imageView.frame.size.height / 60)
    }
    
    var imageViewRatio: CGFloat {
        return (imageView.image!.size.height / 60) / imageView.image!.size.width
    }
    var imageRatio: CGFloat {
        return (spriteImage.size.height / 60) / spriteImage.size.width
    }
    var wholeImageRatio: CGFloat {
        return spriteImage.size.height / spriteImage.size.width
    }
    var imageViewSize: CGSize {
        return imageView.frame.size
    }
    var oneImageViewSize: CGSize {
        return CGSize(width: imageView.frame.size.width, height: imageView.frame.size.height / 60)
    }
    var imageSizeInImageView: CGSize {
        return imageView.image!.size
    }

    var oneImageSize: CGSize {
        return CGSize(width: spriteImage.size.width, height: spriteImage.size.height / 60)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
        configureLayout()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configureUI() {
        clipsToBounds = true
        layer.borderColor = UIColor.black.cgColor
        layer.borderWidth = 1.0
        
        let panGesture = UIPanGestureRecognizer(target: self, action: #selector(handlePan))
        addGestureRecognizer(panGesture)
    }
    
    func configureLayout() {
        translatesAutoresizingMaskIntoConstraints = false
        
        addSubview(imageView)
        
        [imageView].forEach { subview in
            subview.translatesAutoresizingMaskIntoConstraints = false
        }
        
        NSLayoutConstraint.activate([
            imageView.topAnchor.constraint(equalTo: topAnchor),
            imageView.leadingAnchor.constraint(equalTo: leadingAnchor),
            imageView.trailingAnchor.constraint(equalTo: trailingAnchor),
//            imageView.widthAnchor.constraint(equalTo: widthAnchor),
            imageView.heightAnchor.constraint(equalTo: imageView.widthAnchor, multiplier: wholeImageRatio),
            heightAnchor.constraint(equalTo: imageView.heightAnchor, multiplier: 1 / 60),
        ])
    }
    
    @objc
    func handlePan(_ sender: UIPanGestureRecognizer) {
        let translation = sender.translation(in: self)
        let diff = translation.x / self.bounds.width // (0, 1)의 값을 갖는데, 1을 360도라고 생각한다.
        print("diff: \(diff)")
        
        // 몇도 돌아갔을까?
        let angle = 360 * diff
//        print("angle: \(angle)")
        
        // 몇번째 이미지를 보여줘야 할까?
        let imageNext = Int(round(angle / 60))
//        print("imageNext: \(imageNext)")
        
        // y를 몇 옮겨야 할까?
        let lastImageY = imageView.bounds.height - imageSize.height
        var windowY = self.bounds.origin.y + CGFloat(imageNext) * imageSize.height
        if windowY < 0 {
            windowY = lastImageY
        } else if lastImageY < windowY {
            windowY = 0
        }
        
        // 옮기기
        DispatchQueue.main.async {
            self.bounds = CGRect(
                origin: CGPoint(x: self.bounds.origin.x, y: windowY),
                size: self.bounds.size
            )
        }

    }
}

class SpriteViewController: UIViewController {

    let rotationView = RotationView()
    let button = UIButton().then { button in
        button.setTitle("크기 출력", for: .normal)
    }
    let button2 = UIButton().then { button in
        button.setTitle("bounds 출력", for: .normal)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .systemGray
        
        view.addSubview(rotationView)
        
        NSLayoutConstraint.activate([
            rotationView.centerYAnchor.constraint(equalTo: view.centerYAnchor),
            rotationView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            rotationView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
        ])
        
        view.addSubview(button)
        button.frame = CGRect(x: 0, y: 600, width: 100, height: 44)
        button.addTarget(self, action: #selector(buttonDidTap), for: .touchUpInside)
        view.addSubview(button2)
        button2.frame = CGRect(x: 150, y: 600, width: 100, height: 44)
        button2.addTarget(self, action: #selector(showRotationViewBounds), for: .touchUpInside)
    }
    
    @objc
    func buttonDidTap() {
        print("imageViewRatio: \(rotationView.imageViewRatio)")
        print("imageRatio: \(rotationView.imageRatio)")
        print("imageViewSize: \(rotationView.imageViewSize)")
        print("oneImageViewSize: \(rotationView.oneImageViewSize)")
        print("imageSize: \(rotationView.imageSize)")
        print("imageSizeInImageView: \(rotationView.imageView.contentClippingRect)")
        print("oneImageSize: \(rotationView.oneImageSize)")
    }
    @objc
    func showRotationViewBounds() {
        print(rotationView.bounds)
    }
}

extension UIImageView {
    var contentClippingRect: CGRect {
        guard let image = image else { return bounds }
        guard contentMode == .scaleAspectFit else { return bounds }
        guard image.size.width > 0 && image.size.height > 0 else { return bounds }

        let scale: CGFloat
        if image.size.width > image.size.height {
            scale = bounds.width / image.size.width
        } else {
            scale = bounds.height / image.size.height
        }

        let size = CGSize(width: image.size.width * scale, height: image.size.height * scale)
        let x = (bounds.width - size.width) / 2.0
        let y = (bounds.height - size.height) / 2.0

        return CGRect(x: x, y: y, width: size.width, height: size.height)
    }
}
