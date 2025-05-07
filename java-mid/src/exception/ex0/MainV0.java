package exception.ex0;

import java.util.Scanner;

public class MainV0 {
    public static void main(String[] args) {
        NetworkServiceV0 service = new NetworkServiceV0();

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
