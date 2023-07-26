package spells;


public class Spell {
    
    private String name;
    private int price;
    private int manna;

    public Spell(String name, int price, int manna) {
        this.name = name;
        this.price = price;
        this.manna = manna;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getManna() {
        return manna;
    }

    public void setManna(int manna) {
        this.manna = manna;
    }
    
    
    
}
