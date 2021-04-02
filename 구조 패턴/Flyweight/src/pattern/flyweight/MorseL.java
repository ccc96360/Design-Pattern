package pattern.flyweight;

public class MorseL implements Flyweight{
    public MorseL() {
        System.out.println("MorseL 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("*");
        System.out.print("-");
        System.out.print("*");
        System.out.print("*");

        System.out.print(" ");
    }
}
