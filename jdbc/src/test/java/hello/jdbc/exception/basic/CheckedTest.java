package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {
    @Test
    @DisplayName("")
    public void checked_catch(){
        //given
        Service service = new Service();
        service.callCatch();

        //when

        //then
    }
    @Test
    @DisplayName("")
    public void checked_throw() {
        //given
        Service service = new Service();
        //when
        // service.callThrow(); 테스트 입장에서는 예외 터지면 에러나는거니깐 이렇게 하지 말고 assertThatThrownBy를 해줘서 예외 터지는것도 보고 테스트도 통과하자
        //then
        Assertions.assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }
    static class MyCheckedException extends Exception {
        /**
         * Exception을 상속받은 예외는 체크 예외가 된다.
         */
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     * Checked 예외는
     * 예외를 잡아서 처리하거나, 던지거나 둘 중 하나를 필수로 선택해야 한다.
     */
    static class Service{
        Repository repository = new Repository();
        /**
         *  얘외를 잡아서 처리하는 코드
         */
        public void callCatch(){
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외 처리 로직
                log.info("예외 처리, message={}", e.getMessage(), e);
            }
        }

        /**
         * 체크예외를 밖으로 던지느 코드
         * 체크 얘외는 예외를 잡지 않고 밖으로 던지려면 throws 예외를 메서드에 필수로 선언해야 한다.
         * @throws MyCheckedException
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }
    }
    static class Repository{
        public void call() throws MyCheckedException {// 체크예외는 꼭 밖으로 던지는걸 선언해줘야 함 -> 컴파일러가 체크를 항상 하고있음 그래서 체크예외임
            throw new MyCheckedException("ex");  // 여기에만 쓴다고 되는게 아니라 메서드 옆에다 선언해줘야 함.
        }
    }
}
