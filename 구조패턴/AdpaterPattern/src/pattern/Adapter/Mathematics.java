package pattern.Adapter;

public class Mathematics {

    public float twoTime(float num){
        System.out.println("Float형 2배");
        return num * 2;
    }
    public float halfTime(float num){
        System.out.println("Float형 1/2배");
        return num / 2;
    }
}
