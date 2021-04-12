package pattern.chain;

public abstract class Chain {
    protected Chain next;
    public void setNext(Chain nextItem){
        this.next = nextItem;
    }
    abstract void execute(String event);
}
