package pattern.mediator;

public class User extends Colleague{
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    void send(String data) {
        this.mediator.mediate(data, this.name);
    }

    @Override
    void message(String data) {
        System.out.println(this.name+"가 받은 메시지:"+data);
    }
}
