package pattern.command;

public class Exec2 implements Command{
    private Concrete receiver;

    public Exec2(Concrete receiver) {
        this.receiver = receiver;
    }

    @Override
    public void undo() {
        System.out.println("Exec2 취소!!");
    }

    @Override
    public void run() {
        receiver.action1();
        receiver.action2();
    }
}
