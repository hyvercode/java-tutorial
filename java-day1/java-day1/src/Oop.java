import com.solusione.inheritance.Car;

import java.io.Serializable;

public class Oop implements Serializable {

    private final String active="Y";

    public static void main(String[] arg){

        //create aldy
        Person aldy = new Person("aldy",32);
        printPerson(aldy);

        //create wahyu
        Person wahyu = new Person();
        wahyu.setName("Wahyu");
        wahyu.setAge(23);
        printPerson(wahyu,"single");

        //Create animal
        Animal harimau = new Animal("Harimau",4);
        printAnimal(harimau);

        Animal chiken = new Animal("Chiken",2);
        printAnimal(chiken);

    }

    public static void printPerson(Person person){
        System.out.println("Name is :"+person.getName());
        System.out.println("Age is :"+person.getAge());
    }

    public static void printPerson(Person person,String status){
        System.out.println("Name is :"+person.getName());
        System.out.println("Age is :"+person.getAge());
        System.out.println("Status :"+status);
    }

    public static void printAnimal(Animal animal){
        System.out.println("Name is :"+animal.getType());
        System.out.println("Foot  :"+animal.getFoot());
    }
}
