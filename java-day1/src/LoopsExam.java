import java.util.Scanner;

/**
 * Task
 *
 * Ada bilangan integer N , print 10 kali,
 * dimana setiap perulangan N x i (where 1<=10 i <=10) harus dicetak N x i =result.
 *
 * Input Format
 *
 * Single Integer N
 *
 * Constraints
 *
 * 2 <= N <=20
 */

public class LoopsExam {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Input number = ");
        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int result=0;
        for(int i=1;i<=10;i++){
            result=N*i;
            System.out.println(N+" x "+i+" = "+result);
        }

        scanner.close();
    }
}
