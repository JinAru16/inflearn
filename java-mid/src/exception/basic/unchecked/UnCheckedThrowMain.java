package exception.basic.unchecked;

public class UnCheckedThrowMain {
    public static void main(String[] args) {
        Service service = new Service();
        service.callThrow();
        System.out.println("정상종료");

    }

}
