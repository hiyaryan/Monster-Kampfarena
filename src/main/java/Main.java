import factory.product.Monster;
import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;
import singleton.Player;

import java.util.Random;

/**
 * Main (Main.java)
 *
 * This runs the simulation. Main is simply a central location for the program to run. It's purpose is to initialize
 * entities and the simulated world. The Player class is the class that performs the actions. All if it's actions are
 * undetermined it uses randomized logic. The Mediator class, mediates the world. It keeps track of time, sets the
 * weather, and mediates between day and night. Actions vary depending on the time, day or night, or by the game hour
 * (t)--that occurs every 16 real-world seconds--and by the weather.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Main {
    static Player player;
    static Mediator mediator;

    /**
     * Entry to program.
     *
     * @param args String
     * @throws InterruptedException Thread.sleep
     */
    public static void main(String[] args) throws InterruptedException {
        player = Player.getPlayer();
        mediator = WildeLandMediator.getMediator();

        initializeTheWildeLand();
        startSimulation();

        player.printTeamData(player.getTrainers().get("Dock"));
        player.printTeamData(player.getTrainers().get("Tomm"));

        // After the code above initializes the entities and starts the simulation the following code is where the
        // world logic plays out. This is where the Player class tries to make decisions constrained to the time of day.
        while (!mediator.getDate().contains("7d")) {
            mediator.printWildeLandTime();

            // Randomly select a trainer to perform an action
            Trainer trainer = null;
            int compare = new Random().nextInt(player.getTrainers().size());
            int i = 0;
            for(String name : player.getTrainers().keySet()) {
                if(i == compare) {
                    trainer = player.getTrainers().get(name);
                }
                i++;
            }

            int op = new Random().nextInt(4) + 1;
            switch (op) {
                case 1:
                    tryToRegisterAtTheKampfarena();
                    break;
                case 2:
                    tameMonsters(trainer);
                    break;
                case 3:
                    sleep(trainer);
                default:
                    assert trainer != null;
                    System.out.println("\n" + trainer.getName() + " is not sure what to do.\n");
                    break;
            }

            Thread.sleep(new Random().nextInt(8000) + 4000);

            // The simulation ends when only one Trainer is standing.
            if (player.getTrainers().size() == 1) {
                break;
            }
        }

        if (player.getTrainers().size() == 1) {
            System.out.println("\nWelcome to the Wilde Land our newest Champion!");
            for (String champion : player.getTrainers().keySet()) {
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

    /**
     * This method builds the trainers and monsters using the Abstract Factory design pattern in the factory package.
     */
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
        mediator.printWildeLandTime();

        // Explore the Wilde Land as Dock
        player.exploreTheWildeLand("Dock");

        // Pause for narration
        Thread.sleep(10000);
        mediator.printWildeLandTime();

        // Explore the Wilde Land as Tomm
        player.exploreTheWildeLand("Tomm");

        // Pause for narration
        Thread.sleep(2000);
        mediator.printWildeLandTime();

        // Pause for narration
        Thread.sleep(8000);
    }

    /**
     * Option 1 of the switch statement in main. The trainers try to register at the Kampfarena.
     */
    private static void tryToRegisterAtTheKampfarena() throws InterruptedException {
        // The Kampfarena officially opens on 1d:1t:0c
        if (mediator.getDate().contains("0d")) {
            player.registerTrainers();

            // Day and night events occur here.
        } else {
            // If it's between the hours of 3t-0t the Kampfarena will refuse to accept registrations. The logic
            // takes place inside the Kampfarena class.
            player.registerTrainers();
            player.startBattle();
        }
    }

    /**
     * Option 2 of the switch statement in main. The trainers try to tame new monsters. The only times
     * available to tame monsters is from 1t-3t. If they try to tame at 0t they will be blocked.
     */
    private static void tameMonsters(Trainer trainer) throws InterruptedException {
        if(mediator.getDate().contains("1t") || mediator.getDate().contains("2t")
                || mediator.getDate().contains("3t")) {

            player.exploreTheWildeLand(trainer.getName());

        } else {
            System.out.println("\nIt is too dangerous to go out at this time.\n");
        }
    }

    /**
     * Option 3 of the switch statement in main. The trainers sleep and recover hp and mp. The time
     * trainers may sleep is from 3t-0t. The duration of a sleeping session is 2 cycles.
     */
    private static void sleep(Trainer trainer) throws InterruptedException {
        // The Kampfarena officially opens on 1d:1t:0c
        if (mediator.getDate().contains("3d") || mediator.getDate().contains("0d")) {

            System.out.println("\n" + trainer.getName() + " is going to sleep.\n");

            for(int i = 0; i < 2; i++) {
                player.healHp(trainer);
                player.healMp(trainer);

                Thread.sleep(1000);
            }

            System.out.println("\n" + trainer.getName() + " feels refreshed!\n");

            System.out.println(trainer.statsToStringCompact());
            System.out.println(trainer.listMonstersCompact());

        } else {
            System.out.println("\nNow is not the time to be lounging around.\n");
        }
    }
}