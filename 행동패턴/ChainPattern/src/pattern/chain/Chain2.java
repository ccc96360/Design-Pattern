package pattern.chain;

public abstract class Chain2 {
    protected Chain2 next;
    public void setNext(Chain2 nextItem){
        this.next = nextItem;
    }
    public final void support(String event){
        if(resolve(event)){
            execute(event);
        }
        else if(next != null){
            next.support(event);
        }
        else{
            System.out.println("어떤 객체도 처리하지 못함");
        }
    }
    abstract boolean resolve(String event);
    abstract void execute(String event);
}
