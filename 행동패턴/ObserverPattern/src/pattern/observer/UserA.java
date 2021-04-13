package pattern.observer;


public class UserA extends Observer {
    public UserA(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(this.name+"업데이트~!");
    }
}
