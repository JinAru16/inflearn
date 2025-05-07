package nested.nested.anonymous.ex;

public class Ex0refMain {

    static HelloPlatform platform = new HelloPlatform() {
        @Override
        public void hello(String name) {
            System.out.println("프로그램 시작"); //
            System.out.println("Hello " + name);
            System.out.println("프로그램 종료");
        }
    };

    public static void main(String[] args) {
        platform.hello("Java");
        platform.hello("Spring");
    }
}
