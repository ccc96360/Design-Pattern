package pattern.templatemethod;

public abstract class Sandwich {
    public String make(){
        String food = bread();
        food += " + ";
        food += jam();
        food += " + ";
        food += bread();
        return food;
    }
    abstract String bread();
    abstract String jam();
}
