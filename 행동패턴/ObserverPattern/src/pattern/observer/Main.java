package pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Main {
    public static void main(String[] args) {
//	    Subject subject = new Member();
//	    UserA a = new UserA("A");
//	    subject.attach(a);
//	    UserB b = new UserB("B");
//	    subject.attach(b);
//	    subject.notifyy();

		Member2 subject2 = new Member2();
		Observer a2 = new UserA2("A2");
		subject2.addObserver(a2);
		subject2.statusChange();
		Observer b2 = new UserB2("B2");
		subject2.addObserver(b2);
		subject2.statusChange();
    }
}
