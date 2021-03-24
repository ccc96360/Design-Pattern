package pattern.Builder;

public class Ram {
    private int size;

    Ram(){
        this.size = 0;
    }
    Ram(int size){
        this.size = size;
    }
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }

    @Override
    public String toString() {
        return this.size + "GB.";
    }
}
