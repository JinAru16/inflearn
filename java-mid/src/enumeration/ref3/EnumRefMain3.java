package enumeration.ref3;
public class EnumRefMain3 {
    public static void main(String[] args) {
        int price = 10000;
        DiscountService discountService = new DiscountService();
        int basic = discountService.discount(Grade.BASIC, price);
        int gold = discountService.discount(Grade.GOLD, price);
        int diamond = discountService.discount(Grade.DIAMOND, price);

        System.out.println("basic 등급의 할인 금액 : "+ basic);
        System.out.println("gold 등급의 할인 금액 : "+ gold);
        System.out.println("diamond 등급의 할인 금액 : "+diamond);
    }
}
