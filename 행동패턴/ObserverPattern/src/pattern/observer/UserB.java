package pattern.observer;

public class UserB extends Observer{
    public UserB(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        System.out.println(this.name+"업데이트~!");
    }
}
