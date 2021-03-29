package pattern.Composite;

public abstract class Component {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    abstract void printSub(String path);
}
