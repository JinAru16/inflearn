## JPA의 flush 타이밍

* jpa와 jdbcTemplate을 같이 사용하는 경우 JPA의 Flush 타이밍을 주의해야 함.
* JPA는 변경사항을 바로 DB에 반영하지 않는다
* 트랜잭션이 끝나는 시점에 변경사항을 DB에 반영한다.
* 따라서 하나의 트랜잭션 안에서 JPA를 통해서 데이터를 변경한 뒤 JdbcTemplate을 호출 하는 경우
JPA가 변경한 데이터를 JdbcTemplate이 감지하지 못하는 경우가 발생한다
* 따라서 JPA가 데이터를 변경하면 flush를 사용하여 db에 강제로 반영 한 뒤
JdbcTemplate을 통해 jpa가 변경한 데이터를 사용할 수 있다
