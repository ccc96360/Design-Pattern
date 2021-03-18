package pattern.singleton;

public class Main {

    public static void main(String[] args) {
        Config c = Config.getInstance();
        Config c1 = Config.getInstance();
        Config c2 = Config.getInstance();
        Config c3 = Config.getInstance();

    }
}
