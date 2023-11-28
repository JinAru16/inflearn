package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
@Slf4j
public class UnCheckedAppTest {

    @Test
    @DisplayName("unchecked exception")
    public void Unchecked(){
        //given
        Controller controller = new Controller();
        //when

        //then
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(RuntimeSqlException.class);
    }

    @Test
    @DisplayName("예외를 전환할 때는 반드시 기존 예외를 포함해야 한다.")
    public void printEx(){
        //given
        Controller controller = new Controller();
        try{
            controller.request();
        } catch (Exception e){
            log.info("ex", e);
        }

    }


    static class Controller{
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }
    static class Service{
        Repository repository = new Repository();
        NetWorkClient netWorkClient = new NetWorkClient();

        public void logic() {
            repository.call();
            netWorkClient.call();
        }
    }
    static class NetWorkClient{
        public void call()  {
            throw new RuntimeConnectException("연결 실패");
        }
    }
    static class Repository{

        public void call()  {
            try {
                runSql();
            } catch (SQLException e) {
                throw new RuntimeSqlException(e);
            }
        }
        public void runSql() throws SQLException {
            throw new SQLException("ex");
        }
    }
    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message){
            super(message);
        }
    }

    static class RuntimeSqlException extends RuntimeException{
        public RuntimeSqlException() {
        }

        public RuntimeSqlException(Throwable cause) {
            super(cause);
        }
    }
}
