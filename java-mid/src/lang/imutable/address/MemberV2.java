package lang.imutable.address;

public class MemberV2 {
    private String name;
    private ImutableAddress address;

    public MemberV2(String name, ImutableAddress address) {
        this.name = name;
        this.address = address;
    }

    public ImutableAddress getAddress() {
        return address;
    }

    public void setAddress(ImutableAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberV1{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
