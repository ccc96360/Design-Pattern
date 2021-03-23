package pattern.factory;

public class Main {

    public static void main(String[] args) {
        //Korean ko = new Korean();
        //Hello h = new Hello(ko);
        Hello h = new Hello();
        h.greeting(Factory.KOR);
        h.greeting(Factory.ENG);
    }
}
