
import units.Unit;
import java.util.ArrayList;

public class Army {

    private ArrayList<Unit> units;

    Army() {
        units = new ArrayList<>();
    }
    
    public void addUnit(Unit u) {
        units.add(u);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

}
