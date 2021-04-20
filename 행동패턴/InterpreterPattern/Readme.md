# 인터프리터 패턴 (Interpreter Pattern)
#### 간단한 언어적 문법을 표현하는 패턴이다.

---
## 언어 설계
#### 인터프리터 패턴은 간단한 ```미니언어```를 구현하는 패턴이다.
#### 간단한 언어의 문법을 정의하고 그 언어의 문장을 해석하는 방법을 구현한다.
#### 1. 문법 표현
> ###### 언어 설계의 첫 단추는 규칙을 작성하는 것이다.
> ###### 언어문법을 표기하는 방법은 크게 두종류 이다.
> > #### 1. 구문 도표 표기법: 그림으로 표현
> > #### 2. 배커스-나우르 표기법(Backus-Naur Form: ```BNF```): 문자로 표현
> ###### 구문 도표는 그림을 표현하기 때문에 쉽게 이해할 수 있지만, 수정이 어렵다는 점에서 실제 현장에서는 ```BNF```표기법을 더 많이 이용한다.

#### 2. BNF 표기법
> ###### 3가지 메타 기호가 있다. ```::== (정의)``` ```| (선택)``` ```<> (비단말 기호)```
> ###### 예: ```<if문> ::== if <논리식> then <문장>```, ```<if문> ::== if <논리식> then <문장> else <문장> | if <논리식> then <문장>```

#### 3. 인터프리터 패턴 구조
* 추상 구문 트리 인터페이스(Abstract Expression)
* 종료 기호(Terminal Expression)
* 비종료 기호(Non-Terminal Expression)
* 해석기 정보(Context)
* 문장을 나타내는 추상 구문 트리(Client)

---
## 처리계
#### 새로운 언어 모델을 BNF 표기법으로 설계했다면, 실제 해석하고 처리할 구현부를 작성해야한다.
#### 해석자의 처리계는 크게 ```어휘 분석```과 ```구문 분석```으로 나뉜다.
#### 1. 어휘 분석
> ###### 문장으로 작성된 어휘(Lex)는 텍스트 문자열이고, 어휘 분석은 문장의 텍스트를 토큰으로 분리하는 작업이다.
> ###### 다음 문자열은 연산하는 동작이다.
```java 
String lex = "{{ 1 1 + }};" // 후위 표기법 어휘
```
> ###### 연산식의 언어는 시작 식별자(```{{```)와 종료 식별자(```}}```)안에 존재하며, 어휘는 각각의 공백으로 구분돼 있다.
> ###### 토큰을 분리된 문자열은 1차원 배열로 반환 한다.
> ###### 토큰은 어휘에서 키워드, 식별자, 상수, 리터럴, 연산자 등을 구별하며 해석 처리를 위한 단위를 분리한다.

#### 2. 구문 분석
> ###### 토큰으로 분리된 어휘를 해헉하기 위해 자료 구조를 생성한다.
> ###### 토큰으로 분리된 1차원 배열은 구문 분석 단계를통헤 ```구문 트리```를 작성한다
> ###### BNF 로 표현된 문법을 분석하면 ```part-whole``` 형태의 트리 구조가 된다.
> ###### ```Part-Whole``` 트리 구조는 복합체 패턴(Composite Pattern)으로 구현 할 수 있다.

#### 3. Context 클래스
```java
public class Context {
    private StringTokenizer token;
    private String currentToken;
    
    public Context(String text) {
        token = new StringTokenizer(text, " ");
        next();
    }
    public Boolean isStart(){
        if (currentToken.equals("{{")){
            return true;
        }
        return  false;
    }

    public String next(){
        if(token.hasMoreTokens()) {
            currentToken = token.nextToken();
        }
        else currentToken = null;
        return currentToken;
    }
}
```

---
## 중간 코드
#### 패스
> ###### 언어의 해석과 동작은 한 번에 처리할 수 없다.
> ###### 패스(pass)는 해석솨 수행을 처리하기 위한  중간 단계 과정이다.
> ###### 몇번의 패스가 이루어 지는가에 따라 해석기의 성능이 좌우된다.
> ###### 일반 적으로 두 번 이상의 패스를 가지며, 어셈블리어는 2-Pass 해석이 이다.
> ###### 첫 번째 패스에서 토큰을 분리해 식별자를 분리하고, 두 번째는 연산 등을 처리하귀 위한 표현을 변경한다.

---
## 해석
#### 1. 명령
> ###### 언어는 복잡한 동작을 간단한 표현으로 실행하는 것이며, 동작을 실행할며녀 표현 해석이 필요하다.
> ###### 해석자는 언어 문장의 규칙을 분석하고 이를 표햔하는 동작을 처리한다.

#### 2. 추상 구문 트리(Abstract Syntax Tree, AST)
> ###### 1차원적인 자료 구조로는 추상화된 언어를 처리할 수 없으며 하나의 언어를 처리하기 위해 광범위한 자료 구조가 필요하다.
> ###### 실제구현은 추상화를 통해 하위 클래스에 위임하고 트리 형태의 어휘 구조를 복합체 패턴으로 적용한다.
> ###### 추상 구문 표현은 모든 노드에 적용되는 공통된 인터페이스를 갖고 있다.
> ###### ```AST```는 컴파일러 등을 제작할 때 사용하는 자료 구조로, 문장 정보를 나눈고 평가 순서를 재정의 한다.
> ###### 주요 역할은 컴파일러가 작성된 언어를 해석할 때 단계별로 중간 표현을 처리하는 것이다.

#### 3. 파서
> ###### 토큰과 AST는 어휘 구조를 분석한다.
> ###### 어휘의 규칙이 복잡할 경우 이를 처리하는 로직이 방대해질 수 있다. 이때 별도의 파서를 사용하면 보다 효율적으로 처리 가능하다.
> ###### 파서는 문장의 구조를 트리 구조로 표현한 것이다. 파서는 언어의 문법을 구체적으로 반영한다는 점에서 추상 구분 트리와 구별된다.
> ###### 파서는 크게 두 종류의 트리로 구분된다. ```구조 트리``` ```의존성 트리```
 
---
## 클래스 표현
#### 1. 노드
```java
public interface Expression {
    public Boolean interpret();
}
```
> ###### ```interpret()```메서드는 구문을 해석한다.

#### 2. 비종료 기호
> ###### 식별자와 같이 종료 되지 않은 기호이다. (리프노드가 아님, 재귀적 구조 ?)
```java
//Non-Terminal Expression
public class Add implements Expression{
    private Expression left;
    private Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return Integer.toString(Integer.parseInt(left.interpret()) + Integer.parseInt(right.interpret())); 
    }
}
```

#### 3. 종료기호
> ###### BNF 표기가 더 이상 문자 또는 숫자로 치환 되지 않는 최종적인 기호를 말한다.
```java
public class Terminal implements Expression{
    private String n;

    public Terminal(String n) {
        this.n = n;
    }

    @Override
    public String interpret() {
        return n;
    }
}
```
#### 4. 클라이언트

```java
public class Main {

    public static void main(String[] args) {
        String lex = "{{ 1 1 + }}";
        Context context = new Context(lex);
        Stack<Expression> stack = new Stack<>();

        if(context.isStart()){
            System.out.println("연산 시작");
            while(true){
                String token = context.next();
                if(token.equals("}}")){
                    System.out.println("연산 종료");
                    break;
                }
                else if(token.matches("-?\\d+")){
                    stack.push(new Terminal(token));
                }
                else if(token.equals("+")){
                    Expression left = stack.pop();
                    Expression right = stack.pop();

                    Expression add = new Add(left, right);
                    String ret = add.interpret();
                    System.out.println(ret);
                    stack.push(new Terminal(ret));
                }
                else{
                    System.out.println("판별할 수 없는 해석입니다.");
                }
            }
        }
    }
}
```
**실행 결과**
```aidl
연산 시작
2
연산 종료
```
---
## 관련 패턴
#### 복합체 패턴, 플라이 웨이트 패턴, 방문자 패턴