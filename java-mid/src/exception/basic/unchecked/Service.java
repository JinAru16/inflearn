package exception.basic.unchecked;
/**
 * Unchecked 예외는
 * 예외를 잡거나, 던지지 않아도 된다.
 * 예외를 잡지않으면 자동으로 밖으로 던진다.
 */
public class Service {
    Client client = new Client();
    /**
     * 필요한 경우 예외를 잡아서 처리할 수 있다.
     */
    public void callCatch(){
        try{
            client.call();
        } catch(MyUncheckedException e){
            System.out.println("예외처리, message=" + e.getMessage());
        }
        System.out.println("정상로직");
    }

    /**
     * 예외를 잡디 않아도 된다. 자연스럽게 상위로 넘어간다.
     * 체크 예외와 다르게 throws 예외 선언을 하지않아도 됩니다.
     */
    public void callThrow(){
        client.call();
    }


}
