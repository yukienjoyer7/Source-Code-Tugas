import java.util.*;
public class CakBum {
    private static final Scanner scan = new Scanner (System.in);
    public static void main(String[] args) {

        int N = scan.nextInt();
        int a = scan.nextInt();
        int b = scan.nextInt();
        scan.close();
        
        if (a != b){
            for (int i = 0; i <= N; i++){
                 if (i % a == 0 && i % b == 0){
                    System.out.println("BumCak");
                }
                else if (i % a == 0){
                    System.out.println("Bum");
                }
                else if (i % b == 0){
                    System.out.println("Cak");
                }
                else{
                    System.out.println(i);
                }
            }
        }
        else{
            for (int i = 0; i <= N; i++){
            if (i % a == 0){
                System.out.println("CakBum");
            }
            else{
                System.out.println(i);
            }
        }
    }
}
}
