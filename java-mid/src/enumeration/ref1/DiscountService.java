package enumeration.ref1;

import enumeration.ref2.Grade;

public class DiscountService {
    public int discount(ClassGrade grade, int price){
        return price * grade.getDiscountPercent() / 100;
    }

}
