package pattern.Builder;

public class Storage {
    private int size;

    Storage(){
        this.size = 0;
    }
    Storage(int size){
        this.size = size;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(int size){
        return this.size;
    }
    @Override
    public String toString() {
        return this.size + "GB";
    }
}
