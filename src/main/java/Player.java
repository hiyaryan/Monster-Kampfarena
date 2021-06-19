import factory.*;

/**
 * Player (Player.java)
 *
 * This class simulates a player.
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
     *
     * @param controller
     * @param name
     * @return
     */
    public ConcreteProductTrainer buildTrainer(Player controller, String name) {
        AbstractProductTrainer trainer = controller.getAbstractTrainer();

        trainer.setName(name);

        return trainer.getTrainer();
    }

    /**
     *
     * @param controller
     * @param name
     * @return
     */
    public ConcreteProductMonster buildMonster(Player controller, String name) {
        AbstractProductMonster monster = controller.getAbstractMonster();

        monster.setName(name);

        return monster.getMonster();
    }
}