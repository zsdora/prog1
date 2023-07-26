package units;

public class Farmer extends Unit {
    
    public Farmer(){
        super("Farmer", 2, 1, 1, 3, 4, 8, "no");
    }
    
    @Override
    public String toString(){
        return "F";
    }
    
}
