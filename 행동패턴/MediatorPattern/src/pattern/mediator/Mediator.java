package pattern.mediator;

import java.util.ArrayList;
import java.util.List;

public abstract class Mediator {
    List<Colleague> colleagues = new ArrayList<>();

    public void addColleague(Colleague colleague){
        colleagues.add(colleague);
    }

    abstract public void mediate(String data, String name);
}
