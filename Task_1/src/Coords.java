package Task_1.src;

public class Coords {
    int x;
    int y;

    public Coords(int x, int y){
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "x: " + this.x + " y: " + this.y;
    }
}