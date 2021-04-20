package pattern.interpreter;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        String lex = "{{ 1 1 + }}";
        Context context = new Context(lex);
        Stack<Expression> stack = new Stack<>();

        if(context.isStart()){
            System.out.println("연산 시작");
            while(true){
                String token = context.next();
                if(token.equals("}}")){
                    System.out.println("연산 종료");
                    break;
                }
                else if(token.matches("-?\\d+")){
                    stack.push(new Terminal(token));
                }
                else if(token.equals("+")){
                    Expression left = stack.pop();
                    Expression right = stack.pop();

                    Expression add = new Add(left, right);
                    String ret = add.interpret();
                    System.out.println(ret);
                    stack.push(new Terminal(ret));
                }
                else{
                    System.out.println("판별할 수 없는 해석입니다.");
                }
            }
        }
    }
}
