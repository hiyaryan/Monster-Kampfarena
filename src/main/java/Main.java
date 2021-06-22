import decorator.CodeAMon;
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
        Player controller = Player.getController();
        initializeTheWildeLand(controller);
    }

    public static void initializeTheWildeLand(Player controller) {
        System.out.println("Initializing world...\n");

        // Build monsters
        Monster wildWale = controller.buildMonster(controller, "Wale");
        Monster wildKaht = controller.buildMonster(controller, "Kaht");
        Monster wildPyth = controller.buildMonster(controller, "Pyth");
        Monster wildJaxx = controller.buildMonster(controller, "Jaxx");
        Monster wildCoco = controller.buildMonster(controller, "Coco");
        Monster wildAntt = controller.buildMonster(controller, "Antt");

        // Build trainers
        Trainer dock = controller.buildTrainer(controller, "Dock");
        Trainer tomm = controller.buildTrainer(controller, "Tomm");

        System.out.println(dock.statsToString());
        System.out.println(tomm.statsToString());

        // Form bonds
        CodeAMon wale = dock.formBond(wildWale);
        System.out.println(dock.listMonsters());
        System.out.println(wale.listSkills());

        CodeAMon kaht = tomm.formBond(wildKaht);
        System.out.println(tomm.listMonsters());
        System.out.println(kaht.listSkills());

        System.out.println("Registration at Kampfarena is now open!");
        // TODO: Set up registration
    }
}