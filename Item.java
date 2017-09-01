import java.util.*;

public class Item {
    String item_name;
    int damage;
    int constitution;
    int attack_speed;
    String item_slot;

    Item(String name, int dmg, int hp, int attk_spd, String slot) {
	this.item_name = name;
	this.damage = dmg;
	this.constitution = hp;
	this.attack_speed = attk_spd;
	this.item_slot = slot;
    }

    Item() {
	this.item_name = "Unnamed";
	this.damage = 0;
	this.constitution = 0;
	this.attack_speed = 0;
	this.item_slot = "NA";
    }
	

    public String getName() {
	return this.item_name;
    }

    public String getSlotType() {
	return this.item_slot;
    }

    public int getDamage() {
	return this.damage;
    }
    
    public int getConstitution() {
	return this.constitution;
    }

    public int getAttackSpeed() {
	return this.attack_speed;
    }

    public int[] modifierSummary() {
	int[] mods = {this.damage, this.constitution, this.attack_speed};
	System.out.println("DMG: " + this.damage + "\nHP: " + this.constitution + "\nATTK SPD: " + this.attack_speed);
	return mods;
    }

    
}
