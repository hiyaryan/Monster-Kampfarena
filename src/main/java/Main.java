import decorator.CodeAMon;
import factory.Monster;
import factory.Trainer;
import singleton.Player;

/**
 * Main (Main.java)
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
        System.out.println("\nInitializing world...\n");

        System.out.println("   Building trainers...\n");

        // Build trainers
        Trainer dock = controller.buildTrainer(controller, "Dock");
        Trainer tomm = controller.buildTrainer(controller, "Tomm");

        System.out.println(dock.statsToString());
        System.out.println(tomm.statsToString());

        System.out.println("   Building monsters...\n");

        // Build monsters
        Monster wildWale = controller.buildMonster(controller, "Wale");
        Monster wildKaht = controller.buildMonster(controller, "Kaht");
        Monster wildPyth = controller.buildMonster(controller, "Pyth");
        Monster wildJaxx = controller.buildMonster(controller, "Jaxx");
        Monster wildCoco = controller.buildMonster(controller, "Coco");
        Monster wildAntt = controller.buildMonster(controller, "Antt");

        System.out.println(wildWale.statsToString());
        System.out.println(wildKaht.statsToString());

        System.out.println("\nWelcome to the Wilde Land!\n\n");

        System.out.println("   Watch the most significant event of a new trainers journey...");
        System.out.println("      the forming of bonds between trainer and monster.\n\n");

        // Form bonds
        try {
            CodeAMon wale = dock.formBond(wildWale);
            System.out.println(dock.listMonsters());
            System.out.println(wale.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(dock.getName() + "'s Codex is full.");
        }

        System.out.println("\n   What a beautiful sight...");
        System.out.println("        A monster awakening into Code-a-mon.\n\n");

        try {
            CodeAMon kaht = tomm.formBond(wildKaht);
            System.out.println(tomm.listMonsters());
            System.out.println(kaht.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(dock.getName() + "'s Codex is full.");
        }

        System.out.println("\n   Hang tight everyone!");
        System.out.println("        You will soon get to witness what you all came here for.\n\n");

        System.out.println("Registration is now open at the Kampfarena!");
        // TODO: Set up registration
    }
}