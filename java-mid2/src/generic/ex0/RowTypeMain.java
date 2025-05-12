package generic.ex0;

public class RowTypeMain {
    public static void main(String[] args) {
        GenericBox integerBox = new GenericBox(); // <> 안에 제네릭 안적어주면 Object Type으로 그냥 쓰는것과 다름이 없다.
        //GenericBox<ObjectBox> integerBox =  new GenericBox<>();  권장
        integerBox.set(1);
    }
}
