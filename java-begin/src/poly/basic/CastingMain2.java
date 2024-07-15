package poly.basic;

public class CastingMain2 {
    public static void main(String[] args) {
        // 부모변수가 자식 인스턴스 참조(다형적 참조)
        Parent poly = new Child(); // x001
        // 단 자식의 기능은 호출할 수 없다. 컴파일 오류 발생
        //poly.childMethod();

        // 다운캐스팅(부모타입 -> 자식타입)
        Child child = (Child) poly; // x001에서 값을 복사하여서 읽어옴. -> 복사한 값을 자식타입으로 지정함. 원본인 poly는 그대로 부모타입임.
        child.childMethod();

        //일시적 다운캐스팅 - 해당 메서드를 호출하는 순간만 다운캐스팅
        ((Child) poly).childMethod(); //마찬가지로  다운캐스팅을 통해 부모타입을 자식 타입으로 변환 후 기능 호출, 참조값을 읽은 다음 자식 타입으로 다운 캐스팅. 원본인 poly의 타입은 부모타입으로 계속 유지됨
    }
}
