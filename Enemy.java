import java.lang.Math;
import java.util.*;

public class Enemy {
    String name;
    int lvl;
    int xp; // How much is earned when defeating it
    int base_damage;
    int damage_die;
    int damage_decrease; // from curses or hostile spells
    int base_health;
    int current_health;
    boolean immobile;
    int defense;
    int strength;
    int tech;
    int constitution;
    int agility;
    int stealth;
    int current_energy;
    

    Enemy() {
	name = "Unknown";
	lvl = 0;
	xp = (int)(Math.random() * 10) + 10;
	base_damage = 2;
	damage_die = 4;
	damage_decrease = 0;
	base_health = 10 + (int)(Math.random() * 10);
	current_health = base_health;
	immobile = false;
	defense = 1;
	strength = 0;
	tech = 0;
	constitution = 0;
	agility = 0;
	stealth = 0;
	current_energy = 50;
    }

    Enemy(int player_lvl) {
	name = "Fuck you";
	lvl = player_lvl;
	xp = (int) (Math.random() * (10 * this.lvl));
	base_damage = 2 * lvl;
	damage_decrease = 0;
	base_health = 10 + (int)(Math.random() * (lvl * 10) );
	current_health = base_health;
	immobile = false;
	defense = (int)(1.5 * lvl);
	strength = lvl;
	tech = lvl;
	constitution = lvl;
	agility = lvl;
	stealth = lvl;
	current_energy = (int)(50 * 1.25);
    }

    Enemy(String name, int lvl, int xp, int base_damage, int damage_die, 
	  int base_health, int current_health, int defense, int strength, 
	  int tech, int constitution, int agility, int stealth) {
	this.name = name;
	this.lvl = lvl;
	this.xp = xp;
	this.base_damage = base_damage;
	this.damage_die = damage_die;
	this.damage_decrease = 0;
	this.base_health = base_health;
	this.current_health = current_health;
	this.immobile = false;
	this.defense = defense;
	this.strength = strength;
	this.tech = tech;
	this.constitution = constitution;
	this.agility = agility;
	this.stealth = stealth;
	this.current_energy = (int) ((this.lvl + 0.25) * 50 );
    }

    // ----------- MOVEMENT ---------- //
    public void setImmobile() {
	this.immobile = true;
    }

    public void setMobile() {
	this.immobile = false;
    }

    public boolean isImmobile() {
	return this.immobile;
    }

    
    
    // ----------- DEFENSE & ATTRIBUTES ----------- //
    public int getDefense() {
	return this.defense;
    }

    public int getStrength() {
	return this.strength;
    }

    public int getTech() {
	return this.tech;
    }

    public int getCons() {
	return this.constitution;
    }

    public int getAgility() {
	return this.agility;
    }

    public int getStealth() {
	return this.stealth;
    }


    // ----------- OFFENSE ----------- //
    public int attackRoll() {
	return (int) ( (Math.random() * 101) + lvl) ;
    }


    public void attack(Profile player) throws InterruptedException {
	if (this.isImmobile() ) {
	    System.out.println("Enemy cannot move\n");
	    this.setMobile();
	    return;
	}

	boolean hit = this.attackRoll() > player.getAgility();

	if ( hit ) {
	    int damage_roll = (int)(Math.random() * this.damage_die) + 1;
	    int total_damage = damage_roll + base_damage - damage_decrease;
	    
	    if (player.isDefending() ) 
		total_damage -= player.getDefense();
	
	    else 
		total_damage -= player.getDefense() / 4;

	    if ( total_damage <= 0 )
		PlayGame.typeMsg("Enemy did 0 damage", 2);
	    else {
		player.takeDamage(total_damage);
		PlayGame.typeMsg("Enemy hit you for " + total_damage + " damage.", 2);
	    }

	} else {
	    PlayGame.typeMsg("You evaded the challenger's attack!", 2);
	}
    
    }
                 
    public void decreaseAttack(int n) {
	this.damage_decrease += n;
    }

    // ----------- HIT POINTS ----------- //
    public void takeDamage(int n) {
	this.current_health -= n;
    }

    public int getHP() {
	return this.current_health;
    }

    public int getDamage() {
	return this.base_damage;
    }


    // ------------ XP ------------ //
    public int getXP() {
	return this.xp;
    }
    
	
}
