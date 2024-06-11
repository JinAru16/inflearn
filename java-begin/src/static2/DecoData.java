package static2;

public class DecoData {
    private int instanceValue;
    private static int staticValue;
    public static void staticCall(){
        staticValue ++;
        staticMethod(); // 스태틱 메서드 호출 가능

        //instanceValue++;// static 메서드 안에서는 인스턴스 변수에는 접근 불가능, 컴파일 오류남
        // instanceMethod(); static 메서드 안에서는 인스턴스 메서드는 호출이 불가능, 컴파일 오류남.
        // -> static 안에서는 static 메서드, static 변수만 접근이 가능.
        // -> new로 참조형 선언 해주고 나서 사용은 가능.
    }
    public static void staticCall(DecoData data){
        data.instanceValue++;
        data.instanceMethod();
        // -> 외부에서 참조값이 넘어옴. 참조값이 있으면 인스턴스 변수, 인스턴스 함수 사용 가능.
    }

    public void instanceCall(){
        instanceValue++;// 인스턴스 메서드에서는 당연히 인스턴스 변수 호출이 가능
        instanceMethod();// 인스턴스 메서드에서는 당연히 인스턴스 함수 호출이 가능.

        staticValue++;// 스태틱 변수는 모든곳에서 호출이 가능
        staticMethod(); // 스태틱 함수는 모든곳에서 호출이 가능.

    }
    private void instanceMethod(){
        System.out.println("instanceValue = "+instanceValue);
    }

    private static void staticMethod(){
        System.out.println("staticValue = "+staticValue);
    }
}
