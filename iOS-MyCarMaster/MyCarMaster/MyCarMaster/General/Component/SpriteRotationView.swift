//
//  SpriteRotationView.swift
//  MyCarMaster
//
//  Created by SEUNGMIN OH on 2023/08/24.
//

import UIKit

final class SpriteRotationView: UIView {

    // MARK: Property

    /// 스크롤 대상이 되는 세로로 긴 이미지
    private var spriteImage: UIImage! {
        didSet {
            imageView.image = spriteImage
        }
    }

    /// SpriteImage 내부에 있는 사진의 개수
    private var imageCount: Int

    /// 가로로 한 번 스크롤 했을 때, 회전할 수 있는 바퀴 수
    /// 0 이하의 값일 때에는, 1로 설정된다.
    private var sensitive: CGFloat

    /// ImageViewHeight / ImageCount 만큼의 높이를 기준으로 동작한다.
    /// ImageSize를 기준으로 설정하면, float의 정밀도 때문에 오차가 발생한다.
    private var imageSize: CGSize {
        return CGSize(width: imageView.frame.size.width, height: imageView.frame.size.height / CGFloat(imageCount))
    }

    private var wholeImageRatio: CGFloat {
        return spriteImage.size.height / spriteImage.size.width
    }

    private var imageViewHeightAnchor: NSLayoutConstraint?
    private var viewHeightAnchor: NSLayoutConstraint?

    override var description: String {
        return """
        Bounds: \(bounds)
        ImageViewSize: \(imageSize)
        ImageViewImageSize: \(imageView.image!.size)
        ImageSize: \(spriteImage.size)
        WholeImageRatio: \(wholeImageRatio)
        OneImageRatio: \(imageSize.height / imageSize.width)
        """
    }

    /// 스크롤이 시작하는 이미지의 Y값을 나타낸다.
    private var startingY: CGFloat = 0

    /// imageView.bounds.origin.y가 될 수 있는 최대 y값
    private lazy var imageViewMaxY = imageView.bounds.height - imageSize.height

    // MARK: View
    private lazy var imageView = UIImageView().then { imageView in
        imageView.image = spriteImage
        imageView.contentMode = .scaleAspectFill
    }

    init(image: UIImage, imageCount: Int, sensitive: CGFloat) {
        self.imageCount = imageCount
        self.sensitive = sensitive
        self.spriteImage = image

        super.init(frame: .zero)
        configureUI()
        configureLayout()
        updateLayout()
    }

    init() {
        self.imageCount = 60
        self.sensitive = 1.0
        super.init(frame: .zero)
        configureUI()
        configureLayout()
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func configureUI() {
        clipsToBounds = true

        let panGesture = UIPanGestureRecognizer(target: self, action: #selector(handlePan))
        addGestureRecognizer(panGesture)
    }

    private func configureLayout() {
        translatesAutoresizingMaskIntoConstraints = false

        addSubview(imageView)

        [imageView].forEach { subview in
            subview.translatesAutoresizingMaskIntoConstraints = false
        }

        NSLayoutConstraint.activate([
            imageView.topAnchor.constraint(equalTo: topAnchor),
            imageView.leadingAnchor.constraint(equalTo: leadingAnchor),
            imageView.trailingAnchor.constraint(equalTo: trailingAnchor),
        ])
    }

    private func updateLayout() {
        if let imageViewHeightAnchor, let viewHeightAnchor {
            imageView.removeConstraint(imageViewHeightAnchor)
            removeConstraint(viewHeightAnchor)
        }

        viewHeightAnchor = heightAnchor.constraint(equalTo: imageView.heightAnchor, multiplier: 1 / CGFloat(imageCount))
        imageViewHeightAnchor = imageView.heightAnchor.constraint(equalTo: imageView.widthAnchor, multiplier: wholeImageRatio)
        viewHeightAnchor?.isActive = true
        imageViewHeightAnchor?.isActive = true
        layoutIfNeeded()
    }
}

extension SpriteRotationView {
    @objc
    private func handlePan(_ sender: UIPanGestureRecognizer) {
        let translation = sender.translation(in: self)

        // (0, 1)의 값을 갖는데, 1을 360도라고 생각한다.
        let diff = (translation.x / self.bounds.width) * sensitive

        switch sender.state {
        case .began:
            startingY = self.bounds.origin.y
        case .changed:
            // ((360 * diff) / (360 / imageCount)) % imageCount
            let imageNext = Int(round(diff * CGFloat(imageCount))) % imageCount

            // y를 몇 옮겨야 할까?

            var windowY = startingY + CGFloat(imageNext) * imageSize.height

            // y의 최소/최대 범위는 어떻게 처리할까?
            if windowY < 0 {
                windowY += imageViewMaxY
            } else if imageViewMaxY < windowY {
                windowY -= imageViewMaxY
            }

            // 옮기기
            self.bounds = CGRect(
                origin: CGPoint(x: self.bounds.origin.x, y: windowY),
                size: self.bounds.size
            )
        case .ended:
            startingY = self.bounds.origin.y
        default:
            break
        }
    }
}

extension SpriteRotationView {
    func setImage(_ image: UIImage, imageCount: Int = 60, sensitive: CGFloat = 1.0) {
        self.spriteImage = image
        self.imageCount = imageCount
        self.sensitive = sensitive
        updateLayout()
    }
}
