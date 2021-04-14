package pattern.mediator;

public class Server extends Mediator{

    @Override
    public void mediate(String data, String name) {
        System.out.println(name+"로 부터 메시지");
        colleagues.forEach(colleague -> {
            colleague.message(data);
        });
    }
}
