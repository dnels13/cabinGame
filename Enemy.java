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
    String type;
    boolean immobile;
    boolean confused;
    int defense;
    int fire_resistance;
    int cold_resistance;

    Enemy() {
	name = "Unknown";
	lvl = 0;
	xp = (int)(Math.random() * 10) + 10;
	base_damage = 2;
	damage_die = 4;
	damage_decrease = 0;
	base_health = 10 + (int)(Math.random() * 10);
	current_health = base_health;
	type = "NA";
	immobile = false;
	defense = 1;
	fire_resistance = 0;
	cold_resistance = 0;
    }

    Enemy(String name, int lvl, int xp, int base_damage, int damage_die, int base_health, int current_health, String type, int defense, int fire_resistance, int cold_resistance) {
	this.name = name;
	this.lvl = lvl;
	this.xp = xp;
	this.base_damage = base_damage;
	this.damage_die = damage_die;
	this.damage_decrease = 0;
	this.base_health = base_health;
	this.current_health = current_health;
	this.type = type;
	this.immobile = false;
	this.defense = defense;
	this.fire_resistance = fire_resistance;
	this.cold_resistance = cold_resistance;
    }

    // ----------- MOVEMENT ---------- //
    public void setImmobile() {
	this.immobile = true;
    }

    public boolean isImmobile() {
	return this.immobile;
    }

    
    
    // ----------- DEFENSE & RESISTANCE ----------- //
    public int getDefense() {
	return this.defense;
    }

    public int getFireResistance() {
	return this.fire_resistance;
    }

    public int getColdResistance() {
	return this.cold_resistance;
    }


    // ----------- OFFENSE ----------- //
    public void attack(Profile player) {
	int damage_roll = (int)(Math.random() * this.damage_die) + 1;
	int total_damage = damage_roll + base_damage - damage_decrease;
	
	if (this.isImmobile() ) {
	    System.out.println("Enemy cannot move\n");
	    return;
	}

	else if (this.isConfused() ) {
	    double chance = Math.random() / player.getLvl();
	    if (chance < 0.05) {
		System.out.println("Enemy is confused and missed\n");
		return;
	    }
	}

	if (player.isDefending() ) {
	    total_damage -= player.getDefense();
	    player.takeDamage(total_damage);
	}
	
	else { 
	    total_damage -= player.getDefense() / 4;
	    player.takeDamage(total_damage);
	}
	
    }

    public void decreaseAttack(int n) {
	this.damage_decrease += n;
    }

    public void setConfused() {
	this.confused = true;
    }
    
    public boolean isConfused() {
	return this.confused;
    }

    public int castSpell(Profile target) {
	/* 
	 *
	 *   enemy spells
	 *
	 */

	return 0;
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
