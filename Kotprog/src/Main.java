
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import units.*;
import spells.*;

/**
 * Main osztaly ami kezeli es futtatja az egesz programot
 * @author 
 */
public class Main {

    //ellenseg aranya = 1000
    private static int playerGold;
    private static int compGold = 1000;
    private static Hero player;
    private static Hero computer;

    private static Cell[][] field;

    /**
     * main metodus amivel el tudod inditani es futtatni a programot
     * ez a metodus megmutatja a menuket es felhasznaloi inputokat ker, minden szukseges dolgot beallit a csatahoz
     * ez a metodus segit jatekosoknak egysegeket, varazslatokat, tulajdonsagpontokat vasarolni
     * segit a jatekosoknak egysegeket elhelyezni a palyan
     * segit a jatekosoknak elkezdeni a csatat
     * @param args opcionalis command line argumentumok
     */
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        System.out.println("Valassz egy nehezsegi szintet");
        System.out.println("1. Konnyu");
        System.out.println("2. Kozepes");
        System.out.println("3. Nehez");
        System.out.print("Valasz: ");
        int level = in.nextInt();
        if (level == 1) {
            playerGold = 1300;
        } else if (level == 2) {
            playerGold = 1000;
        } else if (level == 3) {
            playerGold = 700;
        }

        // hadseregek keszitese csatahoz
        player = new Hero();
        computer = new Hero();
        
        //egysegek es varazslatok vasarlasa a jatekosnak
        while(playerGold > 0){
            System.out.println("Ennyi aranyad van: "+playerGold);
            System.out.println("Mit szeretnel vasarolni?");
            System.out.println("1. Egyseg");
            System.out.println("2. Varazslat");
            System.out.println("3. Hos tulajdonsagpontok vasarlasa");
            System.out.println("4. Befejeztem a vasarlast. Kezdodjon a csata");
            System.out.println("5. Hos tulajdonsagainak megtekintese");
            System.out.print("Válasz: ");
            int choice = in.nextInt();
            switch(choice){
                case 1:
                    buyUnits();
                    break;
                case 2:
                    buySpells();
                    break;
                case 3:
                    buyHero();
                    break;  
                case 4:
                    if(!player.getArmy().getUnits().isEmpty()){
                        playerGold = 0;
                    }
                    else{
                        System.out.println("Kerlek vasarolj elobb par egyseget!");
                    }
                    break;
                case 5:
                    System.out.println("Jatekos serege\n"+player.toString());
                    break;
                default:
                    System.out.println("Hibas Input!");
                    break;
                    
            }
            System.out.println("");
        }
        
        //hos tulajdonsagpontok vasarlasa a gepnek
        buyHeroForComputer();
        
        // egysegek vasarlasa a gepnek random valasztva, random mennyisegben
        while(compGold > 0) {
            int choice = (int)((Math.random() * (5 - 1)) + 1);
            int quantity = (int)((Math.random() * (10 - 1)) + 1);;
            if(choice == 1){
                Unit u = new Archer();
                int goldRequired = u.getPrice() * quantity;
                if(goldRequired <= compGold){
                    for (int a = 0; a < quantity; a++) {
                        computer.addUnit(new Archer());
                    }
                    compGold -= goldRequired;
                }
            }
            else if(choice == 2){
                Unit u = new Farmer();
                int goldRequired = u.getPrice() * quantity;
                if(goldRequired <= compGold){
                    for (int a = 0; a < quantity; a++) {
                        computer.addUnit(new Farmer());
                    }
                }
                compGold -= goldRequired;
            }
            else if(choice == 3){
                Unit u = new Gri();
                int goldRequired = u.getPrice() * quantity;
                if(goldRequired <= compGold){
                    for (int a = 0; a < quantity; a++) {
                        computer.addUnit(new Gri());
                    }
                }
                compGold -= goldRequired;
            }
            else if(choice == 4){
                Unit u = new Hogs();
                int goldRequired = u.getPrice() * quantity;
                if(goldRequired <= compGold){
                    for (int a = 0; a < quantity; a++) {
                        computer.addUnit(new Hogs());
                    }
                }
                compGold -= goldRequired;
            }
            else if(choice == 5){
                Unit u = new Pekka();
                int goldRequired = u.getPrice() * quantity;
                if(goldRequired <= compGold){
                    for (int a = 0; a < quantity; a++) {
                        computer.addUnit(new Pekka());
                    }
                }
                compGold -= goldRequired;
            }
        }
        
        System.out.println("\n\n");
        System.out.println("Jatekos hadserege\n"+player.toString());
        System.out.println("Gep hadserege\n"+computer.toString());
        

        //palya elokeszitese
        field = new Cell[10][12];
        for (int a = 0; a < field.length; a++) {
            for (int b = 0; b < field[a].length; b++) {
                field[a][b] = new Cell();
            }
        }

        // egysegek hozzaadasa jatekosnak
        ArrayList<Unit> playersUnits = player.getArmy().getUnits();
        Set<String> placed = new HashSet<>();
        for (Unit pu : playersUnits) {
            if (!placed.contains(pu.toString())) {
                while(true){
                    System.out.println("Egyseg elhelyezese "+pu.getName()+": ");
                    System.out.print("Enter x: ");
                    int x = in.nextInt();
                    System.out.print("Enter y: ");
                    int y = in.nextInt();
                    if(y == 0 || y == 1){
                        if(x >= 0 && x < 10){
                            if(field[x][y] != null && field[x][y].getUnit() == null){
                                field[x][y] = new Cell(pu, "P");
                                System.out.println(pu.getName()+" Elhelyezve!");
                                System.out.println("");
                                placed.add(pu.toString());
                                break;
                            }
                            else{
                                System.out.println("Ez a hely mar foglalt!");
                            }
                        }
                        else{
                            System.out.println("Nem helyezheted a palyan kivulre!");
                        }
                    }
                    else{
                        System.out.println("Csak az elso ket oszlopot hasznalhatod a bal oldalt");
                    }
                }
            }
        }
        System.out.println("");
        
        //egysegek hozzaadasa gepnek
        ArrayList<Unit> compUnits = computer.getArmy().getUnits();
        placed = new HashSet<>();
        for (Unit cu : compUnits) {
            if (!placed.contains(cu.toString())) {
                placed.add(cu.toString());
                addToField(cu);
            }
        }
        
        System.out.println("Nyomd meg az Entert a csata elkezdesehez!");
        in.nextLine();
        in.nextLine();
        System.out.println("");
        startFight();
    }
    
    /**
     * metodus a harc elkezdesehez
     * ez a metodus elkezdi a csatat egy while loopban ahol mindket jatekos tud tamadni
     * es egyeb muveletet vegezni
     * ez a metodus segit a jatekosoknak egysegek mozgatasaban, tamadasban, hos es varazslatok hasznalatahoz
     */
    public static void startFight(){
        Scanner in = new Scanner(System.in);
        //csata elkezdese
        int round = 1;
        Unit activeUnit = null;
        while(true){
            printField();
            System.out.println("Jatekos eletereje: "+player.getTotalLife());
            System.out.println("Gep eletereje: "+computer.getTotalLife());
            System.out.println("------------------");
            System.out.println("ROUND "+round);
            System.out.println("------------------");
            
            //jatekos kore
            activeUnit = getHigherInitiativeUnit(player);
            System.out.println("Magasabb kezdemenyezesu egyseg a(z) "+activeUnit.getName());
            System.out.println("Egyseg: "+activeUnit.getName());
            System.out.println("1. Mozgas");
            System.out.println("2. Varakozas");
            System.out.println("3. Tamadas");
            System.out.println("4. Hos hasznalata");
            System.out.print("Valasz: ");
            int choice = in.nextInt();
            if(choice == 1){
                System.out.println("1. Fel");
                System.out.println("2. Le");
                System.out.println("3. Balra");
                System.out.println("4. Jobbra");
                System.out.print("Valasz: ");
                int direction = in.nextInt();
                move(activeUnit, "P", direction);
                //random kivalasztja, hogy a gep egysege mozog vagy marad
                boolean compMove = new Random().nextBoolean();
                if(compMove){
                    direction = (int)((Math.random() * (4 - 1)) + 1);
                    //4 irany kozul a gep 1 valamelyik fele fog mozogni(random valasztott)
                    move(activeUnit, "C", direction);
                }
            }
            else if(choice == 2){
                //varakozzunk
            }
            else if(choice == 3){
                //jatekos megtamadja a gepet
                int count = player.getNumberOfUnits(activeUnit);
                int basicDamage = activeUnit.getDamage()*count;
                int attackProperty = player.getAttack();
                int defenseProperty = computer.getDefense();
                double attackP = (((attackProperty*10.0)/100.0)+1.0)*basicDamage;
                double defenseC = (((defenseProperty*10.0)/100.0)*0.50) * attackP;
                int damage = (int)(attackP - defenseC);
                computer.getDamage(damage);
                
                //gep kore
                int action = (int)((Math.random() * (10 - 1)) + 1);
                if(action < 4){ //for 1-4 gep mozogni fog
                    int direction = (int)((Math.random() * (4 - 1)) + 1);
                    //4 irany kozul a gep 1 valamelyik fele fog mozogni(random valasztott)
                    move(activeUnit, "C", direction);
                }
                else { // kulonben gep valaszt hogy a jatekost tamadja
                    //gep visszatamadja a jatekost
                    count = computer.getNumberOfUnits(activeUnit);
                    basicDamage = activeUnit.getDamage()*count;
                    attackProperty = computer.getAttack();
                    defenseProperty = player.getDefense();
                    double attackC = (((attackProperty*10.0)/100.0)+1.0)*basicDamage;
                    double defenseP = (((defenseProperty*10.0)/100.0)*0.50) * attackC;
                    damage = (int)(attackC - defenseP);
                    player.getDamage(damage);
                }
            }
            else if (choice == 4){
                System.out.println("1. Tamadas");
                System.out.println("2. Varazslat");
                System.out.print("Valasz: ");
                int select = in.nextInt();
                ArrayList<String> uniqueUnits = computer.getUniqueUnits();
                for (int a = 0; a < uniqueUnits.size(); a++) {
                    System.out.println((a+1)+". "+uniqueUnits.get(a));
                }
                System.out.print("Valassz, hogy melyik ellenseges egyseget tamadjuk meg: ");
                int enemyIdx = in.nextInt();
                String enemy = uniqueUnits.get(enemyIdx);
                if(select == 1){
                    int damage = player.getAttack()*10;
                    computer.getDamage(damage, enemy);
                }
                else if(select == 2){
                    ArrayList<Spell> spells = player.getSpells();
                    for (int i = 0; i < spells.size(); i++) {
                        System.out.println((i+1)+". "+spells.get(i).getName());
                    }
                    System.out.print("Valassz, hogy milyen varazslatot szeretnel hasznalni: ");
                    int idx = in.nextInt();
                    if(spells.get(idx-1) != null){
                        Spell s = spells.remove(idx-1);
                        if(player.getKnowledge() >= s.getManna()){
                            int manna = s.getManna()*player.getMagic();
                            computer.getDamage(manna);
                        }
                        else{
                            System.out.println("Nem tudod hasznalni a varazslatot. Nincs eleg Mannad.");
                        }
                    }
                    else{
                        System.out.println("Rossz varazslat valasztas!");
                    }
                }
            }
            else{
                System.out.println("Hibas Input!");
            }
            
            round++;
            if(computer.getTotalLife() <= 0){
                System.out.println("Gratulalok! Nyertel a gep ellen!");
                break;
            }
            else if(player.getTotalLife() <= 0){
                System.out.println("Gep gyozedelmes!");
                break;
            }
            else if(player.getTotalLife() == computer.getTotalLife()) {
                System.out.println("Oh ne. Dontetlen lett!");
                break;
            }
        }
    }

    /**
     * Metodus egysegek vasarlasahoz
     * ez a metodus segit a jatekosnak kulonbozo egysegek vasarlasaban valtozo mennyisegben
     * jatekos lathat egy listat egysegekkel es araikkal, amiket meg tud vasarolni, mig van eleg penze
     */
    public static void buyUnits() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Farmer [$2]");
        System.out.println("2. Archer [$6]");
        System.out.println("3. Gri [$15]");
        System.out.println("4. Hogs [$10]");
        System.out.println("5. Pekka [$5]");
        System.out.println("0. Vissza a menube");
        System.out.print("Valasz: ");
        int choice = in.nextInt();
        System.out.print("Irj be egy mennyiseget: ");
        int quantity = in.nextInt();
        if(choice == 0){
            return;
        }
        else if (choice == 1) {
            Farmer u = new Farmer();
            int goldRequired = u.getPrice() * quantity;
            if (playerGold >= goldRequired) {
                for (int a = 0; a < quantity; a++) {
                    player.addUnit(new Farmer());
                }
                playerGold = playerGold - goldRequired;
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } else if (choice == 2) {
            Archer u = new Archer();
            int goldRequired = u.getPrice() * quantity;
            if (playerGold >= goldRequired) {
                for (int a = 0; a < quantity; a++) {
                    player.addUnit(new Archer());
                }
                playerGold = playerGold - goldRequired;
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } else if (choice == 3) {
            Gri u = new Gri();
            int goldRequired = u.getPrice() * quantity;
            if (playerGold >= goldRequired) {
                for (int a = 0; a < quantity; a++) {
                    player.addUnit(new Gri());
                }
                playerGold = playerGold - goldRequired;
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } else if (choice == 4) {
            Hogs u = new Hogs();
            int goldRequired = u.getPrice() * quantity;
            if (playerGold >= goldRequired) {
                for (int a = 0; a < quantity; a++) {
                    player.addUnit(new Hogs());
                }
                playerGold = playerGold - goldRequired;
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } else if (choice == 5) {
            Pekka u = new Pekka();
            int goldRequired = u.getPrice() * quantity;
            if (playerGold >= goldRequired) {
                for (int a = 0; a < quantity; a++) {
                    player.addUnit(new Pekka());
                }
                playerGold = playerGold - goldRequired;
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        }
        else{
            System.out.println("Hibas Input!");
        }
    }
    
    /**
     * Metodus varazslatok vasarlasahoz
     * ez a metodus segit a jatekosnak kulonbozo varazslatok vasarlasaban
     * jatekos lathat egy listat varazslatokkal es araikkal, amiket meg tud vasarolni, mig van eleg penze
     */
    public static void buySpells() {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Villamcsapas [$60 - 5]");
        System.out.println("2. Tuzlabda [$120 - 9]");
        System.out.println("3. Feltamasztas [$120 - 6]");
        System.out.println("4. Sietseg [$100 - 6]");
        System.out.println("5. Foldrenges [$50 - 4]");
        System.out.println("0. Vissza a menube");
        System.out.print("Valasz: ");
        int choice = in.nextInt();
        if (choice == 0) {
            return;
        }
        else if (choice == 1) {
            Spell s = new LightningSpell();
            int goldRequired = s.getPrice();
            if (playerGold >= goldRequired){ 
                if(!player.contains("Villamcsapas")) {
                    player.addSpell(s);
                    playerGold = playerGold - goldRequired;
                }
                else{
                    System.out.println("Mar megvan neked ez a varazslat!");
                }
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } 
        else if (choice == 2) {
            Spell s = new Fireball();
            int goldRequired = s.getPrice();
            if (playerGold >= goldRequired) {
                if(!player.contains("Tuzlabda")) {
                    player.addSpell(s);
                    playerGold = playerGold - goldRequired;
                }
                else{
                    System.out.println("Mar megvan neked ez a varazslat!");
                }
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } 
        else if (choice == 3) {
            Spell s = new SupportSpell();
            int goldRequired = s.getPrice();
            if (playerGold >= goldRequired) {
                if(!player.contains("Feltamasztas")) {
                    player.addSpell(s);
                    playerGold = playerGold - goldRequired;
                }
                else{
                    System.out.println("Mar megvan neked ez a varazslat!");
                }
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } 
        else if (choice == 4) {
            Spell s = new Haste();
            int goldRequired = s.getPrice();
            if (playerGold >= goldRequired) {
                if(!player.contains("Sietseg")) {
                    player.addSpell(s);
                    playerGold = playerGold - goldRequired;
                }
                else{
                    System.out.println("Mar megvan neked ez a varazslat!");
                }
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } 
        else if (choice == 5) {
            Spell s = new Earthquake();
            int goldRequired = s.getPrice();
            if (playerGold >= goldRequired) {
                if(!player.contains("Foldrenges")) {
                    player.addSpell(s);
                    playerGold = playerGold - goldRequired;
                }
                else{
                    System.out.println("Mar megvan neked ez a varazslat!");
                }
            } else {
                System.out.println("Nincs eleg aranyad!");
            }
        } 
        else{
            System.out.println("Hibas Input!");
        }
    }

    /**
     * Metodus ami printel egy 2 dimenzios field/mezo tombot
     * ebben a tombben van minden egyseg elhelyezve
     * ez a metodus printeli az osszes egyseget es az ures helyeket is
     */
    public static void printField() {
        for (int a = 0; a < field.length; a++) {
            System.out.println("-------------------------------------------------------------------------------------");
            for (int b = 0; b < field[a].length; b++) {
                System.out.print("| " + field[a][b].toString() + " ");
            }
            System.out.println("|");
            System.out.println("");
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    /**
     * Ez a metodus adja hozza az egyseget a tombhoz miutan leelenorizte a random kivalasztott cella ervenyesseget
     * ez a metodus csak a gepnek van.
     * @param u egyseg amit elhelyezzunk a mezon
     */
    public static void addToField(Unit u) {
        while(true){
            int x = (int)((Math.random() * (9 - 0)) + 0);
            int y = (int)((Math.random() * (11 - 10)) + 10);
            if(field[x][y] != null && field[x][y].getUnit() == null){
                field[x][y] = new Cell(u, "C");
                return;
            }
        }
    }

    /**
     * metodus ami visszater azzal az egyseggel aminek a legnagyob kezdemenyezese van az osszes egyseg kozul
     * @param player hogy megszabjuk, hogy jatekosnak vagy gepnek keresunk egyseget
     * @return  egyseg magasabb kezdemenyezessel
     */
    public static Unit getHigherInitiativeUnit(Hero player) {
        ArrayList<Unit> units = player.getArmy().getUnits();
        int max = units.get(0).getInitiative();
        int maxIndex = 0;
        for (int a = 0; a < units.size(); a++) {
            if(max < units.get(a).getInitiative()){
                max = units.get(a).getInitiative();
                maxIndex = a;
            }
        }
        return units.get(maxIndex);
    }

    /**
     * Metodus egyseg mozgatasahoz mindket jatekos szamara, jatekos altal megszabott itanyban
     * @param unit egyseg amit mozgatunk a palyan
     * @param player jatekos vagy gep (P vagy C tipusu String)
     * @param direction irany megadasa(fel, le, jobbra, balra)
     */
    public static void move(Unit unit, String player, int direction) {
        int speed = unit.getSpeed();
        int next = speed+1;
        while(true){
            if(next <= speed){
                break;
            }
            else{
                if(player.equals("P")){
                    Scanner in = new Scanner(System.in);
                    System.out.println("Ennyit tudsz mozogni "+speed);
                    System.out.print("Ird be mennyit szeretnel mozogni: ");
                    next = in.nextInt();
                }
                else{
                    next = (int)((Math.random() * (speed - 1)) + 1);
                }
            }
        }
        int[] loc = getLocation(unit, player);
        int x = loc[0];
        int y = loc[1];

        if(direction == 1){
            //ha felfelet valaszt
            if((x-next) >= 0){
                if(field[x-next][y].getUnit() == null){
                    field[x][y] = new Cell(); //elmozditja az egyseget a regi helyerol
                    field[x-next][y] = new Cell(unit, player);   //uj helyre
                }
                else{
                    System.out.println("Nem tudsz ide mozogni");
                }
            }
        }
        else if(direction == 2){
            //ha lefelet valaszt
            if((x+next) < field.length){
                if(field[x+next][y].getUnit() == null){
                    field[x][y] = new Cell(); //elmozditja az egyseget a regi helyerol
                    field[x+next][y] = new Cell(unit, player);   //uj helyre
                }
                else{
                    System.out.println("Nem tudsz ide mozogni");
                }
            }
        }
        else if(direction == 3){
            //ha balrat valaszt
            if((y-next) >= 0){
                if(field[x][y-next].getUnit() == null){
                    field[x][y] = new Cell(); //elmozditja az egyseget a regi helyerol
                    field[x][y-next] = new Cell(unit, player);   //uj helyre
                }
                else{
                    System.out.println("Nem tudsz ide mozogni");
                }
            }
        }
        else if(direction == 4){
            //ha jobbrat valaszt
            if((y+next) < field[0].length){
                if(field[x][y+next].getUnit() == null){
                    field[x][y] = new Cell(); //elmozditja az egyseget a regi helyerol
                    field[x][y+next] = new Cell(unit, player);   //uj helyre
                }
                else{
                    System.out.println("Nem tudsz ide mozogni");
                }
            }
        }
    }

    /**
     * Metodus ami lekeri a helyzetet az egysegnek(jatekos/gep)
     * @param unit egyseg amit keres
     * @param player C gepnek ES P jatekosnak
     * @return egy tomb aminek x es y koordinataja van
     */
    public static int[] getLocation(Unit unit, String player) {
        for (int a = 0; a < field.length; a++) {
            for (int b = 0; b < field[a].length; b++) {
                if(field[a][b].getUnit() != null 
                        && field[a][b].getUnit().getName().equals(unit.getName()) 
                        && field[a][b].getPlayer().equals(player)){
                    return new int[]{a,b};
                }
            }
        }
        return null;
    }
    
    static int price = 5;  //kezdo ar (mindig 10%-al novekedik)
    
    /**
     * Ez a metodus csak a jatekosnak van
     * metodus ami egy menut mutat, ahol a jatekos novelni tudja a hos tulajdonsagait
     * a kivalasztott tulajdonsag megadott mennyisegben fog novekedni, ha a jatekosnak van eleg penze
     */
    public static void buyHero() {
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("1. Tamadas\n"
                    + "2. Vedekezes\n"
                    + "3. Varazsero\n"
                    + "4. Tudas\n"
                    + "5. Moral\n"
                    + "6. Szerencse\n"
                    + "0. Vissza a menube");
            System.out.println("Az ar $" + price + " lesz minden egyes pontert amit megveszel");
            System.out.print("Valasz: ");
            int choice = in.nextInt();
            if (choice == 0) {
                return;
            }
            System.out.print("Mennyiseg: ");
            int quantity = in.nextInt();
            int cost = 0;
            switch(choice){
                case 1:
                    if((player.getAttack()+quantity) <= 10){
                        for (int a = 1; a <= quantity; a++) {
                            cost = cost + price;
                            price = price + (int)Math.round(price*0.10);   //10%-al noveli az arat minden iteracional
                        }
                        if(playerGold >= cost){
                            player.increaseAttack(quantity);
                            playerGold -= cost;                            
                            System.out.println("Megvasaroltad "+quantity+" mennyisegben tamadast $"+cost+"-ert");
                        }
                        else{
                            System.out.println("Nem tudod megvasarolni. Nincs eleg aranyad!");
                        }
                    }
                    else{
                        System.out.println("Hiba. Nem lehet 10-nel nagyobb!");
                    }
                    break;
                case 2:
                    if((player.getDefense()+quantity) <= 10){
                        for (int a = 1; a <= quantity; a++) {
                            cost = cost + price;
                            price = price + (int)Math.round(price*0.10);   //10%-al noveli az arat minden iteracional
                        }
                        if(playerGold >= cost){
                            player.increaseDefense(quantity);
                            playerGold -= cost;                            
                            System.out.println("Megvasaroltad "+quantity+" mennyisegben vedekezest $"+cost+"-ert");
                        }
                        else{
                            System.out.println("Nem tudod megvasarolni. Nincs eleg aranyad!");
                        }
                    }
                    else{
                        System.out.println("Hiba. Nem lehet 10-nel nagyobb!");
                    }
                    break;
                case 3:
                    if((player.getMagic()+quantity) <= 10){
                        for (int a = 1; a <= quantity; a++) {
                            cost = cost + price;
                            price = price + (int)Math.round(price*0.10);   //10%-al noveli az arat minden iteracional
                        }
                        if(playerGold >= cost){
                            player.increaseMagic(quantity);
                            playerGold -= cost;                            
                            System.out.println("Megvasaroltad "+quantity+" mennyisegben varazserot $"+cost+"-ert");
                        }
                        else{
                            System.out.println("Nem tudod megvasarolni. Nincs eleg aranyad!");
                        }
                    }
                    else{
                        System.out.println("Hiba. Nem lehet 10-nel nagyobb!");
                    }
                    break;
                case 4:
                    if((player.getKnowledge()+quantity) <= 10){
                        for (int a = 1; a <= quantity; a++) {
                            cost = cost + price;
                            price = price + (int)Math.round(price*0.10);   //10%-al noveli az arat minden iteracional
                        }
                        if(playerGold >= cost){
                            player.increaseKnowledge(quantity);
                            playerGold -= cost;                            
                            System.out.println("Megvasaroltad "+quantity+" mennyisegben tudast $"+cost+"-ert");
                        }
                        else{
                            System.out.println("Nem tudod megvasarolni. Nincs eleg aranyad!");
                        }
                    }
                    else{
                        System.out.println("Hiba. Nem lehet 10-nel nagyobb!");
                    }
                    break;
                case 5:
                    if((player.getMorale()+quantity) <= 10){
                        for (int a = 1; a <= quantity; a++) {
                            cost = cost + price;
                            price = price + (int)Math.round(price*0.10);   //10%-al noveli az arat minden iteracional
                        }
                        if(playerGold >= cost){
                            player.increaseMorale(quantity);
                            playerGold -= cost;                            
                            System.out.println("Megvasaroltad "+quantity+" mennyisegben moralt $"+cost+"-ert");
                        }
                        else{
                            System.out.println("Nem tudod megvasarolni. Nincs eleg aranyad!");
                        }
                    }
                    else{
                        System.out.println("Hiba. Nem lehet 10-nel nagyobb!");
                    }
                    break;
                case 6:
                    if((player.getLuck()+quantity) <= 10){
                        for (int a = 1; a <= quantity; a++) {
                            cost = cost + price;
                            price = price + (int)Math.round(price*0.10);   //10%-al noveli az arat minden iteracional
                        }
                        if(playerGold >= cost){
                            player.increaseLuck(quantity);
                            playerGold -= cost;                            
                            System.out.println("Megvasaroltad "+quantity+" mennyisegben szerencset $"+cost+"-ert");
                        }
                        else{
                            System.out.println("Nem tudod megvasarolni. Nincs eleg aranyad!");
                        }
                    }
                    else{
                        System.out.println("Hiba. Nem lehet 10-nel nagyobb!");
                    }
                    break;
                default:
                    System.out.println("Hibas Input!");
                    break;
            }
        }
    }
    /**
     * Ez a metodus csak a gepnek van
     * metodus ahol a gep novelni tudja a hos tulajdonsagait(random valasztas)
     * a random kivalasztott tulajdonsag random mennyisegben fog novekedni, ha a gepnek van eleg penze
     */
    public static void buyHeroForComputer() {
        while(true){
            int choice = (int) ((Math.random() * (8 - 1)) + 1);  //random valaszt egy tulajdonsagot
            int quantity = (int) ((Math.random() * (5 - 1)) + 1);  //random mennyisegben
            int cost = 0;
            switch(choice){
                case 1:
                    if((computer.getAttack()+quantity) <= 10){
                        cost = price*quantity;
                        if(compGold >= cost){
                            computer.increaseAttack(quantity);
                            compGold -= cost;
                            price = price + (int)(price*0.10);   //10%-al noveli az arat kovetkezo korre
                        }
                    }
                    break;
                case 2:
                    if((computer.getDefense()+quantity) <= 10){
                        cost = price*quantity;
                        if(compGold >= cost){
                            computer.increaseDefense(quantity);
                            compGold -= cost;
                            price = price + (int)(price*0.10);   //10%-al noveli az arat kovetkezo korre
                        }
                    }
                    break;
                case 3:
                    if((computer.getMagic()+quantity) <= 10){
                        cost = price*quantity;
                        if(compGold >= cost){
                            computer.increaseMagic(quantity);
                            compGold -= cost;
                            price = price + (int)(price*0.10);   //10%-al noveli az arat kovetkezo korre
                        }
                    }
                    break;
                case 4:
                    if((computer.getKnowledge()+quantity) <= 10){
                        cost = price*quantity;
                        if(compGold >= cost){
                            computer.increaseKnowledge(quantity);
                            compGold -= cost;
                            price = price + (int)(price*0.10);   //10%-al noveli az arat kovetkezo korre
                        }
                    }
                    break;
                case 5:
                    if((computer.getMorale()+quantity) <= 10){
                        cost = price*quantity;
                        if(compGold >= cost){
                            computer.increaseMorale(quantity);
                            compGold -= cost;
                            price = price + (int)(price*0.10);   //10%-al noveli az arat kovetkezo korre
                        }
                    }
                    break;
                case 6:
                    if((computer.getLuck()+quantity) <= 10){
                        cost = price*quantity;
                        if(compGold >= cost){
                            computer.increaseLuck(quantity);
                            compGold -= cost;
                            price = price + (int)(price*0.10);   //10%-al noveli az arat kovetkezo korre
                        }
                    }
                    break;
                default:
                    return;
            }
        }
    }
}