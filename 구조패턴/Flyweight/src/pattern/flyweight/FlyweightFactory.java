package pattern.flyweight;

import java.util.HashMap;

public class FlyweightFactory {
    private static final HashMap<String, Flyweight> pool = new HashMap<>();

    public static Flyweight getCode(String alphabet){
        alphabet = alphabet.toUpperCase();
        Flyweight morseAlpha = (Flyweight) pool.get(alphabet);

        if(morseAlpha == null){
            try {
                morseAlpha = (Flyweight) Class.forName("pattern.flyweight.Morse" + alphabet).newInstance();
                pool.put(alphabet, morseAlpha);
            }
            catch (Exception e){

            }
        }
        return morseAlpha;
    }
}
