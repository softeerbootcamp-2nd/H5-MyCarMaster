#  MCMNetwork

[Moya](https://github.com/Moya/Moya)의 Interface를 참고하여 구현한 Combine을 활용하는 네트워크 레이어

### 가능한 Task
- `requestPlain`, `requestData`, `requestParameters`, `download` 

### 추가 해야 할 사항
- URLSession에 캐시정책을 주입하여, Response를 캐싱
- URLProtocol을 활용해, 네트워크 Request, Response를 테스트
- 네트워킹 과정에서 발생하는 오류에 대한 정책 구현

### 레퍼런스
[Moya](https://github.com/Moya/Moya)
[DownlaodTaskPublisher](https://theswiftdev.com/how-to-download-files-with-urlsession-using-combine-publishers-and-subscribers/)
[CustomPublisher](https://www.donnywals.com/understanding-combines-publishers-and-subscribers/)
