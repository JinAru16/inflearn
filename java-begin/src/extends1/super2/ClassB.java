package extends1.super2;

public class ClassB extends ClassA{
    public ClassB(int a) {
        super(); //원래는 부모의 기본 생성자를 적어줘야 함. but 파라미터가 없는 기본 생성자는 자바가 알아서 만들어 줌. 생략 가능
        System.out.println("생성자 B. a = "+a);
    }

    public ClassB(int a, int b){
        super();
        System.out.println("ClassB 생성자 a = "+a + " b = "+b);
    }
}
