import factory.*;

/**
 * Player (Player.java)
 *
 * This class simulates the Client as a player.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Player {
    private AbstractFactory factory;

    /**
     * Instantiate a ConcreteFactory.
     */
    public Player() {
        this.factory = new ConcreteFactory();
    }

    /**
     * Create a new trainer.
     *
     * @return trainer
     */
    public AbstractProductTrainer getAbstractTrainer() {
        return factory.createTrainer();
    }

    /**
     * Create a new code-a-mon.
     *
     * @return monster
     */
    public AbstractProductMonster getAbstractMonster() {
        return factory.createMonster();
    }

    /**
     * This class builds a single trainer and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name String: Name of trainer
     * @return ConcreteProductTrainer
     */
    public ConcreteProductTrainer buildTrainer(Player controller, String name) {
        AbstractProductTrainer trainer = controller.getAbstractTrainer();
        trainer.setName(name);

        trainer.setHP((int) (Math.random() * 30));
        trainer.setMP((int) (Math.random() * 15));

        return trainer.getTrainer();
    }

    /**
     * This class build a single monster and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name String: Name of monster
     * @return ConcreteProductMonster
     */
    public ConcreteProductMonster buildMonster(Player controller, String name) {
        AbstractProductMonster monster = controller.getAbstractMonster();
        monster.setName(name);

        return monster.getMonster();
    }
}