import java.lang.Math;
import java.util.*;
import java.io.*;

public class Profile {

    private String name;
    public String save_file;
    public Class char_class;
    private int lvl;
    private int xp;
    private int base_damage;
    private int damage_die;
    public int damage_bonus;
    private int defense;
    private boolean defending;
    public Item[] inventory;
    private int base_hp;
    public int current_hp;
    public int base_mana;
    public int current_mana;
    public Ability[] abilities;
    //public Party party; <-- Need to make

    Profile(String name) {
	this.name = name;
	save_file = name + ".txt";
	lvl = 1;
	xp = 0;
	base_damage = 5;
	damage_die = 6;
	damage_bonus = 0;
	defense = 4;
	inventory = new Item[20];
	base_hp = 30 + (int)(Math.random() * 8);
	current_hp = base_hp;
	base_mana = 100;
	current_mana = base_mana;
	abilities = new Ability[10];
    }




    private void chat() {
	System.out.println("Hello");
    }

    /*------------------------------*
     *                              *
     *                              *
     *        SAVE & RECORDS        *
     *                              *
     *                              *
     *------------------------------*                                            
     */

    public void save() {
	try {
	    PrintWriter writer = new PrintWriter(save_file, "UTF-8");
	    writer.println("");
	    writer.close();
	} catch (IOException e) {
	    // do something
	}
	

    }
    

    /*
    public void joinParty(Party p) {
	// Party class and addMember method need to be added
	p.addMember(this);
	this.party = p;
    }
    */

    




    /*
     *-----------------------
     *  END OF SAVE SECTION 
     *-----------------------
     */




    /*------------------------------*
     *                              *
     *                              *
     *      COMBAT & ABILITIES      *
     *                              *
     *                              *
     *------------------------------*
     */

   
    // ---------- DEFENSE & RESISTANCE ---------- //
    public void defend() {
	System.out.println("Defending... \nYour defense: " + this.defense);
	this.defending = true;
    }

    public int getDefense() {
	return this.defense;
    }

    public boolean isDefending() {
	return this.defending;
    }

    public void resetDefense() {
	this.defending = false;
    }

    public void dropDefense(int n) {
	this.defense -= n;
    }


    // ----------- OFFENSE ---------- //
    public int getDamage() {
        return this.base_damage;
    }

    // returns new damage value so we can still keep the old one
    public int decreaseAttack(int n) {
	return this.base_damage - n;
    }

    public void attack(Enemy e) {
	int damage_roll = (int) (Math.random() * base_damage);
	int total_damage = damage_roll + base_damage + damage_bonus;
	e.takeDamage(total_damage);
    }

    public void castSpell(Enemy e) {
	Scanner s = new Scanner(System.in);
	System.out.println("\nWhat spell would you like to cast?" +
			   "\n1| Fireball" +
			   "\n2| Freeze" +
			   "\n3| Demoralize" +
			   "\n4| Confuse" +
			   "\n5| Steal Life" );
  
	while (s.hasNextLine() ) {
	    String spell = s.nextLine();
	    if (spell.equals("1") ) {
		Spells.castFireball(this, e);
		return;
	    }

	    else if (spell.equals("2") ) {
		Spells.castFreeze(this, e);
		return;
	    }

	    else if (spell.equals("3") ) {
		Spells.castDemoralize(this, e);
		return;
	    }

	    else if (spell.equals("4") ) {
		Spells.castConfuse(this, e);
		return;
	    }

	    else if (spell.equals("5") ) { 
	        Spells.castStealLife(this, e);
		return;
	    }

	    else
		System.out.println("\n***** Invalid command, please try again *****\n");
	    
	}
	
	return;
    }


    // ------------ HIT POINTS ----------- //
    public int getHP() {
	return this.current_hp;
    }

    public void heal(int n) {
	this.current_hp += n;
	if (this.current_hp > this.base_hp) 
	    this.current_hp = this.base_hp;
	System.out.println("Regained " + n + " hit points\n");
    }

    public void takeDamage(int n) {
        this.current_hp -= n;
    }


    // ------------ MANA ------------ //
    public void decreaseMana(int n) {
	this.current_mana -= n;
    }
    

    // ------------ EXPERIENCE ----------- //
    public int getLvl() {
	return this.lvl;
    }

    public void gainXP(int xp_gained) {
	this.xp += xp_gained; 
	if (this.xp >= 50) 
	    this.levelUp();
    }

    private void levelUp() {
	this.lvl += 1;
	this.base_damage += (int) (Math.random() * 6) + 1;
	this.defense += 2;
	this.damage_die *= (1.05 + 0.02*this.lvl);
	this.base_hp += (int) (Math.random() * 10) + 1;
	System.out.println("Level up! You are now level " + this.lvl);
    }

}
