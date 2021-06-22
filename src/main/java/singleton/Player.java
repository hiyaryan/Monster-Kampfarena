package singleton;

import factory.*;

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
    public AbstractTrainer getAbstractTrainer(String name) {
        return factory.createTrainer(name);
    }

    /**
     * Create a new code-a-mon.
     *
     * @return monster
     */
    public AbstractMonster getAbstractMonster(String name) {
        return factory.createMonster(name);
    }

    /**
     * This class builds a new trainer and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name String: Name of trainer
     * @return ConcreteProductTrainer
     */
    public Trainer buildTrainer(Player controller, String name) {
        AbstractTrainer trainer = controller.getAbstractTrainer(name);
        return (Trainer) trainer;
    }

    /**
     * This class build a new monster and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name String: Name of monster
     * @return ConcreteProductMonster
     */
    public Monster buildMonster(Player controller, String name) {
        AbstractMonster monster = controller.getAbstractMonster(name);

        // Base level HP/MP--will vary based on code-a-mon type and evolution
        // TODO: Once typefull, adjust code-a-mon hp/mp from base mp

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