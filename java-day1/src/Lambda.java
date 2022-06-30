import java.util.ArrayList;

public class Lambda {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);

        //with lambda
        numbers.forEach( (n) -> { System.out.println("With lambda "+n); } );

        //without lambda
        printNumbers(numbers);
    }

    private static void printNumbers(ArrayList<Integer> numbers){
        for (int number:numbers) {
            System.out.println("Number "+number);
        }
    }
}
