import java.lang.Math;
import java.util.*;

public class Spells {
    public static void castFireball(Profile player, Enemy e) {
	int damage = player.getDamage() * 2;
	player.decreaseMana(20);
	damage -= e.getFireResistance();
	e.takeDamage(damage);
	System.out.println("Fireball did " + damage + " damage.");
	return;
    }

    public static void castFreeze(Profile player, Enemy e) {
	int damage = player.getDamage() / 3;
	player.decreaseMana(30);
	damage -= e.getColdResistance();
	e.takeDamage(damage);
	e.setImmobile();
	System.out.println("Freeze did " + damage + " damage and the target is now frozen.");
    }

    public static void castDemoralize(Profile player, Enemy e) {
	e.decreaseAttack(player.getLvl() * 3);
	player.decreaseMana(15);
	System.out.println("Enemy's attack decreased.");
    }

    public static void castConfuse(Profile player, Enemy e) {
	e.setConfused();
	player.decreaseMana(10);
	System.out.println("Enemy is now confused. Has a small chance to miss.");
    }

    public static void castStealLife(Profile player, Enemy e) {
	int life = (int)( Math.random() * (e.getHP() / 2) ) + player.getLvl();
	e.takeDamage(life);
	player.heal(life);
	player.decreaseMana(35);
	System.out.println("You stole " + life + " life frome your target.");
    }

}