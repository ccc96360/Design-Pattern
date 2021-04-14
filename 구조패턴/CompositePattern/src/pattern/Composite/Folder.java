package pattern.Composite;

import java.util.ArrayList;
import java.util.List;

public class Folder extends Component{
    private List<Component> children = new ArrayList<Component>();

    public Folder(String name) {
        this.setName(name);
    }

    public void addNode(Component folder){
        this.children.add(folder);
    }
    public void remove(Component folder){
        this.children.remove(folder);
    }

    @Override
    void printSub(String path) {
        String mPath =  path + "\\" + this.getName();
        System.out.println(mPath + "폴더 탐색 시작");
        for(Component child: children){
            child.printSub(mPath);
        }
        System.out.println(mPath + "탐색 종료");
    }
    void printSub() {
        String mPath = "\\" + this.getName();
        System.out.println(mPath + "폴더 탐색 시작");
        for(Component child: children){
            child.printSub(mPath);
        }
        System.out.println(mPath + "탐색 종료");
    }
}
