package pattern.factory;

public class Hello {
    private final int KOR = 1;
    private final int ENG = 2;

    //private Language ko;
    private Language lang;

    Hello(){}
    Hello(Korean v){
        // 의존성이 외부적으로 발생 헀다.
        // 의존성 주입이라고 한다.
        //this.ko = v;
    }

    public void greeting(int type){
        //System.out.println("안녕하세요");

        // 의존성이 내부적으로 발생 헀다.
        // Korean 객체와 Hello 객체 간 의존성 발생
        // Korean 객체를 직접 정했으므로 '강력한 결합 관계' 이다.
        // 유지 보수시 유연한 코드 확장을 방해하고 변경과 수정을 어렵게 만드는 원인이 된다.
        //Korean ko = new Korean();
        //ko.text();

        this.lang = Factory.getInstance(type);
        this.lang.text();

        System.out.println("단순 팩토리 패턴!!");
        lang = this.factory(this.KOR);
        this.lang.text();
        lang = this.factory(this.ENG);
        this.lang.text();
    }

    // 단순(정적) 팩토리 패턴
    private Language factory(int type){
        switch (type){
            case KOR:
                return new Korean();
            case ENG:
                return new English();
        }
        return null;
    }
}
