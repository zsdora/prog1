package units;


public class Archer extends Unit {

    public Archer() {
        super("Archer", 6, 2, 4, 7, 4, 9, "shot");
    }
    
    @Override
    public String toString(){
        return "A";
    }
}
