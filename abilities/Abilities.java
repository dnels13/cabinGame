package abilities;

import enemies.*;
import character.*;
import items.*;
import run.PlayGame;

import java.util.*;
import java.lang.Math;
import java.io.*;

public class Abilities {
    //NEED TO FIX THIS CLASS - CAUSING ERRORS IN THE OTHERS
    //This can't compile, and consequently a Profile() cannot
    //be created because Abilities doesn't "exist".

    public static boolean energyCheck(Profile player, int energy_cost) throws InterruptedException {
	boolean hasEnergy = player.getEnergy() >= energy_cost;
	if (! hasEnergy)
	    PlayGame.typeMsg("You don't have enough energy to perform this ability", 2);

	return hasEnergy; 
    }

    public static boolean energyCheck(Enemy e, int energy_cost) {
	return e.getEnergy() >= energy_cost;
    }



    /*----------------------------------------------*
     *                                              *
     *                                              *
     *              Player Abilities                *
     *                                              *
     *                                              *
     *----------------------------------------------*/

    public static void calculatedStrike(Profile player, Enemy target) throws InterruptedException {
	if (! energyCheck(player, 20) ) 
	    return;
   

	boolean hit = ( player.attackRoll() + player.getLvl() + player.getAgility() ) > target.getAgility() ;

	if ( hit ) {
	    target.takeDamage(player.getDmg() * 3);
	    PlayGame.typeMsg("You punch the enemy straight in the " + 
			     "chest with 2000 pounds of force. ", 1);
	    PlayGame.typeMsg("Enemy takes " + player.getDmg() * 2 + " damage.", 2);
	    
	}
	
	else 
	    PlayGame.typeMsg("You missed!", 2);
	
    
	player.spendEnergy(20);
    } // END OF CALCULATEDSTRIKE


    
    public static void armCannon(Profile player, Enemy e) throws InterruptedException {
	
	if ( energyCheck(player, 40) )
	    return;
	

	boolean hit = ( player.attackRoll() + player.getLvl() ) > e.getAgility();
	player.spendEnergy(40);
	
	if ( hit ) {
	    int damage_roll = (int) (Math.random() * player.getDmg()) + player.getDmg();
            int total_damage = (int) ( (1 + ( player.getLvl() / 50.0 ) ) * 
				       (damage_roll + player.getDmgBonus()) );
	    e.takeDamage(total_damage);

	    PlayGame.typeMsg("Enemy is blasted for " + total_damage + " damage.", 2);
	}

	else
	    PlayGame.typeMsg("You missed your target...", 2);
	
    } // END OF ARMCANNON
    


    public static void stun(Profile player, Enemy e) throws InterruptedException {
	if (energyCheck(player, 25) ) {

	    int roll = ( player.attackRoll() + player.getLvl() );
	    boolean hit = roll > e.getAgility();
	    boolean stun = hit && ( roll >= 60 );
	    
	    if ( stun ) {
		e.setImmobile();
		PlayGame.typeMsg("Challenger is now stunned!", 1);
		player.spendEnergy(15);
	    }
	    
	    if ( hit ) {   
		int damage_roll = (int) ( Math.random() * player.getDmg() );
		int total_damage = damage_roll + 5;    
		e.takeDamage(total_damage);
		PlayGame.typeMsg("You dealt " + total_damage + " points of damage.", 2);
		player.spendEnergy(10);
	    }

	    else {
		player.spendEnergy(10);
		PlayGame.typeMsg("You missed!!!", 2);
	    }
	    
	}

	else 
	    PlayGame.typeMsg("You don't have enough energy to do that!", 2);

    } // END OF STUN


} // END OF CLASS
