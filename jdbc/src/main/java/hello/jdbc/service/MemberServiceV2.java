package hello.jdbc.service;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try{
            con.setAutoCommit(false); //트랜잭션 시작
            //비즈니스 로직 실행
            bizLogic(con, fromId, toId, money);
            con.commit();// 성공시 커밋
        }catch (Exception e){
            con.rollback();//실패시 롤백
            throw new IllegalStateException(e);
        }finally {
            release(con);
        }

    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if(con != null){
            try {
                /*
                위에서 트랜잭션을 위해서 오토커밋을 해제해둠.
                이 상태로 그냥 close해버리면 auto commit = false로 커넥션 풀에 커넥션이 반환됨
                그럼 누군가가 이 커넥션을 받아서 쓰면 오토커밋 = ture 인 줄 알고 쓰다가
                문제가 생길 수 있으니 항상 오토커밋으로 되돌려주는 작업을 먼저 해준다.
                 */
                con.setAutoCommit(true);
                con.close();
            } catch (Exception e){
                log.info("err", e);
            }
        }
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
