package pattern.mediator;

public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
    abstract void send(String data);
    abstract void message(String data);
}
