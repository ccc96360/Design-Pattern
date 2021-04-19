# 템플릿 메서드 패턴(Template Method Pattern)
#### 메서드를 이용해 각 단계를 템프릿 구조화하고 행동을 구분한다.

---
## 템플릿
#### 모든 샌드위치는 공통된 모양의 형틀을 갖고 있고 샌드위치를 만드는 원리와 만들어진 모습이 같다.
#### 즉 내용물은 다르지만 겉모양은 비슷하다.

#### 1. 공통 로직
> ###### 샌드위치는 2개의 빵 속에 속재료를 넣은 모양이다. 이는 어떤 샌드위치라도 동일하다.
> ###### 따라서 아래와같이 만들수 있다.
```java
public class Sandwich {
    public String make(){
        String food = bread();
        food += " + ";
        food += jam();
        food += " + ";
        food += bread();
        return food;
    }
    public String bread(){
        return "식빵";
    }
    public String jam(){
        return "딸기잼";
    }
}
```
#### 2. 템플릿
> ###### 위에서 샌드위치를 만들기 위한 공통 로직을 설계했고 ```make()``` 메서드를 반복적으로 실행하면 된다.
> ###### ```make()```메서드와 같이 공통된 단계적 과정을 ```템플릿```이라고한다

---
## 일반화
#### 클래스의 일반화는 공통점을 찾아 상위 클래스로 도출하는 과정이다.
#### 즉 1개의 클래스가 2개로 분리 된다.
#### 1. 클래스분리
> ###### 중복된 코드를 줄이기 위해 공통된 템플릿 로직을 분리한다.
```java
public abstract class Sandwich {
    public String make(){
        String food = bread();
        food += " + ";
        food += jam();
        food += " + ";
        food += bread();
        return food;
    }
    abstract String bread();
    abstract String jam();
}
```
> ###### 공통된 부분
> ###### 추상 클래스로 변경되었다.
```java
public class Strawberry extends Sandwich{
    @Override
    String bread() {
        return "식빵";
    }

    @Override
    String jam() {
        return "딸기잼";
    }
}
```
> ###### 공통되지 않은 부분

---
## 추상화
#### 템플릿 메서드 패턴은 추상 클래스의 특징을 잘 활용하여 적용한 디자인 패턴이다.
#### 1. 오버라이드
> ###### 다음은 위의 예제에서 ```식빵```대신 ```베이글```을 사용하는 경우이다.
```java
public class StrawberryBagel extends Strawberry{
    @Override
    String bread() {
        return "베이글";
    }
}
```
> ###### bread()를 오버라이드해 구현했다.
```java
    public static void main(String[] args) {
        Sandwich sandwich1 = new Strawberry();
        Sandwich sandwich2 = new StrawberryBagel();
        System.out.println(sandwich1.make());
        System.out.println(sandwich2.make());
    }
```
**실행 결과**
```aidl
식빵 + 딸기잼 + 식빵
베이글 + 딸기잼 + 베이글
```

#### 2. final 키워드
> ###### 템플릿은 하위 클래스에서 오버라이딩되지 않도록 방지해야 해야하는데 아래와 같이 ```final```키워드로 방지 할 수 있다.

```java
    public final String make(){
        ... ...
    }
```

---
## 템플릿 메서드

#### 1. 후크
> ###### 하위 클래스에서 구현되는 메서드를 후쿠(Hook)메서드, primitive 메서드라고 한다.
> ###### 후크는 중복된 코드를 제거하고 처리 로직의 일부를 변경할 떄 자주 사용하는 기법이다.
> ###### 템플릿 메서드는 추상 클래스를 통해 호출 방식을 미리 정의하고 하위 클래스에서 실체르 구현한다. 이러한 동작은 후크와 유사하다.

---
## 의존성 디자인
> ###### 템플릿 메서드 패턴에서는 구성요소간 상호 의존성이 발생한다.
> ###### 템플릿 메서드는 할리우드 원칙 이라는 역전 제어 구조를 사용하는데, 높은 수존의 구성요소가 낮은 수준의 구성요소에 의존한다.
> ###### 템플릿 메서드 패턴은 리스코프 치환 원칙도 함께 사용한다.
> > ###### 리스 코프 치환 원칙(LSP): 자료형 ```S```가 자료형 ```T```의 하위형이라면 속성의 변경 없이 ```T```를 ```S```로 교체 할수 있어야 한다.

---
## 관련 패턴
#### 팩토리 메서드 패턴, 전략 패턴

