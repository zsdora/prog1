
import spells.Spell;
import units.Unit;
import java.util.ArrayList;

/**
 * Az osztaly ami a host reprezentalja
 * @author 
 */
public class Hero {

    private int attack;
    private int defense;
    private int magic;
    private int knowledge;
    private int morale;
    private int luck;
    
    private Army army;
    private ArrayList<Spell> spells;

    /**
     * default konstruktor metodus a hosnek, hogy minden tulajdonsagot 1-re allitson be kezdo erteknek
     */
    public Hero() {
        this.attack = 1;
        this.defense = 1;
        this.magic = 1;
        this.knowledge = 1;
        this.morale = 1;
        this.luck = 1;
        spells = new ArrayList<>();
        army = new Army();
    }
    
    /**
     * metodus ami megszamlalja, hogy hany egyseg van ugyanazzal a parameterrel
     * @param unit hogy egyezzen a neve a hadsereg egysegeivel
     * @return egyezo egysegek szamava
     */
    public int getNumberOfUnits(Unit unit){
        int count = 0;
        ArrayList<Unit> units = army.getUnits();
        for (int a = 0; a < units.size(); a++) {
            if(units.get(a).getName().equals(unit.getName())){
                count++;
            }
        }
        return count;
    }
    
    /**
     *  metodus ami lekeri es visszaadja a neveit a kulonbozo egysegeknek
     * @return kulonbozo egysegek neveinek listajaval
     */
    public ArrayList<String> getUniqueUnits(){
        ArrayList<String> uniques = new ArrayList<>();
        ArrayList<Unit> units = army.getUnits();
        for (int a = 0; a < units.size(); a++) {
            if(!uniques.contains(units.get(a).getName())){
                uniques.add(units.get(a).getName());
            }
        }
        return uniques;
    }
    
    /**
     * metodus ami uj egyseget ad a hadsereghez
     * @param u hozza lesz adva az egysegek listajahoz
     */
    public void addUnit(Unit u){
        army.addUnit(u);
    }
    
    /**
     * metodus ami uj varazslatot ad a hadsereghez
     * @param s hozza lesz adva az varazslatok listajahoz
     */
    public void addSpell(Spell s){
        spells.add(s);
    }
    
    /**
     * metodus ami hadsereg objektummal ter vissza
     * @return Army instance
     */
    public Army getArmy(){
        return army;
    }
    
    /**
     * metodus ami noveli a tamadas tulajdonsagot amt ertekkel
     * @param amt mennyiseget hasznalunk az ertek novelesehez
     */
    public void increaseAttack(int amt) {
        attack += amt;
    }
    
    /**
     * metodus ami noveli a vedekezes tulajdonsagot amt ertekkel
     * @param amt mennyiseget hasznalunk az ertek novelesehez
     */
    public void increaseDefense(int amt) {
        defense += amt;
    }
    
    /**
     * metodus ami noveli a varazsero tulajdonsagot amt ertekkel
     * @param amt mennyiseget hasznalunk az ertek novelesehez
     */
    public void increaseMagic(int amt) {
        magic += amt;
    }
    
    /**
     * metodus ami noveli a tudas tulajdonsagot amt ertekkel
     * @param amt mennyiseget hasznalunk az ertek novelesehez
     */
    public void increaseKnowledge(int amt) {
        knowledge += amt;
    }
    
    /**
     * metodus ami noveli a moral tulajdonsagot amt ertekkel
     * @param amt mennyiseget hasznalunk az ertek novelesehez
     */
    public void increaseMorale(int amt) {
        morale += amt;
    }
    
    /**
     * metodus ami noveli a szerencse tulajdonsagot amt ertekkel
     * @param amt mennyiseget hasznalunk az ertek novelesehez
     */
    public void increaseLuck(int amt) {
        luck += amt;
    }
    
    /**
     * metodus ami visszaadja a tamadas tulajdonsagot
     * @return tamadas
     */
    public int getAttack() {
        return attack;
    }

    /**
     * metodus ami visszaadja a vedekezes tulajdonsagot
     * @return vedekezes
     */
    public int getDefense() {
        return defense;
    }

    /**
     * metodus ami visszaadja a varazsero tulajdonsagot
     * @return varazsero
     */
    public int getMagic() {
        return magic;
    }

    /**
     * metodus ami visszaadja a tudas tulajdonsagot
     * @return tudas
     */
    public int getKnowledge() {
        return knowledge;
    }

    /**
     * metodus ami visszaadja a moral tulajdonsagot
     * @return moral
     */
    public int getMorale() {
        return morale;
    }

    /**
     * metodus ami visszaadja a szerencse tulajdonsagot
     * @return szerencse
     */
    public int getLuck() {
        return luck;
    }

    /**
     * metodus ami visszater a varazslatok listajaval
     * @return varazslatok listaja
     */
    public ArrayList<Spell> getSpells() {
        return spells;
    }

    /**
     * metodus ami kap egy tamadast es elosztja az egysegek listaja kozott egy formula hasznalataval
     * @param damage tamadas amit eloszt az egysegek kozott
     */
    public void getDamage(int damage) {
        ArrayList<Unit> units = army.getUnits();
        for (int a = 0; a < units.size(); a++) {
            if(damage == 0){
                return;
            }
            else{
                if(damage >= units.get(a).getLife()){
                    damage = damage - units.get(a).getLife();
                    units.remove(a);
                    a--;
                }
                else if(damage < units.get(a).getLife()){
                    units.get(a).decreaseLife(damage);
                    damage = 0;
                }
            }
        }
    }
    
    /**
     * metodus ami kap egy tamadast es elosztja specifikus egysegek kozott formula hasznalataval
     * @param damage tamadas amit eloszt az egysegek kozott
     * @param unitName az egyseg tipus ami elszenvedi a tamadast
     */
    public void getDamage(int damage, String unitName) {
        ArrayList<Unit> units = army.getUnits();
        for (int a = 0; a < units.size(); a++) {
            if(units.get(a).getName().equals(unitName)){
                if(damage == 0){
                    return;
                }
                else{
                    if(damage >= units.get(a).getLife()){
                        damage = damage - units.get(a).getLife();
                        units.remove(a);
                        a--;
                    }
                    else if(damage < units.get(a).getLife()){
                        units.get(a).decreaseLife(damage);
                        damage = 0;
                    }
                }
            }
        }
    }
    
    /**
     * metodus ami visszater a hadsereg max eleterejevel
     * @return az osszes egyseg eleterejenek osszege
     */
    public int getTotalLife(){
        int total = 0;
        ArrayList<Unit> units = army.getUnits();
        for (int a = 0; a < units.size(); a++) {
            total += units.get(a).getLife();
        }
        return total;
    }

    /**
     *  metodus ami ellenorzi, hogy a varazslat az alabbi nevvel szerepel e a varazslat listaban vagy sem
     * @param name nev amit keres
     * @return true/false eredmeny alapjan
     */
    public boolean contains(String name) {
        for (Spell spell : spells) {
            if(spell.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "";
        ArrayList<Unit> units = army.getUnits();
        for (int a = 0; a < units.size(); a++) {
            if(!str.contains(units.get(a).getName())){
                str += units.get(a).getName()+", ";
            }
        }
        String str2 = "";
        for (int a = 0; a < spells.size(); a++) {
            str2 += spells.get(a).getName()+", ";
        }
        if(str.endsWith(", ")){
            str = str.substring(0, str.length()-2);
        }
        if(str2.endsWith(", ")){
            str2 = str2.substring(0, str2.length()-2);
        }
        return "Tamadas: " + attack + "\nVedekezes: " + defense + "\nVarazsero: " + magic
                + "\nTudas: " + knowledge + "\nMoral: " + morale + "\nSzerencse: " + luck
                + "\nEgysegek: " + str + "\nVarazslatok: " + str2+"\n";
    }
    
}
