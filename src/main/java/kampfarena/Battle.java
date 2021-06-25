package kampfarena;

import decorator.monster.CodeAMon;
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
        int i = 0;
        while (i < 10) {
            Object trainer1Selection = controller.getMenuSelection(trainer1);

            if (trainer1Selection instanceof String) {
                System.out.println((String) trainer1Selection);

            } else {
                System.out.println(((CodeAMon) trainer1Selection).getMonster().getName());
            }


            Object trainer2Selection = controller.getMenuSelection(trainer2);

            if (trainer2Selection instanceof String) {
                System.out.println((String) trainer2Selection);

            } else {
                System.out.println(((CodeAMon) trainer2Selection).getMonster().getName());
            }

            i++;
        }
    }
}
