package pattern.memento;

public class HelloOriginator {
    private Hello state;

    public HelloMemento createMemento(){
        return new HelloMemento(this.state);
    }

    public void restoreMemento(HelloMemento memento){
        this.state = memento.getObj();
    }

    public Hello getState() {
        return state;
    }

    public void setState(Hello state) {
        this.state = state;
    }
}
