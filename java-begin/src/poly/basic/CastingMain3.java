package poly.basic;

// upcasting vs downcasting
public class CastingMain3 {
    public static void main(String[] args) {
        Child child = new Child();

        Parent parent1 = (Parent) child; // 원래 이게 캐스팅의 정석임
        Parent parent2 = child; // 근데 업캐스팅은 생략을 권장

        parent1.parentMethod();
        parent2.parentMethod();
    }

}
