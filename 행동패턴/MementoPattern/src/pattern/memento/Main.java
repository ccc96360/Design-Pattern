package pattern.memento;

public class Main {

    public static void main(String[] args) {
//    	HelloOriginator origin = new HelloOriginator();
//	    Hello hello = new Hello("상태1: 안녕하세요.");
//	    System.out.println(hello.getMsg());
//
//	    origin.setState(hello);
//	    HelloMemento memento = origin.createMemento();
//
//	    hello.setMsg("상태2: Hello nice to meet you.");
//	    System.out.println(hello.getMsg());
//
//	    origin.restoreMemento(memento);
//	    hello = origin.getState();
//	    System.out.println(hello.getMsg());


	    HelloOriginator originator = new HelloOriginator();
	    HelloCareTaker careTaker = new HelloCareTaker();

	    Hello hi = new Hello("상태1: 안녕!");
	    System.out.println(hi.getMsg());

	    originator.setState(hi);
	    HelloMemento memento1 = originator.createMemento();
	    careTaker.push(memento1);

	    hi.setMsg("상태2: Hi~ Nice to meet you");
	    System.out.println(hi.getMsg());

	    memento1 = originator.createMemento();
	    careTaker.push(memento1);

	    hi.setMsg("상태3: 무~야~호~");
	    System.out.println(hi.getMsg());

	    memento1 = originator.createMemento();
	    careTaker.push(memento1);

	    //상태 복원
		memento1 = careTaker.pop();
		originator.restoreMemento(memento1);
		hi = originator.getState();
	    System.out.println(hi.getMsg());

		memento1 = careTaker.pop();
		originator.restoreMemento(memento1);
		hi = originator.getState();
	    System.out.println(hi.getMsg());

		memento1 = careTaker.pop();
		originator.restoreMemento(memento1);
		hi = originator.getState();
	    System.out.println(hi.getMsg());
    }
}
