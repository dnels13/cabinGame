import java.util.*;
import java.lang.Math;
import java.io.*;

public class Abilites {


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


}
