import java.lang.Math;
import java.util.*;
import java.io.*;

public class Profile {

    public String name;
    public String save_file;

    private int lvl;
    private int xp;
    private int base_damage;  
    public int damage_bonus;
    private int defense;
    private boolean defending; // REMOVE
    public Item[] inventory;
    private int base_hp;
    public int current_hp;
    public int base_energy; // RENAMED FROM MANA
    public int current_energy;
    public int strength;
    public int tech;
    public int constitution;
    public int agility;
    public int stealth;
    private int x_pos;
    private int y_pos;
    private boolean engaged;
    private Enemy engagedWith;

    //public Party party; <-- Need to make

    Profile(String name) {
	this.name = name;
	save_file = "saved_profiles/" + name + ".txt";
	lvl = 1;
	xp = 0;
	base_damage = (int)(Math.random() * 5) + 5;
	inventory = new Item[20];
	base_hp = 30 + (int)(Math.random() * 8);
	current_hp = base_hp;
	base_energy = 100;
	current_energy = base_energy;
	strength = 0;
	tech = 0;
	constitution = 0;
	agility = 0;
	stealth = 0;
	x_pos = 25;
	y_pos = 25;
	engaged = false;
	engagedWith = null;
    }

    
    // Saved Character
    Profile(String name, int lvl, int xp, int base_damage,
	    int damage_bonus, int defense, Item[] inventory, 
	    int base_hp, int current_hp, int base_energy, 
	    int current_energy, int strength, int tech, 
	    int constitution, int agility, int stealth, 
	    int x_pos, int y_pos) {
	this.name = name;
	save_file = "saved_profiles/" + name + ".txt";
	this.lvl = lvl;
	this.xp = xp;
	this.base_damage = base_damage;
	this.damage_bonus = damage_bonus;
	this.defense = defense;
	this.defending = false;
	this.inventory = inventory;
	this.base_hp = base_hp;
	this.current_hp = current_hp;
	this.base_energy = base_energy;
	this.current_energy = current_energy;
	this.strength = strength;
	this.tech = tech;
	this.constitution = constitution;
	this.agility = agility;
	this.stealth = stealth;
	this.x_pos = x_pos;
	this.y_pos = y_pos;
	this.engaged = false;
	this.engagedWith = null;
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
	    writer.println(this.name);
            writer.println(this.lvl);
            writer.println(this.xp);
            writer.println(this.base_damage);
            writer.println(this.damage_bonus);
            writer.println(this.defense);
            writer.println(this.inventory.toString());
            writer.println(this.base_hp);
            writer.println(this.current_hp);
            writer.println(this.base_energy);
            writer.println(this.current_energy);
            writer.println(this.strength);
	    writer.println(this.tech);
	    writer.println(this.constitution);
	    writer.println(this.agility);
	    writer.println(this.stealth);
	    writer.println(this.x_pos);
	    writer.println(this.y_pos);
	    writer.println(this.engaged);
	    writer.println(this.engagedWith);
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


    //            PERSONAL INFO          //
    public void changeName(String new_name) {
	this.save_file = "saved_profiles/" + new_name + ".txt";
	this.name = new_name;
    }


    public String getName() {
	return this.name;
    }




    /*------------------------------*
     *                              *
     *                              *
     *      COMBAT & ABILITIES      *
     *                              *
     *                              *
     *------------------------------*
     */


    // ---------- ATTRIBUTES ---------- //
    public void boostStrength(int n) {
	this.strength += n;
    }

    public int getStrength() {
	return this.strength;
    }

    public void boostTech(int n) {
        this.tech += n;
    }

    public int getTech() {
	return this.tech;
    }

    public void boostCons(int n) {
        this.constitution += n;
    }

    public int getCons() {
	return this.constitution;
    }

    public void boostAgility(int n) {
        this.agility += n;
    }

    public int getAgility() {
	return this.agility;
    }

    public void boostStealth(int n) {
        this.stealth += n;
    }

    public int getStealth() {
	return this.stealth;
    }

    public boolean isEngaged() {
	return this.engaged;
    }

    public Enemy engagedWith() {
	return this.engagedWith;
    }

    public void isEngaging(Enemy e) throws InterruptedException {
	this.engaged = true;
	this.engagedWith = e;
	PlayGame.typeMsg("Engaging new enemy...", 1);
    }

    public int getDmg() {
	return this.base_damage;
    }

    
    // ---------- DEFENSE & RESISTANCE ---------- //
    public void defend() throws InterruptedException {
	PlayGame.typeMsg("Defending... \nYour defense: " + this.defense, 1);
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

    public int attackRoll() {
	return (int) (Math.random() * 101);
    }


    // returns new damage value so we can still keep the old one
    public int decreaseAttack(int n) {
	return this.base_damage - n;
    }


    public void attack(Enemy e) throws InterruptedException {
	// chance of miss
	int attk_roll = this.attackRoll();

	if ( attk_roll > e.getAgility() ) {   
	    int damage_roll = (int) (Math.random() * base_damage);
            int total_damage = damage_roll + base_damage + damage_bonus;
	    e.takeDamage(total_damage);
	    PlayGame.typeMsg("You hit your target for " + total_damage + " damage!", 1);
	}

	else 
	    PlayGame.typeMsg("Attack missed...", 1);
	
    }

    public void specialAbility(Enemy e) throws InterruptedException {
	String choice = PlayGame.updatePrompt("Please select an ability:\n" +
					      "1| Calculated Strike\n" +
					      "2| Robot Arm Cannon\n" +
					      "3| Stun", 1);
	while (true) {

	    if ( choice.equals("1") ) {
		this.calculatedStrike(e);
		return;
	    }

	    else if ( choice.equals("2") ) {
		this.armCannon(e);
		return;
	    }

	    else if ( choice.equals("3") ) { 
		this.stun(e);
		return;
	    }
 
	    else
		choice = PlayGame.prompt("Please enter a correct command  ", 1);

	}
    
    }
    
    
    public void calculatedStrike(Enemy e) throws InterruptedException {
	boolean hit = ( this.attackRoll() + this.lvl + this.getAgility() ) > e.getAgility() ;

	if ( hit ) {
	    e.takeDamage(this.base_damage * 3);
	    PlayGame.typeMsg("You punch the enemy straight in the " + 
			     "chest with 2000 pounds of force. ", 1);
	    PlayGame.typeMsg("Enemy takes " + this.base_damage * 2 + " damage.", 2);
	
	}
	
	else 
	    PlayGame.typeMsg("The challenger swiftly dodges your attack", 2);
			
    
	this.spendEnergy(20);
    }

    
    public void armCannon(Enemy e) throws InterruptedException {
	boolean hit = ( this.attackRoll() + this.lvl ) > e.getAgility();
	this.spendEnergy(40);
	
	if ( hit ) {
	    int damage_roll = (int) (Math.random() * base_damage) + base_damage;
            int total_damage = (int) ( (1 + ( this.lvl / 50.0 ) ) * 
				       (damage_roll + damage_bonus) );
	    e.takeDamage(total_damage);

	    PlayGame.typeMsg("Enemy is blasted for " + total_damage + " damage.", 2);
	}

	else
	    PlayGame.typeMsg("You missed your target...", 2);
	
    }
    

    public void stun(Enemy e) throws InterruptedException {
	int roll = ( this.attackRoll() + this.lvl );
	boolean hit = roll > e.getAgility();
	boolean stun = hit && ( roll >= 60 );
        this.spendEnergy(25);
	
	if ( stun ) {
	    e.setImmobile();
	    PlayGame.typeMsg("Challenger is now stunned!", 1);
	}

	if ( hit ) {   
	    int damage_roll = (int) ( Math.random() * base_damage );
	    int total_damage = damage_roll + 5;    
	    e.takeDamage(total_damage);
	    PlayGame.typeMsg("You dealt " + total_damage + " points of damage.", 2);
	}

    }



    // ------------ HIT POINTS ----------- //
    public int getHP() {
	return this.current_hp;
    }
    
    public void heal(int n) throws InterruptedException {
	this.current_hp += n;
	if (this.current_hp > this.base_hp) 
	    this.current_hp = this.base_hp;
	PlayGame.typeMsg("Regained " + n + " hit points", 1);
    }
    
    public void takeDamage(int n) {
        this.current_hp -= n;
    }
    
    
    // ------------ Energy ------------ //
    public void spendEnergy(int n) {
	this.current_energy -= n;
    }

    public void regainEnergy(int n) {
	if (this.current_energy + n >= this.base_energy) {
	    this.current_energy = this.base_energy;
	}
	else
	    this.current_energy += n;
    }
    

    // ------------ EXPERIENCE ----------- //
    public int getLvl() {
	return this.lvl;
    }

    public void gainXP(int xp_gained) {
	this.xp += xp_gained; 
	if (this.xp >= (this.getLvl() * 50)) 
	    this.levelUp();
    }

    private void levelUp() {
	this.lvl += 1;
	this.base_damage += (int) (Math.random() * 6) + 1;
	this.defense += 2;
	this.base_hp += (int) (Math.random() * 10) + 4;
	System.out.println("Level up! You are now level " + this.lvl);
    }
    
    // ------------ POSITIONING ----------- //
    
    public int getX() {
      return this.x_pos;
    }
    
    public int getY() {
      return this.y_pos;
    }

}
