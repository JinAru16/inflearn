package enumeration.test;

public class AuthGradeMain1 {
    public static void main(String[] args) {
        AuthGrade[] authGrades = AuthGrade.values();
        for (AuthGrade authGrade : authGrades) {
            printAuthGrade(authGrade);
        }
    }

    public static void printAuthGrade(AuthGrade authGrade){
        System.out.println("grade : "+authGrade.name() +", 레벨 : "+authGrade.getLevel()+", 손님 : "+ authGrade.getDescription());
    }
}
