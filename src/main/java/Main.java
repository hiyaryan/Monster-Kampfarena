import factory.*;

/**
 * Main (Main.java)
 *
 * This runs the simulation.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Main {
    // Initialize a new static player
    public static Player controller = new Player();

    public static void main(String[] args) {
        // Build trainer1 and monster1
        ConcreteProductTrainer trainer1 = controller.buildTrainer(controller, "Dock");
        ConcreteProductMonster monster1 = controller.buildMonster(controller, "Whaler");

        trainer1.setCodex(monster1.getName(), monster1.getMonster());
        System.out.println(trainer1.getStats());


        // Build trainer2 and monster2
        ConcreteProductTrainer trainer2 = controller.buildTrainer(controller, "Tomm");
        ConcreteProductMonster monster2 = controller.buildMonster(controller, "Kat");

        trainer2.setCodex(monster2.getName(), monster2.getMonster());
        System.out.println(trainer2.getStats());
    }
}