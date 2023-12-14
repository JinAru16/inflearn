* 관례의 불일치
  * db에선 snake case, java에선 camelCase.
  * -> application.properties에 mybatis.configuration.map-underscore-to-camel-case=true 옵션 넣어주면
  * 알아서 스네이크 케이스를 카멜로 바꿔줌.
* Mapper단에 넘겨줄 파라미터가 두개 이상일 경우
  * void update(@Param("id")Long id, @Param("updateParam")ItemUpdateDto updateParam);
  * 이렇게 @Param으로 지정해주면 됨.
* 
* 참고 - XML 파일 경로 수정하기
 XML 파일을 원하는 위치에 두고 싶으면 application.properties 에 다음과 같이 설정하면 된다.
 mybatis.mapper-locations=classpath:mapper/**/*.xml

 이렇게 하면 resources/mapper 를 포함한 그 하위 폴더에 있는 XML을 XML 매핑 파일로 인식한다. 이
경우 파일 이름은 자유롭게 설정해도 된다.

 참고로 테스트의 application.properties 파일도 함께 수정해야 테스트를 실행할 때 인식할 수 있다.
 
* 특수문자
  and price &lt;= #{maxPrice}
  여기에 보면 <= 를 사용하지 않고 &lt;= 를 사용한 것을 확인할 수 있다. 그 이유는 XML에서는 데이터
  영역에 < , > 같은 특수 문자를 사용할 수 없기 때문이다. 이유는 간단한데, XML에서 TAG가 시작하거나
  종료할 때 < , > 와 같은 특수문자를 사용하기 때문이다.
  < : &lt;
 : &gt;
& : &amp;
다른 해결 방안으로는 XML에서 지원하는 CDATA 구문 문법을 사용하는 것이다. 이 구문 안에서는
특수문자를 사용할 수 있다. 대신 이 구문 안에서는 XML TAG가 단순 문자로 인식되기 때문에 <if> ,<where> 등이 적용되지 않는다.