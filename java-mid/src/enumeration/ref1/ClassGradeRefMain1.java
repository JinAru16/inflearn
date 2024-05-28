package enumeration.ref1;

public class ClassGradeRefMain1 {
    public static void main(String[] args) {
        int price = 10000;
        DiscountService discountService = new DiscountService();
        int basic = discountService.discount(ClassGrade.BASIC, price);
        int gold = discountService.discount(ClassGrade.GOLD, price);
        int diamond = discountService.discount(ClassGrade.DIAMOND, price);

        System.out.println("basic 등급의 할인 금액 : "+ basic);
        System.out.println("gold 등급의 할인 금액 : "+ gold);
        System.out.println("diamond 등급의 할인 금액 : "+diamond);

    }
}
