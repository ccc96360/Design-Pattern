package pattern.Decorator;

public class Main {

    public static void main(String[] args) {
	    Product1 p1 = new Product1();
        i7 spec1 = new i7(p1);
        ssd256 spec2 = new ssd256(spec1);

        System.out.println("상품:" + spec2.product());
        System.out.println("가격:" + spec2.price());

    }
}
