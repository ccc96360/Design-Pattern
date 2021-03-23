package pattern.AbstractFactory;
public class Main {


    public static void main(String[] args) {
    	Factory koreaFactory = new KoreaFactory();
    	Factory usaFactory = new USAFactory();
	    Car myCar1 = new KiaCar(koreaFactory);
	    Car myCar2 = new FordCar(usaFactory);

	    myCar1.build();
	    myCar2.build();

    }
}
