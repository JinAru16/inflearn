package poly.basic;

// 다운캐스팅을 자동으로 하지 않는 이유
public class CastingMain4 {
    public static void main(String[] args) {
        Parent parent1 = new Child();
        Child child1 = (Child) parent1;
        child1.childMethod();

        Parent parent2 = new Parent();
        Child child2 = (Child) parent2; // 런타임 오류 - ClassCastException
        child2.childMethod(); // 실행이 불가

        /*
        parent2는 다운캐스팅이 안되는 이유
        인스턴스가 생성 될 때 나의 부모가 같이 생성됨. 자식은 생성되지 않음.
        parent2는 부모가 없음. 자식만 있음 -> 자식의 정보는 인스턴스에 담기지 않음.

        어 다운캐스팅 되던데요? 걔는 부모를 다 만들어 버린거임. 그래서 조부-부모-자식이 인스턴스에 다 올라가서
        자유롭게 다운 캐스팅이 가능한거임.

        나보다 상위만 다 만들어짐. <- 잘 기억할 것.
         */
    }
}
