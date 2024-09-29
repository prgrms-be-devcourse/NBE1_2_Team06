# EmergencyAssistant-Server
응급 비서(실시간 응급실 현황 조회 서비스) 서버 레파지토리입니다.

# CI/CD

- 블루-그린 배포방식으로 무중단 배포 구현

# 아키텍쳐

![image](https://github.com/COS-project/cos-backend/assets/128073698/de0f406e-4b73-477f-bba3-f6caaaead435)

[지속성장가능한 소프트웨어 개발하는 방법](https://geminikims.medium.com/%EC%A7%80%EC%86%8D-%EC%84%B1%EC%9E%A5-%EA%B0%80%EB%8A%A5%ED%95%9C-%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4%EB%A5%BC-%EB%A7%8C%EB%93%A4%EC%96%B4%EA%B0%80%EB%8A%94-%EB%B0%A9%EB%B2%95-97844c5dab63)

# 멀티모듈 구조

응급 비서(Emergency-Assistant) 프로젝트는 멀티 모듈 구조로 설계하였습니다.

```
📁 ea-application # Runnable
📁 ea-common # 공통
📁 ea-domain # Business-Layer, Implementation-Layer
📁 ea-infra # Data-Access-Layer, Kafka, Redis
```

- [ea-application](https://github.com/prgrms-be-devcourse/NBE1_2_Team06/blob/develop/ea-application/README.md)
- [ea-common](https://github.com/prgrms-be-devcourse/NBE1_2_Team06/blob/develop/ea-common/README.md)
- [ea-domain](https://github.com/prgrms-be-devcourse/NBE1_2_Team06/blob/develop/ea-domain/README.md)
- [ea-infra](https://github.com/prgrms-be-devcourse/NBE1_2_Team06/blob/develop/ea-infra/README.md)

# 패키지 구조

```
com
 ㄴ nbe2
    ㄴ $module
        ㄴ emergencyroom
        ㄴ user
        ...
```
