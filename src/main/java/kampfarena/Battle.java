package kampfarena;

import factory.product.Trainer;
import singleton.Player;

import java.util.Queue;

public class Battle {
Player controller = Player.getController();
private Trainer trainer1;
private Trainer trainer2;

    public Battle(Queue<Registration> registry) {
        this.trainer1 = registry.remove().getTrainer();
        this.trainer2 = registry.remove().getTrainer();

        System.out.println("   " + trainer1.getName().toUpperCase() + " vs " + trainer2.getName().toUpperCase());
    }

    public void battle() {
        // TODO: This is where the fight occurs, it uses the Player controller to randomly select a menu option
        System.out.println(controller.getMenuSelection(trainer1));
        System.out.println(controller.getMenuSelection(trainer2));
    }
}
