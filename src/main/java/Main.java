import decorator.monster.CodeAMon;
import factory.product.Monster;
import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;

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
        startSimulation();

        System.exit(0);
        // TODO: Set up registration
    }

    public static void initializeTheWildeLand()
            throws InterruptedException {
        System.out.println("\nInitializing world...");

        System.out.println("   Building trainers...");

        // Build trainers
        Trainer dock = controller.buildTrainer(controller, "Dock");
        Trainer tomm = controller.buildTrainer(controller, "Tomm");

        controller.addTrainer(dock);
        controller.addTrainer(tomm);

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
        Monster wildGith = controller.buildMonster(controller, "Gith");
        Monster wildServ = controller.buildMonster(controller, "Serv");
        Monster wildClie = controller.buildMonster(controller, "Clie");
        Monster wildDesi = controller.buildMonster(controller, "Desi");
        Monster wildExml = controller.buildMonster(controller, "Exml");
        Monster wildAdle = controller.buildMonster(controller, "Adle");

        System.out.println(wildWale.statsToString());
        System.out.println(wildKaht.statsToString());
        System.out.println(wildPyth.statsToString());
        System.out.println(wildJaxx.statsToString());
        System.out.println(wildCoco.statsToString());
        System.out.println(wildAntt.statsToString());
        System.out.println(wildGith.statsToString());
        System.out.println(wildServ.statsToString());
        System.out.println(wildClie.statsToString());
        System.out.println(wildDesi.statsToString());
        System.out.println(wildExml.statsToString());
        System.out.println(wildAdle.statsToString());

        controller.addMonster(wildAdle);
        controller.addMonster(wildKaht);
        controller.addMonster(wildPyth);
        controller.addMonster(wildJaxx);
        controller.addMonster(wildCoco);
        controller.addMonster(wildAntt);
        controller.addMonster(wildGith);
        controller.addMonster(wildServ);
        controller.addMonster(wildClie);
        controller.addMonster(wildDesi);
        controller.addMonster(wildExml);
        controller.addMonster(wildAdle);
    }

    public static void startSimulation() throws InterruptedException {
        // Start the clock
        Thread tick = new Thread((Runnable) mediator);
        tick.start();

        // Pause for narration
        Thread.sleep(10000);
        printWildeLandTime();

        // Form bonds
        try {
            // Trainer indexes who are Code-a-mon in their CODEX's
            CodeAMon wale = controller
                    .getTrainers().get("Dock")
                    .formBond(controller.getMonsters().get("Wale"));

            System.out.println(controller
                    .getTrainers().get("Dock").listMonsters());

            System.out.println(wale.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(controller
                    .getTrainers().get("Dock").getName() + "'s Codex is full.");
        }

        // Pause for narration
        Thread.sleep(10000);
        printWildeLandTime();

        try {
            CodeAMon kaht = controller
                    .getTrainers().get("Tomm")
                    .formBond(controller.getMonsters().get("Kaht"));

            System.out.println(controller
                    .getTrainers().get("Tomm").listMonsters());

            System.out.println(kaht.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(controller
                    .getTrainers().get("Tomm").getName() + "'s Codex is full.");
        }

        // Pause for narration
        Thread.sleep(2000);
        printWildeLandTime();

        // Pause for narration
        Thread.sleep(8000);
    }

    public static void printWildeLandTime() {
        System.out.println("Time:\n   [" + mediator.getWildeLand().whatTimeIsIt() + "]\n");
    }
}