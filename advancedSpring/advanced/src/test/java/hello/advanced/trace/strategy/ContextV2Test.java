package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    public void strategyV1(){
        ContextV2 contextV2 = new ContextV2();
        // 전략을 파라미터로 전달함.
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
        /**
         * Context와 Strategy를 선조립 후 실행하는 방식이 아니라 Context를 실행할 때마다 전략을 파라미터로 전달한다.
         * 클라이언트는 Context를 실행하는 시점에 원하는 Strategy를 전달할 수 있다. 따라서 이전 방식과 비교해서는 원하는 전략을
         * 더욱 유연하게 변경할 수 있다.
         *
         * 테스트코드를 보면 하나의Context만 생성한다. 그리고 하나의 Context에 실행 시점에 여러 전략을 인수로 전달해서 유연하게 실행이 가능하다.
         */
    }
    @Test
    public void strategyV2(){
        ContextV2 contextV2 = new ContextV2();
        /**
         * 익명 내부클래스가 뭔기 이해가 안된다면 그냥 밑에서 구현하는게
         * 코드 조각을 넘긴다고 생각하면 됨. execute()를 실행하려고 하는데
         * call은 구체되회지 않은 상태임.
         * 여기서 구체화하여 작성하고 뒤로 넘겨준다고 생각하면 편함.
         */

        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });

        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 2  로직 실행");
            }
        });
    }

    @Test
    public void strategyV3(){
        ContextV2 contextV2 = new ContextV2();
      /*
      익명 내부 플래스를 람다로 변경
       */

        contextV2.execute(() -> log.info("비즈니스 로직 1 실행"));

        contextV2.execute(() -> log.info("비즈니스 2  로직 실행"));
    }
}
