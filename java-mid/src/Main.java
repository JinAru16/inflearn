import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a = {0, 1};
        int n = a.length;
        int missing =0;
        for(int i=0; i<n; i++){
            int finalI = i;
            if(Arrays.stream(a).anyMatch(value -> finalI == value)){
                continue;
            }else{
                missing = i;
                break;
            }
        }
        if(missing == 0){
            missing = n;
        }

        System.out.println(missing);
    }
}