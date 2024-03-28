package lang.object.tostring;

public class ToStringMain1 {
    public static void main(String[] args) {
        Object object = new Object();
        String string = object.toString();

        //toString()의 반환값을 출력
        System.out.println(string);

        //object 직접 출럭
        System.out.println(object);
    }
}
