package pattern.memento;

import java.util.Stack;

public class HelloCareTaker {
    private Stack<HelloMemento> stack = new Stack<>();

    public void push(HelloMemento memento){
        stack.push(memento);
    }
    public HelloMemento pop(){
        return stack.pop();
    }
}
