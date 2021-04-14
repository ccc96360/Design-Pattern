package pattern.Bridge;

public class Main {

    public static void main(String[] args) {
        Message message1 = new Message(new Korean());
        Message message2 = new Message(new English());

        System.out.println(message1.greeting());
        System.out.println(message2.greeting());
    }
}
