package pattern.Composite;

public class File extends Component {
    private int data;

    public File(String name) {
        this.setName(name);
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    void printSub(String path) {
        System.out.println(path+ "\\" +this.getName());
    }
}
