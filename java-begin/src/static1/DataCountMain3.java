package static1;

public class DataCountMain3 {
    public static void main(String[] args) {
        Data3 data1 = new Data3("A");
        System.out.println("A couont =" + Data3.count);

        Data3 data2 = new Data3("B");
        System.out.println("B couont =" + Data3.count);

        Data3 data3 = new Data3("C");
        System.out.println("C couont =" + Data3.count);

        // 추가
        // 인스턴스를 통한 접근 -> 인스턴스에 접근을 해봤더니 static이네? 다시 static 영역에 가서 불러오는 방식
        /*
        관례상 별로 권장하지 않음. 왜냐? 인스턴스 변수인지 static 변수인지 구별이 잘 안되기 때문.
         */
        Data3 data4 = new Data3("D");
        System.out.println(data4.count);

        //클래스를 통한 접근
        System.out.println(Data3.count);


    }
}
