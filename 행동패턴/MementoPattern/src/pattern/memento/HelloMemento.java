package pattern.memento;

public class HelloMemento {
    private Hello obj;

    public HelloMemento(Hello obj) {
        try {
            this.obj = (Hello) obj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public Hello getObj() {
        return obj;
    }
}
