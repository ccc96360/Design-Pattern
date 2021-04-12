package pattern.chain;

public class Main {
    static class Handler {
        public void event(String msg){
            Chain first = new Order();
            first.setNext(new Cancel());

            first.execute(msg);
        }
    }
    static class Handler2 {
        public void event(String msg){
            Chain2 first = new Order2();
            first.setNext(new Cancel2());

            first.support(msg);
        }
    }
    public static void main(String[] args) {
        Handler handler = new Handler();

        handler.event("cancel");

        Handler2 handler2 = new Handler2();
        handler2.event("order");
        handler2.event("cancel");
        handler2.event("qwerasdzxc");

    }
    public static void ordered(){}
    public static void cancel(){}
}
