import com.solusione.MainCRUD;
import com.solusione.enums.Level;

import java.util.*;

public class Main {
    public static void main(String[] args) {
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
        System.out.println("Is example string " + exString);

        System.out.println("======================== int variable ==================================");
        int exInt = 10;
        System.out.println("Is example int " + exInt);

        System.out.println("======================== float variable ==================================");
        float exFloat = 5.0f;
        System.out.println("Is example float " + exFloat);

        System.out.println("======================== char variable ==================================");
        char exChar = 'D';
        System.out.println("Is example char " + exChar);

        System.out.println("======================== boolean variable ==================================");
        boolean exBoolean = true;
        System.out.println("Is example boolean " + exBoolean);

        System.out.println("======================== interface ==================================");
        MainCRUD bank = new MainCRUD();
        bank.create("CREATE");
        bank.read("READ");
        bank.update("UPDATE");
        bank.delete("DELETE");

        System.out.println("======================== enum ==================================");
        Level spicy = Level.LEVEL3;
        System.out.println("Your level is " + spicy);

        System.out.println("======================== ArrayList ==================================");
        Animal cat = new Animal("Cat", 4);
        Animal dog = new Animal("Dog", 4);
        Animal chiken = new Animal("Chicken", 2);

        ArrayList<Animal> animalArrayList = new ArrayList<>();
        animalArrayList.add(cat);
        animalArrayList.add(dog);
        animalArrayList.add(chiken);
        System.out.println("List of animal" + animalArrayList);

        System.out.println("======================== LinkedList Person ==================================");

        Person aldi = new Person("Aldi", 23);
        Person wahyu = new Person("Wahyu", 27);
        Person dika = new Person("Dika", 25);

        LinkedList<Person> personLinkedList = new LinkedList<>();
        personLinkedList.add(aldi);
        personLinkedList.add(wahyu);
        personLinkedList.add(dika);
        System.out.println("List of persons" + personLinkedList);

        System.out.println("======================== HashMap Person ==================================");
        HashMap<String, String> users = new HashMap<>();
        users.put("aldi@gmail.com", "aldy123");
        users.put("wahyu@gmail.com", "aldy123");
        System.out.println("List of userd " + users);

        System.out.println("======================== HashMap Person ==================================");
        HashMap<String, ArrayList> myObjects = new HashMap<>();
        myObjects.put("animal", animalArrayList);
        System.out.println("List of myObject " + myObjects);

        System.out.println("======================== HashSet ==================================");
        HashSet<String> cars = new HashSet<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("BMW");
        cars.add("Mazda");
        System.out.println(cars);

        System.out.println("======================== Iterator ==================================");
        Iterator<String> iterate = cars.iterator();
        System.out.println("Iterate cars " + iterate.next());


        System.out.println("======================== Ternary operator ==================================");
        int a = 100;
        int nilai = 50;

        System.out.println(nilai == a ? "A" : "B");

        if (nilai == 50) System.out.println("B");
    }
}
