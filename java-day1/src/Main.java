import com.solusione.MainCRUD;
import com.solusione.enums.Level;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        /**
         * This first
         */

        // Single comment
        System.out.println("Helo world");
        System.out.print("Hello World! ");
        System.out.println("I will print on the same line.");

        //Variable
        System.out.println("======================== String variable ==================================");
        String exString = "Is String";
        System.out.println("Is example string "+exString);

        System.out.println("======================== int variable ==================================");
        int exInt = 10;
        System.out.println("Is example int "+exInt);

        System.out.println("======================== float variable ==================================");
        float exFloat = 5.0f;
        System.out.println("Is example float "+exFloat);

        System.out.println("======================== char variable ==================================");
        char exChar = 'D';
        System.out.println("Is example char "+exChar);

        System.out.println("======================== boolean variable ==================================");
        boolean exBoolean = true;
        System.out.println("Is example boolean "+exBoolean);

        System.out.println("======================== interface ==================================");
        MainCRUD bank = new MainCRUD();
        bank.create("CREATE");
        bank.read("READ");
        bank.update("UPDATE");
        bank.delete("DELETE");

        System.out.println("======================== enum ==================================");
        Level spicy = Level.LEVEL3;
        System.out.println("Your level is "+spicy);

        System.out.println("======================== ArrayList ==================================");
        Animal cat = new Animal("Cat",4);
        Animal dog = new Animal("Dog",4);
        Animal chiken = new Animal("Chicken",2);

        ArrayList<Animal> animalArrayList = new ArrayList<>();
        animalArrayList.add(cat);
        animalArrayList.add(dog);
        animalArrayList.add(chiken);

        System.out.println("List of animal"+ animalArrayList);

    }
}
