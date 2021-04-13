package pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Member implements Subject{
    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyy() {
        observers.forEach(o -> o.update());
    }
}
