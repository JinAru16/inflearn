package lang.imutable.change;

public class MutableObject {
    private int value;

    public MutableObject(int value) {
        this.value = value;
    }

    public void add(int addValue){
        value = value + addValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
