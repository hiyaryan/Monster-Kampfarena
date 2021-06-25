package kampfarena;

import decorator.monster.CodeAMon;
import factory.product.Trainer;
import mediator.WildeLandMediator;
import singleton.Player;
import java.util.Queue;

public class Battle {
Player player = Player.getController();
WildeLandMediator mediator = WildeLandMediator.getMediator();
private Trainer trainer1;
private Trainer trainer2;

    public Battle(Queue<Registration> registry) {
        this.trainer1 = registry.remove().getTrainer();
        this.trainer2 = registry.remove().getTrainer();

        System.out.println("   " + trainer1.getName().toUpperCase() + " vs " + trainer2.getName().toUpperCase());
    }

    public void battle() throws InterruptedException {
        while (!mediator.getDate().contains("3t")) {
            Object trainer1Selection = player.getMenuSelection(trainer1);
            System.out.println(trainer1.getName() + " is up!\n");

            Thread.sleep(2000);
            if (trainer1Selection instanceof String) {
                System.out.println("   " + trainer1.getName() + " is going for an " + ((String) trainer1Selection) + "!\n");

            } else {
                System.out.println("   Go " + ((CodeAMon) trainer1Selection).getMonster().getName() + "!\n");
            }


            Object trainer2Selection = player.getMenuSelection(trainer2);
            System.out.println(trainer2.getName() + " is up!");

            Thread.sleep(2000);
            if (trainer2Selection instanceof String) {
                System.out.println("   " + trainer2.getName() + " is going for an " + ((String) trainer2Selection) + "!\n");

            } else {
                System.out.println("   Go " + ((CodeAMon) trainer2Selection).getMonster().getName() + "!\n");

            }
        }
    }
}
