package nested.nested.anonymous.ex;

import java.util.Random;

public class Ex1RefMainSelf {
    public static void runFunction(HelloFunc helloFunc){
        System.out.println("프로그램 시작");
        helloFunc.hello();
        System.out.println("프로그램 종료");
    }

    public static void main(String[] args) {
        runFunction(() -> {
            int i = new Random().nextInt(6) + 1;
            System.out.println("i = "+ i);
        });

        runFunction(() -> {
            for(int i = 0; i < 3; i++){
                System.out.println("i = "+ i);
            }
        });
    }


}
