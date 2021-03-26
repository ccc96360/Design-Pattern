# 어댑터 패턴 (Adapter Pattern)
#### 코드를 재사용 하기위해 구조를 변경하는 패턴이다.

---

## 코드의 변화
> ###### 개발자는 자신의 코드가 샹후 재사용되도록 하기 위해 다양한 변화를 예측한다.
> ###### 그리고 그 예측에 맞춰 코드를 설계한다. 하지만 모든 상황을 완벽히 예측하여 설께할 수는 없다.
> ###### 미세한 동작 변화, 데이터 타입 불일치, 매개변수 인자값 불일치, 반환값 타입 등 다양한 차이가 발생한다.
> ###### 따라서 코드를 재사용하기 위해서는 변환(변형) 작업이 필요하다.

## 인터페이스
> ###### 기존 코드를 재사용하기 위해서 동작 변환 작업뿐 아니라 외부적인 인터페이스 형식도 일치 시켜야한다.
> ###### 어댑터 패턴은 기존의 코드를 재사용하기 위해 내적, 외적 구조를 변환하는 작업을 처리한다.

## 잘못된 코드
> ###### 세상에 완벽한 코드는 없다.
#### 1. 수정 불가
> ###### 원본 소스코드를 분실해서 컴파일된 파일만 남아있는경우 코드를 수정 할 수 없다.
> ###### 이런 경우 오류가 포함된 코드를 감싸 보정 코드를 만들어 사용한다.
> ###### 즉 보정 코드를 통해 문제점을 우회한다.
> ###### 장식자 패턴(Decorator Pattern)도 이러한 보정 코드를 통해 기능을 우회 하기도 한다.
#### 2. 보정 코드
> ###### 보정 코드를 만드는 방법은 매우 다양하다.
> ###### 보정 동작이 여러 곳에 분포돼 있다면 많응 영역의 코드가 수정돼야 한다. 또한, 가독성도 떨어진다.
> ###### 이럴 경우 보정을 처라하는 별도의 객체를 생성하는것이 좋다. 이처럼 보정만을 위해 설계된 객체를 어댑터 패턴이라고 한다.

## 어댑터
> ###### 어댑터 패턴은 다른 말로 래퍼패턴(Wrapper Pattern)이라고도 한다.
> ###### 즉 기존의 클래스를 새로운 클래스로 감싸는것 이다.
> ###### 래퍼 처리된 객체를 어댑터라고 한다.

#### 중개 행동 패턴
> ###### 어댑터 패턴은 2개의 클래스를 중개한다고 해서 중개 패턴으로도 불린다.
> ###### 중개는 새로운 기능을 제공하는 것이 아니라 단순한 변환과 전달 역할만을 목적으로 한다.

#### 클라이언트
> ###### 어댑터 패턴을 적용하면 클라이언트 입장에서는 변화된 것이 없는 것처럼 사용할 수 있다.

#### 클래스 어댑터
> ###### 다중 상속을 이용한다.강한 결합이 형성되며 별도의 변화 작ㅇ버 없이 기존의 코드를 그대로 사용할 수 있다.

## 객체 어댑터
> ###### 객체 어댑터는 객체의 의존성을 이용해 문제를 해결한다.
#### 1.구성
> ###### 객체 어댑터는 내부적으로 객체를 재구성한다. 기존 객체는 복합 객체로 변환된다.
> ###### 객체 어댑터의 구성은 변환될 객체를 의존성 관계로 연결한다.
> ###### 의존성 연결은 어댑터 객체에서 직접 생성 하거나 외부에서 주입 할 수 있다.
> ###### 변경된 객체는 서브클래스의 동작도 같이 처리한다.
#### 2. 캡슐화
> ###### 어댑터는 변경된 인터페이스로 캡슐화 됐기 때문에, 클라이언트에서 변화를 눈치채지 못한채 그대로 사용한다.
#### 3. 설계
###### 기존코드
```java
public class Mathematics {

    public float twoTime(float num){
        System.out.println("Float형 2배");
        return num * 2;
    }
    public float halfTime(float num){
        System.out.println("Float형 1/2배");
        return num/2;
    }
}
    ...
    public static void main(String[] args) {
        Mathematics m = new Mathematics();
        System.out.println(m.twoTime(2f));
        System.out.println(m.halfTime(2f));
    }
    ...
```
###### 실행 결과
```aidl
Float형 2배
4.0
Float형 1/2배
1.0
```
> ###### 위와 같이 실수를 2배 1/2배 해주는 클래스가 있다.
> ###### 그런데 정수의 입력을 처리해야 하는경우 기존 ```Mathematics```클래스를 사용 할 수 없다.
> ###### ```Mathematics```클래스를 수정 할 수 없는 경우 어댑터 패턴을 이용해 인터페으스 변경을 통해 문제를 해결한다.
###### 어댑터 제작
```java
public interface Adapter {
    public int twoTime(int num);
    public int halfTime(int num);
}
```
> ###### 위와 같이 최종 객체가 가져야할 인터 페이스를 정의한다.

```java
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
    ...
    public static void main(String[] args) {
        Mathematics m = new Mathematics();
        System.out.println(m.twoTime(2f));
        System.out.println(m.halfTime(2f));

        IntMathematics m2 = new IntMathematics();
        System.out.println(m2.twoTime(2));
        System.out.println(m2.halfTime(2));
    }
    ...
```
> ###### ```Adapter``` 인터페이스를 적용해 클래스를 새로 구현 한다.
> ###### 이때, 기존 클래스를 상속 받지 않고 내부에 객체를 생성한다.
###### 실행 결과
```
Float형 2배
4.0
Float형 1/2배
1.0
Int 2배
Float형 2배
4
Int 1/2배
Float형 1/2배
1
```
> ###### 위와 같이 기존 클래스와 메소드를 수정하지 않고 자신이 원하는 형태로 변경된다.

## 관련 패턴
> ###### 파사드 패턴, 브리지 패턴, 장식자 패턴