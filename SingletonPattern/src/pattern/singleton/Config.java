package pattern.singleton;

public class Config {
    private static Config self = null;
    protected Config(){
        System.out.println("Cofing 인스턴스 생성.");
    }
    public static Config getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final Config INSTANCE = new Config();
    }
}
