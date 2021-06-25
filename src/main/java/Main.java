import decorator.monster.CodeAMon;
import factory.product.Monster;
import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;
import singleton.Player;

import java.util.Random;

/**
 * Main (Main.java) This runs the simulation.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Main {
    static Player player;
    static Mediator mediator;

    public static void main(String[] args) throws InterruptedException {
        player = Player.getController();
        mediator = WildeLandMediator.getMediator();

        initializeTheWildeLand();
        startSimulation();

        player.printTeamData(player.getTrainers().get("Dock"));
        player.printTeamData(player.getTrainers().get("Tomm"));

        while(!mediator.getDate().contains("7d")) {

            // Register for battle then start
            if (mediator.getDate().contains("0d")) {
                Thread.sleep(new Random().nextInt(8000) + 4000);
                player.registerTrainers();

            } else {
                Thread.sleep(new Random().nextInt(8000) + 4000);
                player.registerTrainers();
                player.startBattle();
            }

            if(player.getTrainers().size() == 1) {
                break;
            }
        }

        if(player.getTrainers().size() == 1) {
            System.out.println("\nWelcome to the Wilde Land our newest Champion!");
            for(String champion : player.getTrainers().keySet()) {
                System.out.println("   " + champion.toUpperCase() + "!\n");
            }
        }

        Thread.sleep(6000);
        System.out.println("Thank you very much for your attendance.");
        System.out.println("   Everyone, please welcome our newest Trainer!\n");

        Thread.sleep(6000);
        System.out.println("\n\nYou.\n");

        Thread.sleep(6000);
        System.exit(0);
    }

    public static void initializeTheWildeLand() {
        System.out.println("Initializing world.");

        System.out.println("   Building trainers...\n");

        // Build trainers
        Trainer dock = player.buildTrainer(player, "Dock");
        Trainer tomm = player.buildTrainer(player, "Tomm");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        System.out.println(dock.statsToString());
        System.out.println(tomm.statsToString());

        System.out.println("   Building monsters...\n");

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale");
        Monster wildKaht = player.buildMonster(player, "Kaht");
        Monster wildPyth = player.buildMonster(player, "Pyth");
        Monster wildJaxx = player.buildMonster(player, "Jaxx");
        Monster wildCoco = player.buildMonster(player, "Coco");
        Monster wildAntt = player.buildMonster(player, "Antt");
        Monster wildGith = player.buildMonster(player, "Gith");
        Monster wildServ = player.buildMonster(player, "Serv");
        Monster wildClie = player.buildMonster(player, "Clie");
        Monster wildDesi = player.buildMonster(player, "Desi");
        Monster wildExml = player.buildMonster(player, "Exml");
        Monster wildAdle = player.buildMonster(player, "Adle");

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

        player.addMonster(wildWale);
        player.addMonster(wildKaht);
        player.addMonster(wildPyth);
        player.addMonster(wildJaxx);
        player.addMonster(wildCoco);
        player.addMonster(wildAntt);
        player.addMonster(wildGith);
        player.addMonster(wildServ);
        player.addMonster(wildClie);
        player.addMonster(wildDesi);
        player.addMonster(wildExml);
        player.addMonster(wildAdle);
    }

    /**
     * This method initializes trainers with their first Code-a-mon.
     *
     * @throws InterruptedException Thread.sleep
     */
    public static void startSimulation() throws InterruptedException {
        // Start the clock
        Thread tick = new Thread((Runnable) mediator);
        tick.start();

        // Pause for narration
        Thread.sleep(10000);
        printWildeLandTime();

        // Form bonds
        int lottery = 0;
        int counter = mediator.getCounter();
        while((mediator.getCounter() - counter) != 1) {
            lottery = new Random().nextInt(12);

            try {
                // Trainer indexes who are Code-a-mon in their CODEX's
                // Initial Code-a-mon a chosen using a lottery system
                String name = "";
                int i = 0;
                for(String n : player.getMonsters().keySet()) {
                    if(i == lottery) {
                        name = n;
                    }
                    i++;
                }

                CodeAMon codeAMon = player
                        .getTrainers().get("Dock")
                        .formBond(player.getMonsters().get(name));
                System.out.println(codeAMon.listSkills());

                Thread.sleep(lottery * 100);
            } catch (NullPointerException npe) {
                System.out.println(player
                        .getTrainers().get("Dock").getName() + "'s Codex is full.\n");
            }
        }

        System.out.println(player
                .getTrainers().get("Dock").listMonsters());

        // Pause for narration
        Thread.sleep(10000);
        printWildeLandTime();

        counter = mediator.getCounter();
        while((mediator.getCounter() - counter) != 1) {
            lottery = new Random().nextInt(12);

            try {
                // Trainer indexes who are Code-a-mon in their CODEX's
                // Initial Code-a-mon a chosen using a lottery system
                String name = "";
                int i = 0;
                for(String n : player.getMonsters().keySet()) {
                    if(i == lottery) {
                        name = n;
                    }
                    i++;
                }

                CodeAMon codeAMon = player
                        .getTrainers().get("Tomm")
                        .formBond(player.getMonsters().get(name));
                System.out.println(codeAMon.listSkills());

                Thread.sleep(lottery * 100);
            } catch (NullPointerException npe) {
                System.out.println(player
                        .getTrainers().get("Tomm").getName() + "'s Codex is full.\n");
            }
        }

        System.out.println(player
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