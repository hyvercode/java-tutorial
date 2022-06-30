public class NonStatic {

    public static void main(String[] args){
        NonStatic nonStatic = new NonStatic();
        nonStatic.printMe("Aldi");
    }

    void printMe(String name){
        System.out.println("Print Me! "+name);
    }

}
