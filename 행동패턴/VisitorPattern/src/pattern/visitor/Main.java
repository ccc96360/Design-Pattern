package pattern.visitor;

public class Main {

    public static void main(String[] args) {
        Visitant visitant = new Visitant();
	    Cart cart = new Cart("컵라면",900,2);
	    Cart cart2 = new Cart("음료수",1900,5);
	    cart.accept(visitant);
	    cart2.accept(visitant);
    }
}
