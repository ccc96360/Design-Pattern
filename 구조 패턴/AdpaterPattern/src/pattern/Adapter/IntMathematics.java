package pattern.Adapter;

public class IntMathematics implements Adapter{
    private Mathematics m;

    IntMathematics(){
        this.m = new Mathematics();
    }

    @Override
    public int twoTime(int num) {
        System.out.println("Int 2배");
        int ret = (int)this.m.twoTime((float) num);
        return ret;
    }

    @Override
    public int halfTime(int num) {
        System.out.println("Int 1/2배");
        int ret = (int) this.m.halfTime((float) num);
        return ret;
    }
}
