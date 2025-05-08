package exception.ex1;


import java.util.Scanner;

public class MainV1 {
    public static void main(String[] args) {
       // NetworkServiceV1_1 service = new NetworkServiceV1_1();
       // NetworkServiceV1_2 service = new NetworkServiceV1_2();
        NetworkServiceV1_3 service = new NetworkServiceV1_3();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("전송할 문자 : ");
            String input = scanner.nextLine();
            if(input.equals("exit")){
                scanner.close();
                break;
            }
            service.sendMessage(input);
            System.out.println();
        }
        scanner.close();
        System.out.println("프로그램 정상종료");
    }
}
