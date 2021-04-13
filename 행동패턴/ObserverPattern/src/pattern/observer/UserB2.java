package pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class UserB2 implements Observer {
    private String name;

    public UserB2(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(this.name+"업데이트~!");
    }
}
