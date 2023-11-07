package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverMangerDataSource - 항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);

    }
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10); //풀의 크기를 지정할 수 있다 기본이 10개임
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
        /*
        커넥션 풀에서 커넥션을 생성하는 작업은 애플리케이션 실행 속도에 영향을 주지 않기 위해서 별도의 쓰레드에서 작동한다.
        별도의 쓰레드에서 동작하기 때문에 테스트가 먼저 종료되어 버린다. 예제처림 Thread.sleep을 통해 대기시간의 주어야 쓰레드 풀에
        커넥션이 생성되는 로그를 볼 수 있다.

        왜 별도의 쓰레드를 사용해서 커넥션 풀에 커넥션을 채우나?
        -> 커넥션 풀에 커넥션을 채우는 것은 상대적으로 시간이 오래걸린다 => 외부 tcp연결을 통해서 db와 연결하기 때문
           따라서 애플리켄이션을 실행할 커넥션 풀을 채울때 까지 마냥 다기하고 있다면 애플리케이션 실행 시간이 늦어진다. 따라서
           이렇게 별도의 쓰레드를 사용해서 커넥션 풀을 채워야 애플리케이션 실행 시간에 영향을 주지 않는다.

           HikariProxyConnection안에 con1, con2가 들어있음

       커넥션 풀에서 커넥션 획득
       커넥션 풀에서 커넥션을 획득하고 그 결과를 출력했다. 여기서는 커넥션 풀에서 커넥션을 2개 획득하고 반환하지는 않았다.  따라서 풀에 있는
       10개의 커넥션 중에서 2개를 가지고 있는 상태이다. 그래서 마지막 로그를 보면 사용중인 커넥션 active = 2, 풀에 대기 상태인 커넥션
       idle=8을 확인 할 수 이따.
       'MyPool - After adding stats(total = 10, active=2, idle=8, waiting=0)

       그럼 connection pool에 connection이 차기전에 connection을 획득하면 어찌되나?
       -> 약간 기다렸다가 채워지면 획득함.

        한계를 넘으면 waiting하다가 설정된 시간을 초과하게 되면 SQLTransientConnectionException을 던짐.

         */

    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
//        Connection con3 = dataSource.getConnection();
//        Connection con4 = dataSource.getConnection();
//        Connection con5 = dataSource.getConnection();
//        Connection con6 = dataSource.getConnection();
//        Connection con7 = dataSource.getConnection();
//        Connection con8 = dataSource.getConnection();
//        Connection con9 = dataSource.getConnection();
//        Connection con10 = dataSource.getConnection();
//        Connection con11 = dataSource.getConnection();
//        Connection con12 = dataSource.getConnection();

        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    /**
     driverManager와 DriverManagerDataSource에는 큰 차이점이 있다
     DriveManager : 새로운 커넥션을 획득할 때 마다 URL, 유저네임, 패스워드를 파라미터로 계속 넘겨야 함
     DriverManagerDataSource : 최초 객체 생성시 한번만 파라미터로 URL, 유저네임, 패스워드로 전달. 그 이후 커넥션 획득시엔 getConnection만 호출하면 됨.

     설정과 사용의 분리
     -> 설정 : DataSource를 만들고 필요한 속성들을 사용해서 URL, USERNAME, PASSWORD 같은 부분을 입력하는 것을 말함.
     이렇게 설정과 관련된 속성들은 한곳에 있는것이 향후 변경에 더 유연하게 대처할 수 있다.
      사용 : 설정은 신경쓰지 않고 DataSource, getConnection()만 호출해서 사용하면 된다.

     쉽게이야기 해서 Repository는 DataSource만 의존 -> 이런 속성들은 몰라도 된다.
     */

}
