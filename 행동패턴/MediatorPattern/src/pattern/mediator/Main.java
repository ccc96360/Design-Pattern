package pattern.mediator;

public class Main {

    public static void main(String[] args) {
	    Server server = new Server();

	    User user1 = new User("User A");
	    User user2 = new User("User B");
	    User user3 = new User("User C");

	    user1.setMediator(server);
	    server.addColleague(user1);
	    user2.setMediator(server);
	    server.addColleague(user2);
	    user3.setMediator(server);
	    server.addColleague(user3);

	    user1.send("Hi~!");
	    user2.send("Hello~!");
	    user3.send("무~야~호~");
    }
}
