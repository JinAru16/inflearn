package generic.ex0;

public class RowTypeMain {
    public static void main(String[] args) {
        GenericBox integerBox = new GenericBox(); // <> 안에 제네릭 안적어주면 Object Type으로 그냥 쓰는것과 다름이 없다. 원시타입. Row타입이라 함
        // 자바버젼 하위 호환을 위해 존재하는것이지 쓰면 안됨. Object로 쓸거면 GenericBox<Object> <- 이렇게 object를 명시해주자.
        //GenericBox<ObjectBox> integerBox =  new GenericBox<>();  권장
        integerBox.set(1);
    }
}
