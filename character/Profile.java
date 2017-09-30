package character;

import java.lang.Math;
import java.util.*;
import java.io.*;

//My Classes
import items.*;
import abilities.*;
import enemies.*;
import run.PlayGame;

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

    //SLOTS
    public Item head;
    public Item left_arm;
    public Item right_arm;
    public Item body;
    public Item legs;
    public Item eyes;

    //public Party party; <-- Need to make

    public Profile(String name) {
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
    public Profile(String name, int lvl, int xp, int base_damage,
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

    public int getDmgBonus() {
	return this.damage_bonus;
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
    
   

    // ------------ HIT POINTS & ENERGY ----------- //
    public int getHP() {
	return this.current_hp;
    }

    // Remove and add to abilities
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

    public int getEnergy() {
	return this.current_energy;
    }

    public int getBaseEnergy() {
	return this.base_energy;
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
