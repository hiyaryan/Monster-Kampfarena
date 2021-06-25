package kampfarena;

import decorator.monster.CodeAMon;
import factory.product.Trainer;
import mediator.WildeLandMediator;
import singleton.Player;
import java.util.Queue;
import java.util.Random;

public class Battle {
Player player = Player.getController();
WildeLandMediator mediator = WildeLandMediator.getMediator();
private Trainer trainer1;
private Trainer trainer2;

    public Battle(Queue<Registration> registry) {
        this.trainer1 = registry.remove().getTrainer();
        this.trainer2 = registry.remove().getTrainer();

        System.out.println("   " + trainer1.getName().toUpperCase() + " vs " + trainer2.getName().toUpperCase() + "\n");
    }

    public void battle() throws InterruptedException {
        while (!mediator.getDate().contains("3t")) {

            System.out.println(trainer1.getName() + " is up!");
            Thread.sleep(new Random().nextInt(2000) + 2000);
            attack(trainer1);

            System.out.println(trainer2.getName() + " is up!");
            Thread.sleep(new Random().nextInt(2000) + 2000);
            attack(trainer2);
        }

        // If the battle ends due to it being late
        if (mediator.getDate().contains("3t")) {
            System.out.println("The battle could not be resolved...");

            int tomorrow = mediator.getDay() + 1;
            System.out.println("   Join us tomorrow at " + tomorrow + "d:1t:0c, the battle shall continue!\n");
        }
    }

    public void attack(Trainer trainer) throws InterruptedException {
        Object trainerSelection = player.getMenuSelection(trainer);

        if (trainerSelection instanceof String) {
            System.out.println("   " + trainer.getName() + " is going for an " + ((String) trainerSelection) + "!\n");

        } else {
            System.out.println("   Go " + ((CodeAMon) trainerSelection).getMonster().getName() + "!\n");
        }
    }
}
