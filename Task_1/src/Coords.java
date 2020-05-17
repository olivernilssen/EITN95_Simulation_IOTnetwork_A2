package Task_1.src;

import java.util.Comparator;

public class Coords implements Comparator<Coords> {
    Integer id;
    int x;
    int y;

    public Coords(int id, int x, int y){
        super();
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int compare(Coords a, Coords b) {
        return (a.x < b.x) ? -1 : (a.x > b.x) ? 1 : 0;
    }

    @Override
    public String toString(){
        return "x: " + this.x + " y: " + this.y;
    }
}