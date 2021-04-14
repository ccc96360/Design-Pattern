package pattern.Bridge;

public class Message extends Language{

    Message(Hello lang){
        this.language = lang;
    }
    @Override
    public String greeting() {
        return this.language.greeting();
    }
}
