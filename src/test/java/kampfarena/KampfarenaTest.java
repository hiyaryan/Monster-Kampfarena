package kampfarena;

import factory.product.Monster;
import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import singleton.Player;

import java.util.HashMap;

public class KampfarenaTest {
    Mediator mediator;
    Player player;
    Kampfarena kampfarena;

    Monster monster1;
    Monster monster2;

    Trainer trainer1;
    Trainer trainer2;

    /**
     * Setup a new mediator for every unit test.
     */
    @Before
    public void setUp() {
        System.out.println("\n===============================");
        System.out.println("    Kampfarena.java Test Suite");
        System.out.println("===============================");

        player = Player.getPlayer();

        mediator = WildeLandMediator.getMediator();
        Thread tick = new Thread((Runnable) mediator);
        tick.start();

        kampfarena = Kampfarena.getKampfarena();

        monster1 = player.buildMonster(player, "Monster1");
        monster2 = player.buildMonster(player, "Monster2");

        player.addMonster(monster1);
        player.addMonster(monster2);

        trainer1 = player.buildTrainer(player, "Trainer1");
        trainer2 = player.buildTrainer(player, "Trainer2");

        player.addTrainer(trainer1);
        player.addTrainer(trainer2);

        player.getTrainers().get("Trainer1").formBond(monster1);
        player.getTrainers().get("Trainer2").formBond(monster2);
    }

    /**
     * Set class attributes to null after every test.
     */
    @After
    public void tearDown() {
        if(player.getTrainers().size() == 0) {
            trainer1 = player.buildTrainer(player, "Trainer1");
            trainer2 = player.buildTrainer(player, "Trainer2");

            player.addTrainer(trainer1);
            player.addTrainer(trainer2);
        }
    }

    /**
     * Test Singleton accessibility.
     */
    @Test
    public void testKampfarenaAsASingleton() {
        System.out.println("\n+TEST: testKampfarenaAsASingleton\n");

        System.out.println("\nExpected: !null");
        System.out.println("Actual: " + Kampfarena.getKampfarena() + "\n");

        Assert.assertNotNull(Kampfarena.getKampfarena());
    }

    /**
     * Test constructor attributes.
     */
    @Test
    public void testKampfarenaConstructor() {
        System.out.println("\n+TEST: testKampfarenaConstructor\n");

        System.out.println("\nExpected: size=" + player.getTrainers().size());
        System.out.println("Actual: size=" + kampfarena.getRegistry().size() + "\n");

        System.out.println("Expected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertEquals(player.getTrainers().size(), kampfarena.getRegistry().size());
        Assert.assertFalse(kampfarena.isABattleOngoing());
    }

    /**
     * Test isRegistrationOpen true at 1t. Based on time tracked by the mediator.
     */
    @Test
    public void testIsRegistrationOpenTrue1t() {
        System.out.println("\n+TEST: testIsRegistrationOpenTrue1t\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("1t"));

        System.out.println("\nExpected: true\n");
        System.out.println("Actual: " + kampfarena.isRegistrationOpen() + "\n");

        Assert.assertTrue(kampfarena.isRegistrationOpen());
    }

    /**
     * Test isRegistrationOpen true at 2t. Based on time tracked by the mediator.
     */
    @Test
    public void testIsRegistrationOpenTrue2t() {
        System.out.println("\n+TEST: testIsRegistrationOpenTrue2t\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("2t"));

        System.out.println("\nExpected: true\n");
        System.out.println("Actual: " + kampfarena.isRegistrationOpen() + "\n");

        Assert.assertTrue(kampfarena.isRegistrationOpen());
    }

    /**
     * Test isRegistrationOpen false. Based on time tracked by the mediator.
     */
    @Test
    public void testIsRegistrationOpenFalse3t() {
        System.out.println("\n+TEST: testIsRegistrationOpenFalse3t\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("3t"));

        System.out.println("\nExpected: false\n");
        System.out.println("Actual: " + kampfarena.isRegistrationOpen() + "\n");

        Assert.assertFalse(kampfarena.isRegistrationOpen());
    }

    /**
     * Test isRegistrationOpen false. Based on time tracked by the mediator.
     */
    @Test
    public void testIsRegistrationOpenFalse0t() {
        System.out.println("\n+TEST: testIsRegistrationOpenFalse0t\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("0t"));

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isRegistrationOpen() + "\n");

        Assert.assertFalse(kampfarena.isRegistrationOpen());
    }

    /**
     * Test registerForBattle with isRegistrationOpen is true.
     */
    @Test
    public void testRegisterForBattleRegistrationIsOpenTrue() {
        System.out.println("\n+TEST: testRegisterForBattleRegistrationIsOpenTrue\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("1t"));

        kampfarena.registerForBattle(player.getTrainers());

        System.out.println("\nExpected: " + player.getTrainers().size());
        System.out.println("Actual: " + kampfarena.getRegistry().size() + "\n");

        Assert.assertEquals(player.getTrainers().size(), kampfarena.getRegistry().size());
    }

    /**
     * Test registerForBattle with isRegistrationOpen is false.
     */
    @Test
    public void testRegisterForBattleRegistrationIsOpen() {
        System.out.println("\n+TEST: testRegisterForBattleRegistrationIsOpen\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("3t"));

        System.out.println("\nExpected: null");
        System.out.println("Actual: " + kampfarena.registerForBattle(player.getTrainers()) + "\n");

        Assert.assertNull(kampfarena.registerForBattle(player.getTrainers()));
    }

    /**
     * Test registerForBattle with already registered trainers.
     */
    @Test
    public void testRegisterForBattleTrainerIsAlreadyRegistered() {
        System.out.println("\n+TEST: testRegisterForBattleTrainerIsAlreadyRegistered\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("1t"));

        kampfarena.registerForBattle(player.getTrainers());

        Trainer trainer3 = player.buildTrainer(player, "Trainer3");
        player.addTrainer(trainer3);

        HashMap<String, Boolean> alreadyRegistered = kampfarena.registerForBattle(player.getTrainers());

        System.out.println("\nExpected: 2");
        System.out.println("Actual: " + alreadyRegistered.size());

        System.out.println("\nExpected: 3");
        System.out.println("Actual: " + kampfarena.getRegistry().size() + "\n");

        Assert.assertEquals(2 , alreadyRegistered.size());
        Assert.assertEquals(3 , kampfarena.getRegistry().size());
    }

    /**
     * Test initiate battle during open hours.
     */
    @Test
    public void testInitiateBattleDuringOpenTime() throws InterruptedException {
        System.out.println("\n+TEST: testInitiateBattleDuringOpenTime\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("1t"));

        kampfarena.registerForBattle(player.getTrainers());
        kampfarena.initiateBattle();

        if(kampfarena.getRegistry().size() < 2) {
            Assert.assertFalse(kampfarena.isABattleOngoing());

        } else {
            Assert.assertTrue(kampfarena.isABattleOngoing());
        }
    }

    /**
     * Test initiate battle during closed hours.
     */
    @Test
    public void testInitiateBattleDuringClosedTime() throws InterruptedException {
        System.out.println("\n+TEST: testInitiateBattleDuringClosedTime\n");

        while(!mediator.getWildeLand().whatTimeIsIt().contains("3t"));

        kampfarena.registerForBattle(player.getTrainers());
        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
    }
}