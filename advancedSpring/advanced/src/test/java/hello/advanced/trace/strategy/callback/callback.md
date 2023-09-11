# 1. 콜백정의
프로그래밍에서 콜백 또는 콜애프터함수는 다른 코드의 인수로써 넘겨주는 실행 가능한 코드를 밀한다.
콜백을 넘겨받는 코드는 이 콜백을 필요에 따라 즉실 실행할 수도 있고, 아니면 나중에 실행할 수 있다.
-> 파라미터로 함수를 넘겨주는거임.

쉽게 이야기 하여 callback은 코드가 호출(call)은 되는데 코드를 넘겨준 곳의 뒤(back)에서 실행된다는 뜻이다.
```text
ContextV2 예제는 콜백 Strategy다.
여기에서는 클라이언트에서 직접 strategy를 실행하는것이 아니라, 클라이언트가 ContextV2.execute(??)를
실행 할 때 Strategy를 넘겨주고, ContextV2뒤에 Strategy가 실행된다.
```

# 2. 자바 언어에서 콜백
* 자바 언언에서 실행 가능한 코드를 인수로 넘기려면 객체가 필요하다. 자바8부터는 람다함수를 사용할 수 있다.
* 자바8 이전에는 보통 하나의 메소드를 가진 인터페이스를 구현하고, 주로 익명 내부 클래스를 사용했다.
* 쾨근에는 주로 람다를 사용한다.

# 3. 템플릿 콜백 패턴
* 스프링에서는 ContextV2와 같은 방식의 전략 패턴을 템플릿 콜백 패턴이라 한다. 전략 패턴에서 Context가 템플릿 역항르 하고, Strategy 부분이 콜백으로 넘어온다 생각하면 된다.
* 참고로 콜백 패턴은 GoF 패턴은 아니고 스프링 내부에서 이런 방식을 자주 사용하여 스프링에서만 이렇게 부름 -> 전략패턴에서 템플릿과 콜백 부분이 강조된 패턴임.
  * 스프링에서 JdbcTemplate, RestTemplate, TransactionTemplate, RedisTemplate처럼 다양한 템플릿 콜백 패턴이 사용된다. 스프링에서 이름에 xxxTemplate이 있3다면 템플릿 콜백 패턴으로 만들어졌다고 생각하면 된다.