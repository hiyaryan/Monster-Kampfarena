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

//        System.exit(0);
        // TODO: Set up registration
    }

    public static void initializeTheWildeLand()
            throws InterruptedException {
        System.out.println("Initializing world.");

        System.out.println("   Building trainers...\n");

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

        controller.addMonster(wildWale);
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

    /**
     * This method initializes trainers with their first Code-a-mon.
     *
     * @throws InterruptedException
     */
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
            System.out.println(wale.listSkills());

            // Tame maximum monsters
//            CodeAMon pyth = controller
//                    .getTrainers().get("Dock")
//                    .formBond(controller.getMonsters().get("Pyth"));
//            System.out.println(pyth.listSkills());
//
//            CodeAMon coco = controller
//                    .getTrainers().get("Dock")
//                    .formBond(controller.getMonsters().get("Coco"));
//            System.out.println(coco.listSkills());
//
//            CodeAMon clie = controller
//                    .getTrainers().get("Dock")
//                    .formBond(controller.getMonsters().get("Clie"));
//            System.out.println(clie.listSkills());
//
//            CodeAMon exml = controller
//                    .getTrainers().get("Dock")
//                    .formBond(controller.getMonsters().get("Exml"));
//            System.out.println(exml.listSkills());
//
//            CodeAMon desi = controller
//                    .getTrainers().get("Dock")
//                    .formBond(controller.getMonsters().get("Desi"));
//            System.out.println(desi.listSkills());
//
//            CodeAMon kaht = controller
//                    .getTrainers().get("Dock")
//                    .formBond(controller.getMonsters().get("Kaht"));
//            System.out.println(kaht.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(controller
                    .getTrainers().get("Dock").getName() + "'s Codex is full.\n");
        }

        System.out.println(controller
                .getTrainers().get("Dock").listMonsters());

        // Pause for narration
        Thread.sleep(10000);
        printWildeLandTime();

        try {
            CodeAMon kaht = controller
                    .getTrainers().get("Tomm")
                    .formBond(controller.getMonsters().get("Kaht"));
            System.out.println(kaht.listSkills());

            // Tame maximum monsters
//            CodeAMon jaxx = controller
//                    .getTrainers().get("Tomm")
//                    .formBond(controller.getMonsters().get("Jaxx"));
//            System.out.println(jaxx.listSkills());
//
//            CodeAMon antt = controller
//                    .getTrainers().get("Tomm")
//                    .formBond(controller.getMonsters().get("Antt"));
//            System.out.println(antt.listSkills());
//
//            CodeAMon gith = controller
//                    .getTrainers().get("Tomm")
//                    .formBond(controller.getMonsters().get("Gith"));
//            System.out.println(gith.listSkills());
//
//            CodeAMon adle = controller
//                    .getTrainers().get("Tomm")
//                    .formBond(controller.getMonsters().get("Adle"));
//            System.out.println(adle.listSkills());
//
//            CodeAMon serv = controller
//                    .getTrainers().get("Tomm")
//                    .formBond(controller.getMonsters().get("Serv"));
//            System.out.println(serv.listSkills());

        } catch (NullPointerException npe) {
            System.out.println(controller
                    .getTrainers().get("Tomm").getName() + "'s Codex is full.\n");
        }

        System.out.println(controller
                .getTrainers().get("Tomm").listMonsters());

        // Pause for narration
        Thread.sleep(2000);
        printWildeLandTime();

        // Pause for narration
        Thread.sleep(8000);
    }

    /**
     * This method prints the game time in the Wilde Land
     * Example: [Time: Day 0d:1t:9c]
     */
    public static void printWildeLandTime() {
        System.out.println("[Time: " + mediator.getWildeLand().whatTimeIsIt() + "]\n\n");
    }
}