package generic.ex1;

public class BoxMain3 {
    public static void main(String[] args) {
        GenericBox<Integer> integerBox = new GenericBox<>();
        integerBox.set(10);
        //integerBox.set("100");// Integer 타입만 허용, 컴파일 오류
        Integer integer = integerBox.get(); // Integer 타입 반환.

        System.out.println("integer : " + integer);

        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("hello");
        System.out.println("stringBox : " + stringBox.get());

        //타입 추론 : 생성하는 제네릭 타입 생략 가능
        GenericBox<Integer> integerBox2 = new GenericBox<>();
    }
}
