package pattern.interpreter;

import java.util.StringTokenizer;

public class Context {
    private StringTokenizer token;
    private String currentToken;
    public Context(String text) {
        token = new StringTokenizer(text, " ");
        next();
    }
    public Boolean isStart(){
        if (currentToken.equals("{{")){
            return true;
        }
        return  false;
    }

    public String next(){
        if(token.hasMoreTokens()) {
            currentToken = token.nextToken();
        }
        else currentToken = null;
        return currentToken;
    }
}
