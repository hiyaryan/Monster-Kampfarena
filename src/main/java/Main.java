import decorator.CodeAMon;
import decorator.Initial;
import factory.Monster;
import factory.Trainer;
import singleton.Player;

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
        Trainer dock = controller.buildTrainer(controller, "Dock");
        Trainer tomm = controller.buildTrainer(controller, "Tomm");

        // Build monsters
        Monster wildWale = controller.buildMonster(controller, "Wale");
        CodeAMon wale = new Initial(wildWale);
        wale.operation();

        Monster wildKaht = controller.buildMonster(controller, "Kaht");
        CodeAMon kaht = new Initial(wildKaht);
        kaht.operation();

        // Build team 1
        setCodex(dock, wale);
        printTeamData(dock, wale);

        // Build team 2
        setCodex(tomm, kaht);
        printTeamData(tomm, kaht);
    }

    /**
     * Set trainer CODEX.
     *
     * @param trainer ConcreteProductTrainer
     * @param codeAMon ConcreteProductMonster
     */
    public static void setCodex(Trainer trainer, CodeAMon codeAMon) {
        trainer.setCodex(codeAMon.getMonster().getName(), codeAMon.getMonster());
    }

    /**
     * Print team data.
     *
     * @param trainer ConcreteProductTrainer
     * @param codeAMon ConcreteProductMonster
     */
    public static void printTeamData(Trainer trainer, CodeAMon codeAMon) {
        // Print trainer data
        System.out.println(trainer.getStats());
        System.out.println(trainer.listMonsters());

        // Print monster data
        System.out.println(trainer.getCodex().get(codeAMon.getMonster().getName()).getStats());
        System.out.println(trainer.getCodex().get(codeAMon.getMonster().getName()).listSkills());
    }
}