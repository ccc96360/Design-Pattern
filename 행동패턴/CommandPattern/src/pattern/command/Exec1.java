package pattern.command;

public class Exec1 implements Command{
    public Exec1() {
        System.out.println("Exec1 객체 생성");
    }

    @Override
    public void undo() {
        System.out.println("Exec1 명령 취소");
    }

    @Override
    public void run() {
        System.out.println("Exec1 명령 실행");
    }
}
