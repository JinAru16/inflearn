package generic.ex0;

public class BoxMain2 {
    public static void main(String[] args) {
        ObjectBox integerBox = new ObjectBox();
        integerBox.set(10);
        Integer i = (Integer) integerBox.get();
        System.out.println("integer : "+ i);

        ObjectBox stringBox = new ObjectBox();
        stringBox.set("test");
        String s = (String) stringBox.get();
        System.out.println("string : "+ s);

        //잘못된 타입의 인수 전달시
        integerBox.set("문자100");
        Integer result = (Integer) integerBox.get(); // String -> Integer 캐스팅 여ㅖ외
        System.out.println("result : "+ result);
    }
}
