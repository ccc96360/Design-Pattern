package pattern.command;

public class Main {

    public static void main(String[] args) {
        Command cmd1 = new Exec1();

        cmd1.run();

        System.out.println("====리시버 연결====");
        //리시버 연결
        Concrete receiver = new Concrete();
        Command cmd2 = new Exec2(receiver);

        cmd2.run();

        System.out.println("===인보커 연결===");
        //인보커 연결
        Command cmd3 = new Exec1();

        Concrete receiver2 = new Concrete();
        Command cmd4 = new Exec2(receiver2);

        Invoker invoker = new Invoker();
        invoker.setCommand("exec1", cmd3);
        invoker.setCommand("exec2", cmd4);

        invoker.run("exec2");
        invoker.run("exec1");

        System.out.println("===익명 클래스===");
        // 어나니머스 클래스 활용
        Invoker invoker2 = new Invoker();
        invoker2.setCommand("cmd1",
                new Command(){
                    @Override
                    public void run() {
                        System.out.println("익명 클래스로 실행한다~!");
                    }

                    @Override
                    public void undo() {

                    }
                }
        );
        invoker2.run("cmd1");

        System.out.println("===Undo 실행===");
        //undo
        Exec1 cmd5 = new Exec1();

        cmd5.run();
        cmd5.undo();

        System.out.println("===인보커 언두===");
        // 인보커 undo
        Exec1 cmd6 = new Exec1();

        Concrete receiver3 = new Concrete();
        Exec2 cmd7 = new Exec2(receiver3);

        Invoker invoker3 = new Invoker();
        invoker3.setCommand("cmd1",cmd6);
        invoker3.setCommand("cmd2",cmd7);

        invoker3.run("cmd2");
        invoker3.run("cmd1");

        invoker3.undo();
        invoker3.undo();
        invoker3.undo();
    }
}
