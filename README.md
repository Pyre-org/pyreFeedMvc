## 🙌 Hello. Pyre is always with you!
<img src="https://github.com/user-attachments/assets/630a13b0-6a68-44ba-8189-2fd58c060a4e" alt="drawing" width="400"/>

#### 현재 파이어는 미완성 프로젝트입니다.
#### [Github 조직](https://github.com/Pyre-org)

# PyreAuth
## 프로젝트 구조
<img src="https://github.com/user-attachments/assets/a17214a5-3fd7-409e-a0f8-0c359733e15c" alt="drawing" width="600"/>

## 기술 스택
- Java 21
- Spring Boot (3.2.2)
- Spring Web
- Spring Boot data JPA

- Spring Cloud eureka client
- Spring Cloud config
- Spring cloud Open Feign

- MongoDB

- Open API Swagger
  
## 피드 서비스
- NoSQL 데이터베이스 MongoDB를 사용하여 파이어의 피드 데이터를 관리합니다.
- 스크린샷으로부터 발생하는 피드를 저장하고, 조회할 수 있습니다.
- 엔티티
  - Feed : 피드를 구성하는 엔티티입니다.
    - id : 피드 아이디
    - userId : 유저 아이디
    - spaceId : 스페이스 아이디
    - title : 제목
    - description : 설명
    - cAt : 생성일
    - url : 이미지 링크
- 
- Open API [링크](https://apis.pyre.live/feed/swagger-ui/index.html)

# 구현 중 이슈
## MongoDB
### 선택 이유
- 피드는 스페이스와 유저와의 관계만을 참조할 수 있으면 되기 때문에 굳이 관계형 DB를 사용하지 않아도 될 것이라고 생각함.
- 피드는 파이어 커뮤니티의 채널, 룸, 스페이스보다 많이 데이터 처리를 해야하기 때문에 빠른 속도가 필요할 것이라고 생각함.
- 기존에 피드는 비동기 WebFlux를 사용할 예정이었기 때문에 비동기 처리를 할 수 있는 NoSQL을 선택하게 됨.

- Cassandra 나 ScyllaDB를 고려했으나, 배포 서버의 환경과 유료 클라우드의 비용을 생각하여 선택 대상에서 제외됨.
- 결론적으로 피드를 컬렉션으 사용하는 MongoDB를 선택하게 됨.
