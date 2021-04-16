package pattern.status;

public class StateOrder implements State {
    @Override
    public void process() {
        System.out.println("주문");
    }
}
