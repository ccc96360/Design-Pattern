package pattern.status;

import java.util.HashMap;

public class Order {
    HashMap<String, State> states = new HashMap<>();

    public Order() {
        states.put("ORDER", new StateOrder());
        states.put("PAY", new StatePay());
        states.put("ORDERED", new StateOrdered());
        states.put("FINISH", new StateFinish());
    }
    public void process(String status){
        this.states.get(status).process();
    }
}
