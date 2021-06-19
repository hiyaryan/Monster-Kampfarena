import factory.*;

/**
 * Main (Main.java)
 *
 * This class is acting as the client. Contains main which runs the simulation.
 *
 *
 *
 */
public class Main {
    // Initialize a new static player
    public static Player controller = new Player();

    public static void main(String[] args) {
        // Build trainer1 and monster1
        ConcreteProductTrainer trainer1 = controller.buildTrainer(controller, "Dock");
        ConcreteProductMonster monster1 = controller.buildMonster(controller, "Whaler");

        trainer1.setCodex(monster1.getName(), monster1.getMonster());
        System.out.println(trainer1.listMonsters());

        // Build trainer2 and monster2
        ConcreteProductTrainer trainer2 = controller.buildTrainer(controller, "Tom");
        ConcreteProductMonster monster2 = controller.buildMonster(controller, "Kat");

        trainer2.setCodex(monster2.getName(), monster2.getMonster());
        System.out.println(trainer2.listMonsters());

        trainer1.setCodex(monster2.getName(), monster2.getMonster());
        System.out.println(trainer1.listMonsters());
    }
}