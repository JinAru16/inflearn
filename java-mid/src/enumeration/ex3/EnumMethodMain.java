package enumeration.ex3;

import java.util.Arrays;

public class EnumMethodMain {
    public static void main(String[] args) {
        //모든 enum 반환
        Grade[] values = Grade.values();
        System.out.println("values = "+ Arrays.toString(values));

        for (Grade value : values) {
            System.out.println("name : "+ value.name() + ", ordinal = "+value.ordinal());
        } // ordinal()은 가급적 사용하지 않는게 좋다. 왜냐면 이 값을 사용하다가 중간에 상수를 선언하는 위치가 변경된면 전체 상수의 위치가 모두 변경될 수 있기 때문이다.

        //String -> enum 변환
        String input = "GOLD";
        Grade gold = Grade.valueOf(input);
        System.out.println("gold = "+gold);
    }
}
