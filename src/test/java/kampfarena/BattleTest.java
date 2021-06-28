package kampfarena;

import factory.product.Monster;
import factory.product.Trainer;
import mediator.WildeLandMediator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import singleton.Player;

import java.util.HashMap;

public class BattleTest {
    private static final Player player = Player.getPlayer();
    private static final WildeLandMediator mediator = WildeLandMediator.getMediator();
    private static final Kampfarena kampfarena = Kampfarena.getKampfarena();
    private Trainer dock;
    private Trainer tomm;

    /**
     * Setup a new monster and trainer for every unit test.
     */
    @Before
    public void setUp() throws InterruptedException {
        System.out.println("\n===============================");
        System.out.println("    Battle.java Test Suite");
        System.out.println("===============================");

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale");
        Monster wildKaht = player.buildMonster(player, "Kaht");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        // Start the clock
        Thread tick = new Thread(mediator);
        tick.start();
    }

    /**
     * Set class attributes to null after every test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Test register for battle with two trainers.
     */
    @Test
    public void testRegisterForBattleWithTwoTrainers() throws InterruptedException {
        System.out.println("\n+TEST: testRegisterForBattle\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock");
        tomm = player.buildTrainer(player, "Tomm");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        player.getTrainers().get("Dock").formBond(player.getMonsters().get("Wale"));
        player.getTrainers().get("Tomm").formBond(player.getMonsters().get("Kaht"));

        while (kampfarena.getRegistry().size() == 0) {
            kampfarena.registerForBattle(player.getTrainers());
            Thread.sleep(1000);
        }

        System.out.println("\nExpected: true");
        System.out.println("Actual: " + (kampfarena.getRegistry().size() > 0) + "\n");

        Assert.assertTrue(kampfarena.getRegistry().size() > 0);
    }

    /**
     * Test register for battle with two trainers repeated.
     */
    @Test
    public void testRegisterForBattleWithTwoTrainersRepeated() throws InterruptedException {
        System.out.println("\n+TEST: testRegisterForBattle\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock");
        tomm = player.buildTrainer(player, "Tomm");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        player.getTrainers().get("Dock").formBond(player.getMonsters().get("Wale"));
        player.getTrainers().get("Tomm").formBond(player.getMonsters().get("Kaht"));

        kampfarena.registerForBattle(player.getTrainers());

        while (kampfarena.getRegistry().size() == 0) {
            Thread.sleep(1000);
        }

        HashMap<String, Boolean> alreadyRegistered = kampfarena.registerForBattle(player.getTrainers());

        System.out.println("\nExpected: 2");
        System.out.println("Actual: " + alreadyRegistered.size() + "\n");

        Assert.assertEquals(2, alreadyRegistered.size());
    }

    /**
     * Test register for battle while closed.
     */
    @Test
    public void testRegisterForBattleWhileClosed() throws InterruptedException {
        System.out.println("\n+TEST: testRegisterForBattleWhileClosed\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock");
        tomm = player.buildTrainer(player, "Tomm");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        player.getTrainers().get("Dock").formBond(player.getMonsters().get("Wale"));
        player.getTrainers().get("Tomm").formBond(player.getMonsters().get("Kaht"));

        HashMap<String, Boolean> nullHashMap = null;

        while (kampfarena.getRegistry().size() == 0) {
            while (mediator.getDate().contains("1t") || mediator.getDate().contains("2t")) {
                Thread.sleep(8000);

                nullHashMap = kampfarena.registerForBattle(player.getTrainers());
            }
        }

        System.out.println("\nExpected: null");
        System.out.println("Actual: " + nullHashMap + "\n");

        Assert.assertNull(nullHashMap);
    }

    /**
     * Test initiate battle during closed hours.
     */
    @Test
    public void testInitiateBattleDuringClosedHours() throws InterruptedException {
        System.out.println("\n+TEST: testInitiateBattleDuringClosedHours\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock");
        tomm = player.buildTrainer(player, "Tomm");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        player.getTrainers().get("Dock").formBond(player.getMonsters().get("Wale"));
        player.getTrainers().get("Tomm").formBond(player.getMonsters().get("Kaht"));

        kampfarena.registerForBattle(player.getTrainers());

        while (mediator.getDate().contains("1t") || mediator.getDate().contains("2t")) {
            Thread.sleep(8000);
        }

        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
    }

    /**
     * Test initiate battle with less than two trainers.
     */
    @Test
    public void testInitiateBattleWithLessThanTwoTrainers() throws InterruptedException {
        System.out.println("\n+TEST: testInitiateBattleWithLessThanTwoTrainers\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock");

        player.addTrainer(dock);

        player.getTrainers().get("Dock").formBond(player.getMonsters().get("Wale"));

        if (kampfarena.getRegistry().size() == 0) {
            kampfarena.registerForBattle(player.getTrainers());

        } else {
            while (kampfarena.getRegistry().size() > 1) {
                kampfarena.initiateBattle();
            }

            kampfarena.registerForBattle(player.getTrainers());
        }

        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
    }


}
