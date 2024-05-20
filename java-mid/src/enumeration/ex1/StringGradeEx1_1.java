package enumeration.ex1;

public class StringGradeEx1_1 {
    public static void main(String[] args) {
        int price = 10000;
        DiscountService discountService = new DiscountService();
        int basic = discountService.discount(StringGrade.BASIC, price);
        int gold = discountService.discount(StringGrade.GOLD, price);
        int diamond = discountService.discount(StringGrade.DIAMOND, price);

        System.out.println("basic 등급의 할인 금액 : "+ basic);
        System.out.println("gold 등급의 할인 금액 : "+ gold);
        System.out.println("diamond 등급의 할인 금액 : "+diamond);
    }
    // 상수를 사용하더라도 근본적인 문제는 없어지지 않음.
    //discountService.discount(string, price) -> 결국은 String 타입을 파라미터로 받기 때문에 개발자가 직접 String 타입의 임이의 값을 적으면 막을 방법이 없다.
    // 주석에 뭐 StringGrade를 넣으세요 라고 써도 안보면 그만 -> 컴파일 에러로 잡을수 없다.
}
