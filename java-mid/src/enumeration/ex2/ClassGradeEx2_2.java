package enumeration.ex2;

public class ClassGradeEx2_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();
        //ClassGrade classGrade = new ClassGrade(); // 함수 설명 봤더니 ClassGrade가 필요하다고 하니 다른 개발자가 그냥 생성자로 만들어 버릴 가능성도 있다.
        // 그래서 기본 생성자는 private로 막게되면 컴파일 에러가 나니깐 다른 개발자가 유심히 보게 된다.


        //int result = discountService.discount(classGrade, price);
        int result = discountService.discount(ClassGrade.GOLD, price);
        System.out.println("result : "+result);
    }
    // 단점 : 코드 작성량이 넘 많아짐 -> ENUM 타입이 등장함
}
