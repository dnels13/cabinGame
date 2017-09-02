import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.TimeUnit;
import java.io.*;

public class PlayGame {
    /*-------------------------------------------------*
     *                                                 *
     *                 CHARACTER LOAD                  *
     *                                                 *
     *-------------------------------------------------*
     */
    public static Profile loadChar(String char_name) throws InterruptedException {
	try(BufferedReader br = new BufferedReader(new FileReader("saved_profiles/" + char_name.toLowerCase() + ".txt"))) {
		br.readLine();
		int lvl = Integer.parseInt(br.readLine());
		int xp = Integer.parseInt(br.readLine());
		int base_dmg = Integer.parseInt(br.readLine());
		int dmg_die = Integer.parseInt(br.readLine());
		int dmg_bonus = Integer.parseInt(br.readLine());
		int dfn = Integer.parseInt(br.readLine());
		Item[] inv = loadItems(br.readLine());
		int base_hp = Integer.parseInt(br.readLine());
		int current_hp = Integer.parseInt(br.readLine());
		int base_mana = Integer.parseInt(br.readLine());
		int current_mana = Integer.parseInt(br.readLine());
		Ability[] abilities = loadAbilities(br.readLine());
		
		Profile player = new Profile(char_name, lvl, xp, base_dmg,
					    dmg_die, dmg_bonus, dfn, inv,
					    base_hp, current_hp, base_mana,
					    current_mana, abilities);
		typeMsg("Player experience: " + xp, 1);
		return player;
		
	    } catch(IOException e) {
	    typeMsg("Character not found...", 2);
		}
    
	return new Profile(char_name);
    }


    
    public static Item[] loadItems(String s) {
	String[] all_items = s.split(" ");
	int l = all_items.length;

	Item[] I = new Item[l];
	// FILL IN
	return I;
    }



    public static Ability[] loadAbilities(String s) {
	String[] all_abilities = s.split(" ");
	int l = all_abilities.length;

	//FILL IN
	Ability[] A = new Ability[l];
	//FILL IN
	return A;
    }


    /*-------------------------------------------------*
     *                                                 *
     *                                                 * 
     *                 GAME  MESSAGING                 *
     *                       &                         *
     *                  UPDATE METHODS                 *
     *                                                 *
     *                                                 * 
     *-------------------------------------------------*
     */

    static int MSG_SPEED = 40; // how fast the "computer" will type. 0 = instant typing
    static int LB_DELAY = 150; // To delay printing line breaks
    static Scanner PROMPT = new Scanner(System.in);

    
    private static String capitalize(String line) {
	return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
    

    // For printing out messages to the user. Computer is typing feel.
    public static void typeMsg(String msg, int lbreaks) throws InterruptedException {
	
	// Print message
	for(int i = 0; i < msg.length(); i++) {
	    Thread.sleep(MSG_SPEED);
	    System.out.print(msg.charAt(i));
	}
	
	// Print linebreaks
	for (int i = 0; i < lbreaks; i++) {
	    System.out.println("\n");
	    Thread.sleep(LB_DELAY);
	}
    }



    /*
     * For printing a question, collecting user 
     * response and printing delayed trailing 
     * line breaks
     */
    public static String prompt(String question, int lbreaks) throws InterruptedException {
	typeMsg(question, 0);
	String response = PROMPT.nextLine();
	typeMsg("", lbreaks);

	return response.toLowerCase();
    }

    public static String updatePrompt(String msg, int lbreaks) throws InterruptedException {
	updateMsg(msg, 1);
	String response = PROMPT.nextLine();
	typeMsg("", lbreaks);

	return response.toLowerCase();
    }



    // For instant printing with delayed trailing line breaks
    public static void updateMsg(String msg, int lbreaks) throws InterruptedException {
	System.out.print(msg);
	typeMsg("", lbreaks);
    }


    /////////////////////////////////////////////////////////////////
    //                                                             //
    //                                                             //
    //                                                             //
    //                          MAIN METHOD                        // 
    //                                                             //
    //                                                             //
    //                                                             //
    /////////////////////////////////////////////////////////////////

    public static void main(String[] args) throws InterruptedException {

	// Intro Effect //

	typeMsg("", 20);
	typeMsg("Cabin Fever", 0);
	TimeUnit.SECONDS.sleep(1);
	typeMsg("", 16);
	
	// END INTRO //




	/*
	 *******************
	 *                 *
	 *   BEGIN GAME    *  
	 *                 *
	 *******************
	 */
	
	// Q1 - PLAYER CREATION
	Profile player = new Profile("dummy");
	String character_name = "";

	String player_decision = prompt("Do you wish to load a saved character?  ", 1);
	
	if (player_decision.toLowerCase().equals("yes") ) {
	    character_name = prompt("What is the name of your character?  ", 1);
	    typeMsg("Ah, good to see you again " +
		    capitalize(character_name), 2);
	    player = loadChar(character_name);
	}

	else {
	    character_name = prompt("What would you like the name of your character to be?   ", 2);
	    player = new Profile(character_name);

	    typeMsg("Hmmm...", 1);
	    typeMsg("Well then. Welcome to my game " 
		    + capitalize(character_name), 2);
	}	
	///// DONE CREATING PLAYER /////



	// Ask for battle(s)
	while( true ) {	    
	    player_decision = prompt("Would you like to fight a monster?   ", 1);
	    // typeMsg("You typed:" + player_decision, 1);
	    if ( player_decision.equals("no") ) {
		typeMsg("Okay then, bye", 3);
		break;
	    }

	    // Initiate a battle
	    else if ( player_decision.equals("yes") ) {
		typeMsg("Very well, let's see how you fare", 10);
		
		//Create a new enemy
		Enemy e = new Enemy();

		int turn_counter = 0;

		updateMsg("BEGIN BATTLE!!!\n---------------", 3);

		// Fight until enemy dies
		while (e.getHP() > 0) {

		    updateMsg("------- TURN " + turn_counter + " -------" +
			      "\n\nEnemy HP: " + e.getHP() +
			      "\nYour HP: " + player.getHP() +
			      "\n--------------------", 3 );
		    
		    // Even turn -> Player
		    if (turn_counter % 2 == 0) {
			// set Profile.defending to False
			player.resetDefense();

			// ask for a command
			String player_action = updatePrompt("CHOOSE YOUR ACTION \n" +
							    "1| to attack\n" + 
							    "2| to defend\n" + 
							    "3| to cast a spell\n\n", 1 );
 
			// make sure command is valid
			while ( true ) {
			    // For troubleshooting
			    //typeMsg("You typed:" + player_action, 1);


			    // attack
			    if (player_action.equals("1") ) {
				typeMsg("You swing your sword valiantly at the enemy", 1);
				player.attack(e);
				break;
			    }
			    // defend
			    else if (player_action.equals("2") ) {
				player.defend();
				break;
			    }
			    // cast
			    else if (player_action.equals("3") ) {
				player.castSpell(e);
				break;
			    }
			    // incorrect input, get new one
			    else 
				player_action = prompt("\nPlease enter a correct command  ", 1);
			}

			
		    } // END OF PLAYER TURN

		    // Odd turn -> Enemy
		    else if (turn_counter % 2 == 1) {
			typeMsg("...Enemy's turn...", 1);

			e.attack(player);

			// for dramatic effect
			TimeUnit.SECONDS.sleep(1);
			// Player death
			if ( player.getHP() <= 0 ) {
			    typeMsg("You have died", 1);
			    return;
			}

		    } // END OF ENEMY TURN
		    
		    // All actions have taken place, move on to next turn
		    typeMsg("Turn Now Over", 2);
		    turn_counter += 1;
		    
		    
		}
		
		/* END OF HP LOOP - ENEMY DEAD
		 * Matches while (e.getHP() > 0) {
		 *
		 *Ask for another battle
		 *
		 */

		typeMsg("Enemy is dead", 1);
		player.gainXP(e.getXP() );
		typeMsg("Experience gained: " + e.getXP(), 1);
	    }

	    
	    // Part of first if-block. User did not enter "yes" or "no"
	    else {
		updateMsg("Sorry, your input was unintelligible.", 2);
	    }

	} 
	
	// SAVE PLAYER DATA
	player.save();
	typeMsg("Player data saved, please come back...", 1);
	Thread.sleep(100);
	typeMsg(capitalize(character_name), 2);

	// CLOSE SCANNER                                                        
	PROMPT.close();

    } // END OF MAIN METHOD
} // END OF CLASS

