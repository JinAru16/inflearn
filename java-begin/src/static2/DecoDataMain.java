package static2;

public class DecoDataMain {
    public static void main(String[] args) {
        System.out.println("1. 정적 호출");
        DecoData.staticCall();

        System.out.println("2. 인스턴스 호출");
        DecoData data1 = new DecoData();
        data1.instanceCall();

        System.out.println("3. 인스턴스 호출2");
        DecoData  data2 = new DecoData();
        data2.instanceCall();

        // 추가
        // 인스턴스를 통한 접근
        DecoData data3 = new DecoData();
        data3.staticCall(); // 컴파일 에러는 안나지만 인스턴스 메서드처럼 보이기 때문에 클래스 소속은 DecoData.staticCall()로 적어주자.

        // 클래스를 통한 접근
        DecoData.staticCall();
    }
}
