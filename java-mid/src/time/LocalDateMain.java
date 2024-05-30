package time;

import java.time.LocalDate;

public class LocalDateMain {
    public static void main(String[] args) {
        LocalDate nowDate = LocalDate.now();
        LocalDate ofDate = LocalDate.of(2013, 11, 21);
        System.out.println("오늘의 날짜 = "+nowDate);
        System.out.println("지정 날짜 = "+ ofDate);

        //계산(불변) -> 반환값을 받아야 함.
        LocalDate changedDate = ofDate.plusDays(10);
        System.out.println("ofDate = "+ changedDate);

        /*
         # 모든 날짜 클래스는 불변이다 -> 따라서 변경이 발생하는 경우(날짜의 덧셈 등등) 객체를 생성해서 변환하므로 꼭 반환값을 지정해줘야 한다.
         */
    }
}
