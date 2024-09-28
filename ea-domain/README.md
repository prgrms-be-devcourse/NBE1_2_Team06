# Domain Module
응급비서 어플리케이션의 도메인 모듈입니다.

- 가장 독립적이어야하기 때문에 최소한의 의존성을 갖습니다.
- 비즈니스의 플로우를 표현하는 Buissness-Layer(Service)를 갖습니다.
- Buisness-Layer에서 사용하는 컴포넌트들을 구현한 Implementation-Layer를 갖습니다.

**이번 프로젝트에서는 JPA 기술이 변동될 가능성이 없다고 생각하여 도메인 모듈에서 JPA 관련 의존성을 허용하였습니다.**
