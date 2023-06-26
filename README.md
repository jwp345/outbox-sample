# OUTBOX 패턴 실습
+ OutBox 패턴은 왜 필요한가?
+ db를 업데이트하고, 다른 서비스에 메시지를 보내는 걸 트랜잭션 단위로 묶고 싶다. 비동기로 처리하고 싶을 때 어떤 방법을 사용할 수 있는가?
  + db 테이블에 성공한 이벤트 이력을 저장한 다음 polling 방식으로 가져온다.
  + 이러한 방식이 필요한 이유는 만약 비동기처리할 때 db에 저장과 메시지 발행이 동시에 일어날 수 있고, 이렇게 되면 트랜잭션으로 묶을 수 없으므로 db에 수정이나 삭제가 발생 시 db에 적재해서 이벤트를 발행하게 만든다.
  + 분산 트랜잭션이란 방식이 있지만(2단계 커밋) 성능 저하때문에 잘 쓰지않고 카프카의 경우 기능 자체를 지원하지 않는다.
  + outbox 패턴을 이용하여 트랜잭션로그를 보고 이벤트를 발행한다.
  + 동기 방식은 어차피 작업의 완료 여부를 체크하는 @Transactional이 동작한다.
  + 하지만 비동기 방식은 동작하지 않는 게 문제다.
  + 왜 둘 중 outbox 패턴이 더 효율적인가?
    + db에 저장했다가 스케줄러나 배치로 polling하는 작업은 비용이 비싸다.
    + outbox 패턴은 db에 저장을 했다가 바로 삭제해서 배치 작업동안 테이블이 block 되는 단점을 극복할 수 있다. 
    + 트랜잭션 기록이 테이블에 남기때문에 kafka Connector를 활용해 트랜잭션 로그들을 캡처하여 가져온다.
+ outbox 패턴을 실습해 보았다. 안될땐 docker 설정을 확인하고 제대로 connector api 사용해 연결했는지 확인하자.
빌드할 때 images가 아니라 image로 입력해야한다.
+ docker 설정 시 Expose daemon on tcp://localhost:2375 without TLS, WSL 아래 설정 확인
+ 연결이 끊길 때가 있으므로 kafka Connector 제대로 연결되었는지 get으로 확인

출처 : https://fullstackdeveloper.guru/2022/05/19/how-to-implement-transactional-outbox-design-pattern-in-spring-boot-microservices/?expand_article=1
https://flowlog.tistory.com/91#4.%20docker-compose
