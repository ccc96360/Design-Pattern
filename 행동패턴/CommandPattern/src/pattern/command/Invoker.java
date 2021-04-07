package pattern.command;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Invoker {
    HashMap<String,Command> cmd = new HashMap<String,Command>();
    Stack<Command> undoStatus = new Stack<Command>();

    public void setCommand(String key, Command command){
        cmd.put(key, command);
    }

    public void removeCommand(String key){
        cmd.remove(key);
    }

    public void run(String key){
        if(cmd.containsKey(key)){
            Command command = cmd.get(key);
            undoStatus.push(command);
            command.run();
        }
    }

    public void undo(){
        if(!undoStatus.isEmpty()){
            Command command = undoStatus.pop();
            command.undo();
        }
    }
}
