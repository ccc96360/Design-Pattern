package pattern.Composite;

public class Main {

    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder home = new Folder("home");
        Folder minjae = new Folder("minjae");
        Folder ccc96360 = new Folder("ccc96360");
        Folder users = new Folder("users");
        Folder temp = new Folder("temp");

        File img1 = new File("img1");
        File img2 = new File("img2");
        File img3 = new File("img3");
        File img4 = new File("img4");

        root.addNode(home);
        root.addNode(users);
        users.addNode(minjae);
        minjae.addNode(img1);
        minjae.addNode(img2);
        users.addNode(ccc96360);
        ccc96360.addNode(img3);
        ccc96360.addNode(img4);
        root.addNode(temp);

        root.printSub();

        System.out.println("ccc96360 삭제");
        users.remove(ccc96360);
        root.printSub();
    }

}
