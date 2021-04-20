package pattern.interpreter;

//Non-Terminal Expression
public class Add implements Expression{
    private Expression left;
    private Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return Integer.toString(Integer.parseInt(left.interpret()) + Integer.parseInt(right.interpret()));
    }
}
