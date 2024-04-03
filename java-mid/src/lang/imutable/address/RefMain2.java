package lang.imutable.address;

public class RefMain2 {
    public static void main(String[] args) {
        // 참조형 변수는 하나의 인스턴스를 공유할 수 있다.
        ImutableAddress a = new ImutableAddress(" 서울"); //x001
        ImutableAddress b = a;// 참조값 대입을 막을 방법은 없다.  // x001

        System.out.println("a = "+ a);
        System.out.println("b = "+ b);

        //b.setValue("부산"); // 하지만 b의 값을 바꾸는건 막을수 있다.
        b = new ImutableAddress("부산");
        System.out.println("a = "+ a);
        System.out.println("b = "+ b);

    }
}
