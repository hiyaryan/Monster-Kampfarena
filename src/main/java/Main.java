import singleton.Player;
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
    public static void main(String[] args) {
        // Instantiate Player singleton
        Player controller = Player.getController();

        // Build trainers
        ConcreteProductTrainer dock = controller.buildTrainer(controller, "Dock");
        ConcreteProductTrainer tomm = controller.buildTrainer(controller, "Tomm");

        // Build monsters
        ConcreteProductMonster wale = controller.buildMonster(controller, "Wale");
        ConcreteProductMonster kaht = controller.buildMonster(controller, "Kaht");

        // Build team 1
        setCodex(dock, wale);
        printTeamData(dock, wale);

        // Build team 2
        setCodex(tomm, kaht);
        printTeamData(tomm, kaht);

        // Decorate monster
    }

    /**
     * Set trainer CODEX.
     *
     * @param trainer ConcreteProductTrainer
     * @param monster ConcreteProductMonster
     */
    public static void setCodex(ConcreteProductTrainer trainer, ConcreteProductMonster monster) {
        trainer.setCodex(monster.getName(), monster.getMonster());
    }

    /**
     * Print team data.
     *
     * @param trainer ConcreteProductTrainer
     * @param monster ConcreteProductMonster
     */
    public static void printTeamData(ConcreteProductTrainer trainer, ConcreteProductMonster monster) {
        // Print trainer data
        System.out.println(trainer.getStats());
        System.out.println(trainer.listMonsters());

        // Print monster data
        System.out.println(trainer.getCodex().get(monster.getName()).getStats());
        System.out.println(trainer.getCodex().get(monster.getName()).listSkills());
    }
}