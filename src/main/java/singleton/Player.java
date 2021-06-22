package singleton;

import decorator.CodeAMon;
import factory.*;

import java.util.Random;

/**
 * Player (Player.java)
 *
 * This class uses the Singleton design pattern simulating a client as a player.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Player {
    private static Player controller = new Player();
    private AbstractFactory factory;

    /**
     * Instantiate a ConcreteFactory.
     */
    private Player() {
        this.factory = new Factory();
    }

    /**
     * Getter returns a single instance of this class.
     *
     * @return Player
     */
    public static Player getController() {
        return controller;
    }

    /**
     * Create a new trainer.
     *
     * @return trainer
     */
    public AbstractTrainer getAbstractTrainer() {
        return factory.createTrainer();
    }

    /**
     * Create a new code-a-mon.
     *
     * @return monster
     */
    public AbstractMonster getAbstractMonster() {
        return factory.createMonster();
    }

    public void initializeTheWildeLand() {
        System.out.println("Initializing world...\n");

        // Build monsters
        Monster wildWale = controller.buildMonster(controller, "Wale");
        Monster wildKaht = controller.buildMonster(controller, "Kaht");
        Monster wildPyth = controller.buildMonster(controller, "Pyth");
        Monster wildJaxx = controller.buildMonster(controller, "Jaxx");
//        Monster wildJaxx = controller.buildMonster(controller, "Jaxx");

        // Build trainers
        Trainer dock = controller.buildTrainer(controller, "Dock");
        Trainer tomm = controller.buildTrainer(controller, "Tomm");

        System.out.println(dock.statsToString());
        System.out.println(tomm.statsToString());

        // Form bonds
        CodeAMon wale = dock.formBond(wildWale);
        System.out.println(dock.listMonsters());
        System.out.println(wale.listSkills());

        CodeAMon kaht = tomm.formBond(wildKaht);
        System.out.println(tomm.listMonsters());
        System.out.println(kaht.listSkills());
    }

    /**
     * This class builds a new trainer and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name String: Name of trainer
     * @return ConcreteProductTrainer
     */
    public Trainer buildTrainer(Player controller, String name) {
        AbstractTrainer trainer = controller.getAbstractTrainer();
        trainer.setName(name);

        Random random = new Random();

        trainer.setHP(random.nextInt(30) + 1);
        trainer.setMP(random.nextInt(15) + 1);

        return trainer.getTrainer();
    }

    /**
     * This class build a new monster and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name String: Name of monster
     * @return ConcreteProductMonster
     */
    public Monster buildMonster(Player controller, String name) {
        AbstractMonster monster = controller.getAbstractMonster();
        monster.setName(name);
        monster.setLvl(1);


        // Base level HP/MP--will vary based on code-a-mon type and evolution
        // FIXME: Right now all code-a-mon are typeless--use decorator to make typefull
        // TODO: Once typefull, adjust code-a-mon hp/mp from base mp
        // TODO: Use decorator to add skills to a particular code-a-mon
        Random random = new Random();
//        monster.setHP((int) ((Math.random() * 99) + 30));
        monster.setHp(random.nextInt(99) + 30);
//        monster.setMP((int) ((Math.random() * 30) + 15));
        monster.setMp(random.nextInt(30) + 15);


        // FIXME: Move the following code somewhere else
//        int lvl = monster.getLvl();
//        if(lvl < 10) {
//            monster.setHP((int) ((Math.random() * 99) + 30));
//            monster.setMP((int) ((Math.random() * 30) + 15));
//
//        } else if (lvl < 20) {
//            monster.setHP((int) ((Math.random() * 198) + 30));
//            monster.setMP((int) ((Math.random() * 60) + 15));
//
//        } else if (lvl < 30) {
//            monster.setHP((int) ((Math.random() * 396) + 30));
//            monster.setMP((int) ((Math.random() * 90) + 15));
//
//        } else if (lvl < 40) {
//            monster.setHP((int) ((Math.random() * 792) + 30));
//            monster.setMP((int) ((Math.random() * 120) + 15));
//
//        } else if (lvl < 50) {
//            monster.setHP((int) ((Math.random() * 1584) + 30));
//            monster.setMP((int) ((Math.random() * 150) + 15));
//
//        } else if (lvl < 60) {
//            monster.setHP((int) ((Math.random() * 3168) + 30));
//            monster.setMP((int) ((Math.random() * 180) + 15));
//
//        } else if (lvl < 70) {
//            monster.setHP((int) ((Math.random() * 6336) + 30));
//            monster.setMP((int) ((Math.random() * 210) + 15));
//
//        } else if (lvl < 80) {
//            monster.setHP((int) ((Math.random() * 9999) + 30));
//            monster.setMP((int) ((Math.random() * 240) + 15));
//
//        } else if (lvl < 90) {
//            monster.setHP((int) ((Math.random() * 9999) + 30));
//            monster.setMP((int) ((Math.random() * 280) + 15));
//
//        } else if (lvl < 100) {
//            monster.setHP((int) ((Math.random() * 9999) + 30));
//            monster.setMP((int) ((Math.random() * 310) + 15));
//        }

        return (Monster) monster;
    }

    /**
     * Print team data.
     *
     * @param trainer Trainer
     */
    public void printTeamData(Trainer trainer) {
        // Print trainer data
        trainer.statsToString();
        System.out.println(trainer.listMonsters());
    }
}