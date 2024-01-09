package hello.springx.propagation;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;


import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LogRepository logRepository;


    /**
     * MemberService @Transactional:OFF
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON
     */
    @Test
    public void outerTxOff_success() {
        //given
        String username = "outerTxOff_success";
        //when
        memberService.joinV1(username);
        //then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }


    /**
     * MemberService @Transactional:OFF
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    public void outerTxOff_fail() {
        //given
        String username = "로그예외_outerTxOff_fail ";
        //when
        Assertions.assertThatThrownBy(() ->
            memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        //then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
    }


    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional: OFF
     * LogRepository @Transactional:OFF
     */
    @Test
    public void singleTx() {
        //given
        String username = "outerTxOff_success";
        //when
        memberService.joinV1(username);
        //then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }
    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional: ON
     * LogRepository @Transactional:OFF
     */
    @Test
    public void outerTxOnSuccess() {
        //given
        String username = "outerTxOff_success";
        //when
        memberService.joinV1(username);
        //then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    public void outerTxOn_fail() {
        //given
        String username = "로그예외_outerTxOn_fail ";
        //when
        Assertions.assertThatThrownBy(() ->
                        memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        //then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    public void recoverException_fail() {
        //given
        String username = "로그예외_recoverException_fail";
        //when
        Assertions.assertThatThrownBy(() ->
                        memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        //then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
        /**
         * service에서 try catch로 exception을 잡아서 복구를 한다해도 이미 예외가 터진 시점에 내부 트랜잭션에서 트랜잭션 메니저에서
         * RollbackOnly = true로 마킹을 함 => 전체가 롤백이 일어남
         * 기본옵션에서는 논리트랜잭션 중 단 하나라도 예외가 터지면 전체가 롤백이 일어난다는걸 명심하자
         * 내부 트랜잭션이 롤백이 되었지만 외부 트랜잭션에서 커밋이 일어나더라도 트랜잭션 매니저에 RollbackOnly = true가 기록되어 있다면
         * 그냥 전체가 롤백이 되어버림. UnexpectedRollbackException가 터짐
         */
    }


    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON(REQUIRES_NEW) Exception
     */
    @Test
    public void recoverException_success() {
        //given
        String username = "로그예외_recoverException_fail";
        //when
        memberService.joinV2(username);
        //then: 회원가입엔 성공하고 로그 남기는건 롤백됨
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());
        /**
         * 새로운 트랜잭션에서는 기존의 트랜잭션을 이어받은게 아니므로 rollbackOnly = true를 트랜잭션 매니저에 기록하지 않는다.
         * 왜냐? 논리 트랜잭션이 아니라 물리트랜잭션임.
         * Requires_New 옵션을 사용하면 새로운 커넥션을 가져와서 연결하므로 완전히 다른 트랜잭션이다.
         * 그냥 물리적으로 롤백을 하고 끝내버린다.
         *
         * 다만 주의할 점은 requires_new 옵션은 커넥션을 두개를 동시에 사용하게됨 -> 성능에 지장이 생길수도 있음
         * 그래서 회원은 100명인데 db커넥션은 200명분이 물고있는 현상이 발생할 수 있음.
         * 성능도 챙기고 롤백 여부를 따로 가져가고싶다면  하나의 서비스에 두개의 레파지토리를 의존하게끔 하지 않고
         * 각각 다른 연산에 담아서 트랜잭션을 분리시키는 방법을 사용하기도 하지만 Requires_new가 더 깔끔한 경우도 있기때문에
         * 상황에 맞게 잘 사용하자.
         */
    }
}