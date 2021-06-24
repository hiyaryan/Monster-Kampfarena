import decorator.monster.CodeAMon;
import factory.product.Monster;
import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;
import singleton.Player;

/**
 * Main (Main.java) This runs the simulation.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Main {
    static Player controller;
    static Mediator mediator;

    public static void main(String[] args) throws InterruptedException {
        controller = Player.getController();
        mediator = WildeLandMediator.getMediator();

        initializeTheWildeLand();
        // TODO: Set up registration


        System.exit(0);
    }

    public static void initializeTheWildeLand()
            throws InterruptedException {
        System.out.println("\nInitializing world...\n");

        // Start the clock
        Thread tick = new Thread((Runnable) mediator);
        tick.start();

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

        // Pause for narration
        Thread.sleep(10000);
        System.out.println("[" + mediator.getWildeLand().whatTimeIsIt(mediator.getDate()) + "]");
        System.out.println();

        // Form bonds
        try {
            CodeAMon wale = dock.formBond(wildWale);
            System.out.println(dock.listMonsters());
            System.out.println(wale.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(dock.getName() + "'s Codex is full.");
        }

        // Pause for narration
        Thread.sleep(10000);
        System.out.println("[" + mediator.getWildeLand().whatTimeIsIt(mediator.getDate()) + "]");
        System.out.println();

        try {
            CodeAMon kaht = tomm.formBond(wildKaht);
            System.out.println(tomm.listMonsters());
            System.out.println(kaht.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(dock.getName() + "'s Codex is full.");
        }

        // Pause for narration
        Thread.sleep(2000);
        System.out.println("[" + mediator.getWildeLand().whatTimeIsIt(mediator.getDate()) + "]");
        System.out.println();

        // Pause for narration
        Thread.sleep(8000);
    }
}