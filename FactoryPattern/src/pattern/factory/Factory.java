package pattern.factory;

public class Factory {
    static final int KOR = 1;
    static final int ENG = 2;
    static public Language getInstance(int type){
        System.out.println("Factory 객체를 통한 객체생성!");
        switch (type) {
            case KOR:
                return new Korean();
            case ENG:
                return new English();
        }
        return null;
    }
}
