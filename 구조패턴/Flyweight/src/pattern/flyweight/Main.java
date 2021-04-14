package pattern.flyweight;

public class Main {

    public static void main(String[] args) {
		String name = "gooogllleee";
		for(String letter: name.split("")){
			System.out.print(letter + "=>");
			FlyweightFactory.getCode(letter).code();
			System.out.println();
		}
    }
}
