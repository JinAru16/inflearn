


# JPA의 연산
```java
@Slf4j
@Repository
@Transactional// 필수
public class JpaItemRepositoryV1 implements ItemRepository {
    
}
```
## 1. @Transactional
* JPA의 모든 연산에는 트랜잭션 안에서 이뤄져야 한다(조회는 없어도 됨) 필수임!
* 일반적으로 변경의 경우 서비스 계층에서 트랜잭션을 시작하기 때문에 문제가 없지만 이번 예제에서는 서비스에 복잡한 내용이 없어서
서비스단에 @Transactional 걸어주지 않음. -> 레파지토리에 대신 트랜잭션을 걸었음. 일반적으로는 서비스단에 거는게 맞다.


## 2. update
* jpa에서는 업데이트 명령어가 따로 없다.
* jpa는 트랜잭션이 커밋되는 시점에 변경된 엔티티 객체가 있는지 확인한다. 특정 엔티티 객체가 변경된 경우에는 update sql을 실행한다.
* 트랜잭션 커밋 시점에 JPA가 변경된 엔티티 객체를 찾아서 UPDATE sql을 날려준다.
* 테스트의 경우엔 트랜잭션이 롤백되므로 update문이 날아가지 않는다
* 따라서 update문을 보고싶다면 @Commit 어노테이션을 선언해주자.

## 3. JPA의 예외변환

* JPA는 순수 jpa기술이고 스프링과는 관계가 없음 -> 엔티티 메니저는 예외가 발생하면 JPA 예외를 발생시킴
* JPA는 PersistenceException과 그 하위 예외를 발생시킴
* 근데 에러들 찍어보면 IllegalStateException, IllegalArgumentException을 발생시킴(이들은 스프링 예외)
* 어떻케 변환시킨거임? -> 비밀은 @Repository


## 4. @Repository의 기능
* @Repository는 알다싶이 컴포넌트 스캔의 대상이 된다.
* 한가지 더 기능이 있는데, 예외 변환AOP의 적용 대상이 된다.
  * 스프링과 JPA를 함께 사용하는 경우 스프링은 JPA예외 변환기(PersistenceExceptionTranslator)를 등록한다.
  * 예외 변환AOP 프록시는 JPA 관련 예외가 발생하면 JPA 예외 변환기를 통해 발생한 예외를 스프링 데이터 접근 예외려 변환시킴

