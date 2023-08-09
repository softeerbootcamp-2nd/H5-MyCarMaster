#  MCMResource

## Typeface
선언적으로 폰트 및 텍스트 스타일을 적용할 수 있는 시스템

### Example
```swift
private let subTitleLabel: UILabel = {
    let label = UILabel()
    label.style = .bodyLarge2
    label.setText("마이 카마스터와 함께해요")
    label.textAlignment = .center
    label.textColor = .white

    label.sizeToFit()
    return label
}()

private(set) var startButton: UIButton = {
    let button = UIButton()
    button.backgroundColor = .white
    button.style = .bodyMedium2
    button.setStyledTitle("마이 카마스터 시작하기", for: .normal)
    button.setTitleColor(.black, for: .normal)
    return button
}()
```

### Note
* LineHeight을 지정했을 때 글자가 하단에 붙는 현상
  * 수정 전(왼쪽), 수정 후(오른쪽)
  * <img width="283" alt="image" src="https://github.com/softeerbootcamp-2nd/H5-MyCarMaster/assets/46219689/ca5cb687-148a-40bc-842a-5a74c09b2e4e"><img width="287" alt="image" src="https://github.com/softeerbootcamp-2nd/H5-MyCarMaster/assets/46219689/2ac2f2d6-e083-40f7-a6f5-d68deb720ebe">

### Reference
[Font System](https://github.com/blueapron/styled-text/blob/master/Sources/StyledText/Classes/TextStyle.swift#L23)
