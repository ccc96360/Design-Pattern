package pattern.prototype;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Hello ko = new Hello("안녕하세요~!");
        System.out.println(ko.getMessage());
        Hello ko2 = (Hello) ko.clone();
        ko2.setMessage("안녕~!");
        System.out.println(ko.getMessage());
        System.out.println(ko2.getMessage());
    }
}
