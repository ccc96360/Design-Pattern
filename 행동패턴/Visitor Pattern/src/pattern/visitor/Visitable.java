package pattern.visitor;

public interface Visitable {
    public void accept(Visitor visitor);
}
