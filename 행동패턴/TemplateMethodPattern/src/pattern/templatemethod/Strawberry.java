package pattern.templatemethod;

public class Strawberry extends Sandwich{

    @Override
    String bread() {
        return "식빵";
    }

    @Override
    String jam() {
        return "딸기잼";
    }
}
