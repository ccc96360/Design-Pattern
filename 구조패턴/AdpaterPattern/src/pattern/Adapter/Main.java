package pattern.Adapter;

public class Main {

    public static void main(String[] args) {
	    Mathematics m = new Mathematics();
	    System.out.println(m.twoTime(2f));
        System.out.println(m.halfTime(2f));

        IntMathematics m2 = new IntMathematics();
        System.out.println(m2.twoTime(2));
        System.out.println(m2.halfTime(2));
    }
}
