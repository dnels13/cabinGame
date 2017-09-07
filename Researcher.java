import java.lang.Math;
import java.util.*;
import java.io.*;

public class Researcher extends Profile {


    Researcher(String name) {
	super(name);
	this.strength = 10;
	this.tech = 4;
	this.constitution = 7;
	this.agility = 5;
	this.stealth = 2;
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

	}

    }

    
    
    
    /*---------------------------------------------------------*
     *                                                         *
     *                                                         * 
     *                                                         * 
     *                SPECIAL CLASS ABILITIES                  * 
     *                                                         * 
     *                                                         * 
     *                                                         * 
     *---------------------------------------------------------*
     */


    public void calculatedStrike(Enemy e) throws InterruptedException {
        boolean hit = ( this.attackRoll() + this.getLvl() + this.getAgility() ) > e.getAgility() ;

        if ( hit ) {
            e.takeDamage(this.getDmg() * 3);
            PlayGame.typeMsg("You punch the enemy straight in the " +
                             "chest with 2000 pounds of force. ", 1);
            PlayGame.typeMsg("Enemy takes " + this.getDmg() * 2 + " damage.", 2);

        }

        else
            PlayGame.typeMsg("The challenger swiftly dodges your attack", 2);


        this.spendEnergy(20);
    }




    public void armCannon(Enemy e) throws InterruptedException {
        boolean hit = ( this.attackRoll() + this.getLvl() ) > e.getAgility();
        this.spendEnergy(40);

        if ( hit ) {
            int damage_roll = (int) (Math.random() * this.getDmg() ) + this.getDmg();
            int total_damage = (int) ( (1 + ( this.getLvl() / 50.0 ) ) *
                                       (damage_roll + this.getDmg() ) );
            e.takeDamage(total_damage);

            PlayGame.typeMsg("Enemy is blasted for " + total_damage + " damage.", 2);
        }

        else
            PlayGame.typeMsg("You missed your target...", 2);
    
    }




    public void stun(Enemy e) throws InterruptedException {
        int roll = ( this.attackRoll() + this.getLvl() );
        boolean hit = roll > e.getAgility();
        boolean stun = hit && ( roll >= 60 );
        this.spendEnergy(25);

        if ( stun ) {
            e.setImmobile();
            PlayGame.typeMsg("Challenger is now stunned!", 1);
        }

        if ( hit ) {
            int damage_roll = (int) ( Math.random() * this.getDmg() );
            int total_damage = damage_roll + 5;
            e.takeDamage(total_damage);
            PlayGame.typeMsg("You dealt " + total_damage + " points of damage.", 2);
        }

    }
    

}
