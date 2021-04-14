package pattern.flyweight;

public class MorseO implements Flyweight{
    public MorseO() {
        System.out.println("MorseO 객체 생성");
    }

    @Override
    public void code() {
        System.out.print("-");
        System.out.print("-");
        System.out.print("-");

        System.out.print(" ");
    }
}
