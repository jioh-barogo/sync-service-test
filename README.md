# sync-service-test
- 내부통신 라이브러리별 퍼포먼스 테스트
- 아주 기본적인 구조로 테스트 한 것이므로 100퍼센트 완벽한 값들은 아닙니다... 참조용

## 동기 요청 10000건에 대한 테스트
## 테스트 결과 1차
- 1 : name='GRPC', time=7.947
- 2 : name='RestTemplate-Connection-Pool', time=8.183
- 3 : name='retrofit2-Connection-Pool', time=17.499
- 4 : name='retrofit-Non-Connection-Pool', time=17.836
- 5 : name='RestTemplate-Non-Connection-Pool', time=19.892
- 6 : name='KAFKA-SendAndReceive', time=23.29

## 테스트 결과 2차
- 1 : name='RestTemplate-Connection-Pool', time=4.822
- 2 : name='GRPC', time=6.976
- 3 : name='KAFKA-SendAndReceive', time=21.894
- 4 : name='retrofit2-Connection-Pool', time=24.201
- 5 : name='retrofit-Non-Connection-Pool', time=25.107
- 6 : name='RestTemplate-Non-Connection-Pool', time=25.734


## 의견
- http 통신시 connection 잡는데 시간이 생각보다 많이 소모되는 것으로 보인다.
- connection-pool 만 활용해도 시간절약이 많이 되는 것으로 보인다.
- kafka 는 실시간으로 응답값을 전달받는 것은 역시 안좋다. 당연하게도 이벤트형식으로만 활용하도록 하자.
- grpc 는 proto문법을 익혀야 하며, core-library에 대한 의존도가 매우 높다.
- retrofit 은 okhttp, restTemplate은 apache httpclient 를 활용하는 단순 라이브러리일뿐이다.
- okhttp는 비동기도 지원이 된다.
- okhttp는 왜 connection-pool이 안되는지... 확인이 필요하다.
- 1:N 이 아닌 경우에 굳이 비동기통신이 필요하진 않아 보인다.
- 
