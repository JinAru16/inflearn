package poly.basic;

public class CastingMain1 {
    public static void main(String[] args) {
        // 부모변수가 자식 인스턴스 참조(다형적 참조)
        Parent poly = new Child(); // x001
        // 단 자식의 기능은 호출할 수 없다. 컴파일 오류 발생
        //poly.childMethod();

        // 다운캐스팅(부모타입 -> 자식타입)
        Child child = (Child) poly; // x001에서 값을 복사하여서 읽어옴. -> 복사한 값을 자식타입으로 지정함. 원본인 poly는 그대로 부모타입임.
        child.childMethod();
    }
}
