package items;

import java.util.*;

//My Classes
import run.PlayGame;
import character.*;
import enemies.*;
import abilities.*;

public class Item {
    String item_name;
    String item_slot;

    int dmg;
    int cons_bonus;
    int str_bonus;
    int stealth_bonus;
    int agility_bonus;
    int tech_bonus;


    Item(String name, String item_slot, int dmg, int cons_bonus, int str_bonus,
	 int stealth_bonus, int agility_bonus, int tech_bonus) {
	this.item_name = name;
	this.item_slot = item_slot;
	this.dmg = dmg;
	this.cons_bonus = cons_bonus;
	this.str_bonus = str_bonus;
	this.stealth_bonus = stealth_bonus;
	this.agility_bonus = agility_bonus;
	this.tech_bonus = tech_bonus;	
    }


    Item() {
	item_name = "NA";
	item_slot = "body";
	dmg = 0;
	cons_bonus = 0;
	str_bonus = 0;
	stealth_bonus = 0;
	agility_bonus = 0;
	tech_bonus = 0;
    }
	

    public String getName() {
	return this.item_name;
    }

    public String getSlotType() {
	return this.item_slot;
    }

    public int getDamage() {
	return this.dmg;
    }
    
    public int getConstitution() {
	return this.cons_bonus;
    }

    public int getStrength() {
	return this.str_bonus;
    }

    public int getAgility() {
	return this.agility_bonus;
    }

    public int getStealth() {
	return this.stealth_bonus;
    }

    public int getTech() {
	return this.tech_bonus;
    }

    public void modifierSummary() {
	System.out.println("Item Name: " + this.getName() + "\n" +
			   "Slot type: " + this.getSlotType() + "\n" + 
			   "Damage: " + this.getDamage() + "\n" + 
			   "Constitution: " + this.getConstitution() + "\n" +
			   "Strength: " + this.getStrength() + "\n" +
			   "Agility: " + this.getAgility() + "\n" +
			   "Stealth: " + this.getStealth() + "\n" +
			   "Tech: " + this.getTech() );
	    
	    
    }

    
}
