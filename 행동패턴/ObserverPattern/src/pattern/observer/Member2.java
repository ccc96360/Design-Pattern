package pattern.observer;

import java.util.Observable;

public class Member2 extends Observable {

    public void statusChange(){
        setChanged();
        notifyObservers();
    }
}
