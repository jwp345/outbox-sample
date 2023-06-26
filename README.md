outbox 패턴을 활용해 보았다. 안될땐 docker 설정을 확인하고 제대로 connector api 사용해 연결했는지 확인하자.
빌드할 때 images가 아니라 image로 입력해야한다.
docker 설정 시 Expose daemon on tcp://localhost:2375 without TLS, WSL 아래 설정 확인
연결이 끊길 때가 있으므로 kafka Connector 제대로 연결되었는지 get으로 확인

outbox 패턴 핵심 -> db에 저장했다가 스케줄러나 배치로 polling하는 작업은 비용이 비싸다.
outbox 패턴은 db에 저장을 했다가 바로 삭제해서 테이블이 블록되는 단점을 극복할 수 있다. 트랜잭션 기록이 테이블에 남기때문에 kafka Connector를 활용해 트랜잭션 로그들을 캡처하여 가져온다. 

출처 : https://fullstackdeveloper.guru/2022/05/19/how-to-implement-transactional-outbox-design-pattern-in-spring-boot-microservices/?expand_article=1
https://flowlog.tistory.com/91#4.%20docker-compose
