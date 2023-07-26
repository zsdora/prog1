
import units.*;

public class Cell {
    
    private Unit unit;
    private String player;
    Cell(){
        unit = null;
        player = "";
    }
    
    Cell(Unit unit, String player){
        this.unit = unit;
        this. player = player;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public String toString() {
        if(unit == null){
            return "  . ";
        }
        else{
            return unit.toString()+"("+player+")";
        }
    }
    
}