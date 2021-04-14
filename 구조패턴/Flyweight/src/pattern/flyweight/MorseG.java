package pattern.flyweight;

public class MorseG implements Flyweight{
    public MorseG() {
        System.out.println("MorseG 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("*");
        System.out.print("*");
        System.out.print("-");
        System.out.print("*");

        System.out.print(" ");
    }
}
