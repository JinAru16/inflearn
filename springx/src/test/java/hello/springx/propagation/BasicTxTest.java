package hello.springx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {

    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config{
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource){
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Test
    void commit(){
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 커밋");
        txManager.commit(status);
        log.info("트랜잭션 커밋 완료");
    }

    @Test
    void rollback(){
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 롤백 커밋");
        txManager.rollback(status);
        log.info("트랜잭션 롤백 완료");
    }

    @Test
    void double_commit(){
        log.info("트랜잭션1 시작");
        TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 커밋");
        txManager.commit(tx2);


        log.info("트랜잭션 커밋 완료");
    }

    @Test
    void double_commit_rollback(){
        log.info("트랜잭션1 시작");
        TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("트랜잭션2 롤백");
        txManager.rollback(tx2);
    }

    @Test
    void inner_commit(){
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction() = {}", outer.isNewTransaction()); //이건 새로 생긴 트랜잭션

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction() = {}", inner.isNewTransaction()); // 얘는 기존의 트랜잭션을 이어받아 참여하는 트랜잭션.
        // 트랜잭션에 참여한다 -> 그냥 아무것도 안한다는거임. 기존의 연산을 그대로 기존의 트랜잭션 내에서 계속 이어나가겠다는 말임.

        log.info("내부 트랜잭션 커밋");
        txManager.commit(inner);

        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer);
    }
    @Test
    void outer_rollback(){
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction() = {}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction() = {}", inner.isNewTransaction());

        log.info("내부 트랜잭션 커밋");
        txManager.commit(inner);

        log.info("외부 트랜잭션 롤백");
        txManager.rollback(outer);

        /**
         *  이것만 기억하면 된다. 기본옵션(REQUIRED를 직접 주거나 아니면 아무것도 지정하지 않은 기본 상태)에서는 
         *  여러개의 하나의 물리 트랜잭션을 구성하는 여러개의 논리 트랜잭션들 중 하나라도 롤백이 발생하면 전체가 롤백이 된다.
         */
    }@Test
    void inner_rollback(){
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction() = {}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction() = {}", inner.isNewTransaction());

        log.info("내부 트랜잭션 롤백");
        txManager.rollback(inner); // 내부적으로 외부에서 무슨일이 벌어지든지 롤백 무조건 시키라고 마킹을 함. 트랜잭션 동기화 매니저에 rollback = true 라고 표시를 해둠

        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer); // 외부트랜잭션에서 커밋전에 트랜잭션 동기화 매니저를 한번 뒤져봄 뒤져보고 rollback = true라고 마킹되어 있으면 커밋이라고 되어있어도 그냥 롤백을 때려버림

        /*
        모든 논리 트랜잭션이 커밋이어야 실제로 커밋이 날아감. 내부든 외부든 하나라도 롤백이 있으면 전부 롤백시키는게 스프링 기본 정책임
        근데 개발자는 커밋을 하라고 명령했는데 실제로는 롤백이 되었으니깐 예외를 터트려서 개발자에게 알려줌.
        UnexpectedRollbackException을 터트려서 실제 의도와는 다르게 동작해서 어떻게 처리할지를 개발자에게 정하라고 알려줘야해서
        예외를 터트리고 롤백을 시킴.

        헷갈린다면 대원칙만 기억하자.
        여러개의 논리 트랜잭션 중 하나라도 롤백이 발생하면 해당 물리 트랜잭션은 그냥 롤백이 된다고 생각하자.
         */
    }
    @Test
    @DisplayName("트랜잭션을 시작하고 외부나 내부에서 롤백이 발생하여도 롤백 발생한 부분을 제외한 나머지는 커밋이 그대로 진행된다.")
    public void inner_rollback_required_new(){

        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("inner.isNewTransaction() = {}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 이 옵션을 부여하면 기존 트랜잭션을 무시하고 새로운 트랜잭션을 만들어버림 -> 그래서 전체 롤백되는걸 막고 원하는걸 커밋 시킬수 있음
        TransactionStatus inner = txManager.getTransaction(definition);
        log.info("outer.isNewTransaction() = {}", outer.isNewTransaction());

        log.info("내부 트랜잭션 롤백");
        txManager.rollback(inner);

        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer);
        /**
         * 트랜잭션에 특별이 옵션을 주지 않았던 시절을 떠올려보자
         * 트랜잭션.isNewTransaction() == true 인 애만 실제 물리적으로 롤백이나 커밋이 이뤄짐.
         * false인 애는 기존의 트랜잭션을 이어받은 아이라서 물리적으로 롤백이나 커밋 해버리면 이전의 트랜잭션이 완전히 종료되어버리는 참사가 발생하기 때무네
         * fasle의 경우엔 그냥 rollback only == true로 기록하거나 커밋의 경우 아무것도 하지 않고 다음트랜잭션을 이어받음.
         *
         * 여기에선 PROPAGATION_REQUIRES_NEW 옵션을 부여했기 때문에 새로운 트랜잭션임 .
         * 트랜잭션.isNewTransaction() == true인 상황. 따라서 실제 물리 트랜잭션에 대해서 커밋이나 롤백이 발생한다
         *
         * 그럼 기존의 커낵션은???
         * 기존을 conn1, 그다음 새로운 커낵션을 conn2라 하자
         * conn2를 사용할 경우엔 conn1은 그대로 트랜잭션 동기화 매니저에 둠. 잠시 대기시켜둔다. conn1이 대기라고 해서 커넥션 풀에 반납하거나 하지 않는다. 아직 연결된 상태다
         * conn2에 대해서 커밋이든 롤백이든 진행 한 후 conn2는 커넥션 풀에 반납을 함.
         * conn1에 대해서 다시 연산을 진행한다. 얘도 마찬가지로 트랜잭션.isNewTransaction() == true이기 때문에 롤백이나 커밋에 대해서 실제 물리적으로 동작함
         * 그 후에 conn1을 커넥션풀에 다시 반납한다.
         *
         * PROPAGATION_REQUIRES_NEW를 쓴다는건 커넥션을 두개이상 물고있다는 뜻이다. -> 잘못 사용시 데이터베이스의 커넥션이 빨리 고갈될 수 있다.
         */

    }
}
