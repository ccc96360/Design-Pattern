package pattern.status;

public class StatePay implements State {
    @Override
    public void process() {
        System.out.println("결제중");
    }
}
