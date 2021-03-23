#팩토리 메서드 패턴
##### 팩토리 패턴의 확장 패턴으로, 팩토리 패턴과 템플릿 메서드 패턴이 결합된 패턴이다. 

---
## 패턴 확장
> ###### 추상화를 통해 팩토리 패턴을 확장한다.
> ###### 다음은 피자를 만드는 팩토리 예제이다.
```java
public interface Pizza {
    public void prepare();
    public void bake();
    public void box();
}

abstract public class PizzaFactory {
    public final Pizza create(String type){
        Pizza p = createPizza(type);
        p.prepare();
        p.bake();
        p.box();
        return p;
    }
    abstract public Pizza createPizza(String type);
}


```
> ###### 위 와 같이 팩토리를 ```abstract```클래스로 변경 한다.
> ###### 실제 구현은 하위 클래스로 위임한다.
## 상위 클래스
> ###### 팩토리 메서드 패턴은 팩토리 패턴에 추상화를 적용 한 것 이므로, 상위 클래스 하위 클래스로 분리 된다.
> ###### 일반 클래스가 추상화 되면, 일반적인 클래스 보다 결합 관계가 더 느슨한 관계로 변경된다.
> ###### 기존 팩토리가 고정적인 생성 패턴들만  결합한다면, 팩토리 메서드는 느슨한 생성으로 결합한다.
> ###### 위 예제의 PizzaFactory에 해당한다.
## 하위 클래스
> ###### 이제 PizzaFactory를 상속받아 createPizza를 구현 하면된다.
> ###### 예제는 아래와 같다.
```java
public class MsPizza extends PizzaFactory {
    @Override
    public Pizza createPizza(String type) {
        if("cheese".equals(type)){
            return new MsCheesePizza();
        }
        else if("bulgogi".equals(type)){
            return new MsBulgogiPizza();
        }
        return null;
    }
}
```
> ###### ```MsPizza```에서 PizzaFactory를 상속받아 createPizza메소드를 구현했다.
```java
public class MsCheesePizza implements Pizza{
    MsCheesePizza(){
        System.out.println("맛있는 미스 치즈 피자~");
    }
    ......
public class MsBulgogiPizza implements Pizza{
    MsBulgogiPizza(){
        System.out.println("더 맛있는 미스 불고기 피자!");
    }
    ......
...    
public static void main(String[] args) {
    PizzaFactory msPizza = new MsPizza();
    Pizza msCheesePizza = msPizza.createPizza("cheese");
    Pizza msBulgogiPizza = msPizza.createPizza("bulgogi");
}
...
```
#####실행 결과
```aidl
맛있는 미스 치즈 피자~
더 맛있는 미스 불고기 피자!
```
> ###### 
## 개방-폐쇄 원칙 (Open-Closed Principle, OCP)
> ###### 객체 지향의 설계 원칙 중 하나로, 바뀌지 않는 공통된 부분을 분리하여 관리한다.
> ###### OCP 원칙을 적용하는 대표적인 방법은 다형성 이다.
> ###### 팩토리 패턴은 객체 생성을 다른 클래스로 캡슐화 처리 한것이다. 하지만, 팩토리 메서드의 경우 추상화를  통해
> ###### 객체 생성을 더욱 유연하게 분리할 수 있다. 코드를 분리할때 OCP원칙을 적용할 수 있다.

## 다형성을 이용한 클래스 선택
```java


public class PizzaCool extends PizzaFactory{
    @Override
    public Pizza createPizza(String type) {
        if("cheese".equals(type)){
            return new CheesePizzaCool();
        }
        else if("bulgogi".equals(type)){
            return new BulgogiPizzaCool();
        }
        return null;
    }
}
public class CheesePizzaCool implements Pizza{
    CheesePizzaCool(){
        System.out.println("치즈 피자 쿨~");
    }
    ......
public class BulgogiPizzaCool implements Pizza{
    BulgogiPizzaCool(){
        System.out.println("불고기 피자 쿨~");
    }
    ......
...
public static void main(String[] args) {
    PizzaFactory msPizza = new MsPizza();
    PizzaFactory pizzaCool = new PizzaCool();
    Pizza msCheesePizza = msPizza.createPizza("cheese");
    Pizza msBulgogiPizza = msPizza.createPizza("bulgogi");
    Pizza cheesePizzaCool = pizzaCool.createPizza("cheese");
    Pizza bulgogiPizzaCool = pizzaCool.createPizza("bulgogi");
}
....
```

##### 실행 결과
```aidl
맛있는 미스 치즈 피자~
더 맛있는 미스 불고기 피자!
치즈 피자 쿨~
불고기 피자 쿨~
```
> ###### 위와 같이 객체생성을 Ms피자와 피자Cool 처럼 군집화하여 관리 할 수 있다.
> ###### 객체의 생성이 한 곳에 집중되 수정이 필요 할 경우 유지보수가 쉽다.


##관련 패턴
> ###### 템플메서드 패턴, 싱글톤 패턴, 복합체 패턴, 반복자 패턴