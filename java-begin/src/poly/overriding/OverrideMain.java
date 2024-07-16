package poly.overriding;

public class OverrideMain {
    public static void main(String[] args) {
        // 자식 변수가 자식 인스턴스 참조
        Child child = new Child();
        System.out.println("Child -> Child");
        System.out.println("value = " + child.value);
        child.method();

        //부모 변수가 부모 인스턴스 참조
        Parent parent = new Parent();
        System.out.println("Parent -> Parent");
        System.out.println("value = " + parent.value);
        parent.method();

        //부모 변수가 자식 인스턴스 참조(다형적 참조)
        Parent poly = new Child();
        System.out.println("Parent -> Child");
        System.out.println("value = "+ poly.value); //변수는 오버라이팅 x
        poly.method();// 메서드는 오버라이딩 O -> 항상 하위의 오버라이딩 메서드가 우선권을 가짐

        // 부모 - 자식 - 손자 가있는데 부모의 메서드를 자식, 손자가 오버라이딩 해가면 손자의 메서드가 최우선권을 가짐
    }
}
