package pattern.Facade;

public class Facade {
    private Package1 package1;
    private Package2 package2;
    private Package3 package3;

    public Facade() {
        this.package1 = new Package1();
        this.package2 = new Package2();
        this.package3 = new Package3();
    }

    // 패키지 동작 1,2,3을 한번에 수행 해야 되는
    // 복잡한 동작을 파사드 메서드로 생성한다.
    public void processAll(){
        this.package1.process();
        this.package2.process();
        this.package3.process();
    }
}
