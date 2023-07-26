package units;


public class Unit {
    
    private String name;
    private int price;
    private int damageMin;
    private int damageMax;
    private int life;
    private int speed;
    private int initiative;
    private String specialAbility;

    public Unit(String name, int price, int damageMin, int damageMax, int life, int speed, int initiative, String specialAbility) {
        this.name = name;
        this.price = price;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.life = life;
        this.speed = speed;
        this.initiative = initiative;
        this.specialAbility = specialAbility;
    }
    
    public int getDamage(){
        return (int)((Math.random() * (damageMax - damageMin)) + damageMin);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getLife() {
        return life;
    }

    public int getSpeed() {
        return speed;
    }

    public int getInitiative() {
        return initiative;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }
    
    @Override
    public String toString(){
        return "U";
    }

    public void decreaseLife(int damage) {
        life = life - damage;
    }
    
}