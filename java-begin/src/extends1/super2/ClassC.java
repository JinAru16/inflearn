package extends1.super2;

public class ClassC extends ClassB{

    public ClassC(){
        // super(); 옛날의 기억을 잘 떠올려보면 파라미터가 있는 생성자를 직접 지정하면 기본생성자가 자동으로 만들어지지 않음.
        super(10, 20);
        System.out.println("ClassC 생성자");
    }
}
