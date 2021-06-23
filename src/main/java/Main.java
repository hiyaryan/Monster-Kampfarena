import decorator.CodeAMon;
import factory.Monster;
import factory.Trainer;
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
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        Player controller = Player.getController();
        Mediator mediator = WildeLandMediator.getMediator();
        initializeTheWildeLand(controller, mediator);
    }

    public static void initializeTheWildeLand(Player controller, Mediator mediator)
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

        Thread.sleep(10000);

        // Form bonds
        try {
            CodeAMon wale = dock.formBond(wildWale);
            System.out.println(dock.listMonsters());
            System.out.println(wale.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(dock.getName() + "'s Codex is full.");
        }

        Thread.sleep(10000);

        try {
            CodeAMon kaht = tomm.formBond(wildKaht);
            System.out.println(tomm.listMonsters());
            System.out.println(kaht.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(dock.getName() + "'s Codex is full.");
        }

        Thread.sleep(2000);

        // TODO: Set up registration
        System.out.println(mediator.getWorld().whatTimeIsIt(mediator.getDate()));
//        mediator.getWorld().whatTimeIsIt(mediator.getDate());
    }
}