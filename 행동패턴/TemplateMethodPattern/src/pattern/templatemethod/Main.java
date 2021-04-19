package pattern.templatemethod;

public class Main {

    public static void main(String[] args) {
        Sandwich sandwich1 = new Strawberry();
        Sandwich sandwich2 = new StrawberryBagel();
	    System.out.println(sandwich1.make());
	    System.out.println(sandwich2.make());
    }
}
