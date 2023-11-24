# 1. AOP 적용

### MemberServiceV3_3
```java
@Slf4j
@RequiredArgsConstructor
    public class MemberServiceV3_3 {
    private final MemberRepositoryV3 memberRepository;

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        bizLogic(fromId, toId, money);
    }
}
```
* 순수 비즈니스 로직만 낙미고 트랜잭션 관련 코드는 모두 제거됨.
* 스프링이 제공하는 트랜잭션 AOP를 적용하기 위해 @Transactional 어노테이션을 추가함.
* @Transactional은 클래스나 메서드 어디든 붙여도 됨 -> 클래스에 붙이면 public으로 선언한 메서드가 AOP적용 대상이 된다.

# 2. 참고사항

```java
@Slf4j
@SpringBootTest
class MemberServiceV3_3Test {
    @Autowired
    MemberRepositoryV3 memberRepository;
    @Autowired
    MemberServiceV3_3 memberService;
    
    @TestConfiguration
    static class TestConfig {
        @Bean
        DataSource dataSource() {
            return new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        }
        @Bean
        PlatformTransactionManager transactionManager() {
            return new DataSourceTransactionManager(dataSource());
        }
        @Bean
        MemberRepositoryV3 memberRepositoryV3() {
            return new MemberRepositoryV3(dataSource());
        }
        @Bean
        MemberServiceV3_3 memberServiceV3_3() {
            return new MemberServiceV3_3(memberRepositoryV3());
        }
    }
}
```

* Test시에는 @Transactional이 동작하지 않는다.
* 스프링 컨테이너가 동작하고 있어야 스프링이 관리하에 @Transactional이 동작한다. -> @SpringBootTest를 추가해준다.
* @TestConfiguration : 테스트 안에서 내부 설정 클래스를 만들어서 사용하면 이 어노테이션을 붙이면 
스프링부트가 자동으로 만들어주는 빈들에 추가로 필요한 스프링빈들을 등록하고 테스트를 수행할 수 있다.
* 스프링이 제공하는 트랜잭션AOP는 스프링 빈에 등록된 트랜잭션 매니저를 찾아서 사용하기 때문에 트랜잭션 매니저를 스프링빈으로 등록해야 한다.