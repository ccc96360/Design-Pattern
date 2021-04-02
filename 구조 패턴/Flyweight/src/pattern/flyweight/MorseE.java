package pattern.flyweight;

public class MorseE implements Flyweight{
    public MorseE() {
        System.out.println("MorseE 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("*");

        System.out.print(" ");
    }
}
