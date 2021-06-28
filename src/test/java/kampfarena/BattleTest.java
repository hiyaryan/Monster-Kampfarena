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
    public void setUp() {
        System.out.println("\n===============================");
        System.out.println("    Battle.java Test Suite");
        System.out.println("===============================");

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
        dock = player.buildTrainer(player, "Dock1");
        tomm = player.buildTrainer(player, "Tomm1");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale1");
        Monster wildKaht = player.buildMonster(player, "Kaht1");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getTrainers().get("Dock1").formBond(player.getMonsters().get("Wale1"));
        player.getTrainers().get("Tomm1").formBond(player.getMonsters().get("Kaht1"));

        while (kampfarena.getRegistry().size() == 0) {
            kampfarena.registerForBattle(player.getTrainers());
            Thread.sleep(1000);
        }

        System.out.println("\nExpected: true");
        System.out.println("Actual: " + (kampfarena.getRegistry().size() > 0) + "\n");

        Assert.assertTrue(kampfarena.getRegistry().size() > 0);
    }

    /**
     * Test register for battle while closed.
     */
    @Test
    public void testRegisterForBattleWhileClosed() throws InterruptedException {
        System.out.println("\n+TEST: testRegisterForBattleWhileClosed\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock3");
        tomm = player.buildTrainer(player, "Tomm3");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale3");
        Monster wildKaht = player.buildMonster(player, "Kaht3");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getTrainers().get("Dock3").formBond(player.getMonsters().get("Wale3"));
        player.getTrainers().get("Tomm3").formBond(player.getMonsters().get("Kaht3"));

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
        dock = player.buildTrainer(player, "Dock4");
        tomm = player.buildTrainer(player, "Tomm4");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale4");
        Monster wildKaht = player.buildMonster(player, "Kaht4");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getTrainers().get("Dock4").formBond(player.getMonsters().get("Wale4"));
        player.getTrainers().get("Tomm4").formBond(player.getMonsters().get("Kaht4"));

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
        dock = player.buildTrainer(player, "Dock5");

        player.addTrainer(dock);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale5");

        player.addMonster(wildWale);

        player.getTrainers().get("Dock5").formBond(player.getMonsters().get("Wale5"));

        if (!mediator.getDate().contains("1t") || mediator.getDate().contains("1t")) {
            while (mediator.getDate().contains("3t") || mediator.getDate().contains("0t")) {
                Thread.sleep(8000);
            }
        }

        if (kampfarena.getRegistry().size() != 0) {
            while (kampfarena.getRegistry().size() > 1) {
                kampfarena.initiateBattle();

                Thread.sleep(8000);
            }
        }

        kampfarena.registerForBattle(player.getTrainers());
        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
    }

    /**
     * Test battle where trainer one wins.
     */
    @Test
    public void testBattleWhereTrainerOneWins() throws InterruptedException {
        System.out.println("\n+TEST: testBattleWhereTrainerOneWins\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock6");
        dock.setHp(9999);
        dock.setStrength(9999);

        tomm = player.buildTrainer(player, "Tomm6");

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale6");
        Monster wildKaht = player.buildMonster(player, "Kaht6");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getTrainers().get("Dock6").formBond(player.getMonsters().get("Wale6"));
        player.getTrainers().get("Dock6").getCodex().get("Wale6").getMonster().setHp(9999);
        player.getTrainers().get("Dock6").getCodex().get("Wale6").getMonster().setStrength(9999);

        player.getTrainers().get("Tomm6").formBond(player.getMonsters().get("Kaht6"));

        kampfarena.registerForBattle(player.getTrainers());


        if (!mediator.getDate().contains("1t") || mediator.getDate().contains("1t")) {
            while (mediator.getDate().contains("3t") || mediator.getDate().contains("0t")) {
                Thread.sleep(8000);
            }
        }

        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
    }


    /**
     * Test battle where trainer two wins.
     */
    @Test
    public void testBattleWhereTrainerTwoWins() throws InterruptedException {
        System.out.println("\n+TEST: testBattleWhereTrainerOneWins\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock7");

        tomm = player.buildTrainer(player, "Tomm7");
        tomm.setHp(9999);
        tomm.setStrength(9999);

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale7");
        Monster wildKaht = player.buildMonster(player, "Kaht7");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getTrainers().get("Dock7").formBond(player.getMonsters().get("Wale7"));

        player.getTrainers().get("Tomm7").formBond(player.getMonsters().get("Kaht7"));
        player.getTrainers().get("Tomm7").getCodex().get("Kaht7").getMonster().setHp(9999);
        player.getTrainers().get("Tomm7").getCodex().get("Kaht7").getMonster().setStrength(9999);

        kampfarena.registerForBattle(player.getTrainers());


//        if (!mediator.getDate().contains("1t") || mediator.getDate().contains("1t")) {
//            while (mediator.getDate().contains("3t") || mediator.getDate().contains("0t")) {
//                Thread.sleep(8000);
//            }
//        }

        while (kampfarena.getRegistry().size() == 0) {
            kampfarena.registerForBattle(player.getTrainers());
            Thread.sleep(1000);
        }

        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
    }

    /**
     * Test battle is over and Code-a-mon gains exp.
     */
    @Test
    public void testBattleIsOverAndCodeAMonEarnsExp() throws InterruptedException {
        System.out.println("\n+TEST: testBattleIsOverAndCodeAMonEarnsExp\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock8");

        tomm = player.buildTrainer(player, "Tomm8");
        tomm.setHp(9999);
        tomm.setStrength(9999);

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale8");
        Monster wildKaht = player.buildMonster(player, "Kaht8");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getTrainers().get("Dock8").formBond(player.getMonsters().get("Wale8"));

        player.getTrainers().get("Tomm8").formBond(player.getMonsters().get("Kaht8"));
        player.getTrainers().get("Tomm8").getCodex().get("Kaht8").getMonster().setHp(9999);
        player.getTrainers().get("Tomm8").getCodex().get("Kaht8").getMonster().setStrength(9999);
        player.getTrainers().get("Tomm8").getCodex().get("Kaht8").setExp(9999);

        kampfarena.registerForBattle(player.getTrainers());

        if (!mediator.getDate().contains("1t") || mediator.getDate().contains("1t")) {
            while (mediator.getDate().contains("3t") || mediator.getDate().contains("0t")) {
                Thread.sleep(8000);
            }
        }

        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        System.out.println("\nExpected: true");
        System.out.println("Actual: " + (player.getTrainers().get("Tomm8").getCodex().get("Kaht8").getExp() > 0)
                + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
        Assert.assertTrue(player.getTrainers().get("Tomm8").getCodex().get("Kaht8").getExp() > 0);
    }

    /**
     * Test battle is over and losing Code-a-mon loses exp.
     */
    @Test
    public void testBattleIsOverAndCodeAMonLosesExp() throws InterruptedException {
        System.out.println("\n+TEST: testBattleIsOverAndCodeAMonLosesExp\n");

        // Build trainers
        dock = player.buildTrainer(player, "Dock9");
        tomm = player.buildTrainer(player, "Tomm9");
        tomm.setHp(9999);
        tomm.setStrength(9999);

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale9");
        Monster wildKaht = player.buildMonster(player, "Kaht9");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getMonsters().get("Wale9").setExp(9999);

        player.getTrainers().get("Dock9").formBond(player.getMonsters().get("Wale9"));

        player.getTrainers().get("Tomm9").formBond(player.getMonsters().get("Kaht9"));
        player.getTrainers().get("Tomm9").getCodex().get("Kaht9").getMonster().setHp(9999);
        player.getTrainers().get("Tomm9").getCodex().get("Kaht9").getMonster().setStrength(9999);
        player.getTrainers().get("Tomm9").getCodex().get("Kaht9").setExp(9999);

        kampfarena.registerForBattle(player.getTrainers());

        if (!mediator.getDate().contains("1t") || mediator.getDate().contains("1t")) {
            while (mediator.getDate().contains("3t") || mediator.getDate().contains("0t")) {
                Thread.sleep(8000);
            }
        }

        kampfarena.initiateBattle();

        System.out.println("\nExpected: false");
        System.out.println("Actual: " + kampfarena.isABattleOngoing() + "\n");

        System.out.println("\nExpected: 0");
        System.out.println("Actual: " + player.getMonsters().get("Wale9").getExp() + "\n");

        Assert.assertFalse(kampfarena.isABattleOngoing());
        Assert.assertEquals(0, player.getMonsters().get("Wale9").getExp());
    }

    /**
     * Test battle is over and losing Code-a-mon loses exp.
     */
    @Test
    public void testAttackWithTrainer() throws InterruptedException {
        System.out.println("\n+TEST: testBattleIsOverAndCodeAMonLosesExp\n");

        dock = player.buildTrainer(player, "Dock10");

        // Build trainers
        tomm = player.buildTrainer(player, "Tomm10");
        tomm.setHp(9999);
        tomm.setStrength(9999);

        player.addTrainer(dock);
        player.addTrainer(tomm);

        // Build monsters
        Monster wildWale = player.buildMonster(player, "Wale10");
        Monster wildKaht = player.buildMonster(player, "Kaht10");

        player.addMonster(wildWale);
        player.addMonster(wildKaht);

        player.getMonsters().get("Wale10").setExp(9999);

        player.getTrainers().get("Dock10").formBond(player.getMonsters().get("Wale10"));

        player.getTrainers().get("Tomm10").formBond(player.getMonsters().get("Kaht10"));
        player.getTrainers().get("Tomm10").getCodex().get("Kaht10").getMonster().setHp(9999);
        player.getTrainers().get("Tomm10").getCodex().get("Kaht10").getMonster().setStrength(9999);
        player.getTrainers().get("Tomm10").getCodex().get("Kaht10").setExp(9999);

        kampfarena.registerForBattle(player.getTrainers());

        if (!mediator.getDate().contains("1t") || mediator.getDate().contains("1t")) {
            while (mediator.getDate().contains("3t") || mediator.getDate().contains("0t")) {
                Thread.sleep(8000);
            }
        }

        Battle battle = new Battle(kampfarena.getRegistry());
        int attack = battle.attack(tomm);

        System.out.println("\nExpected: 9999");
        System.out.println("Actual: " + attack + "\n");

        Assert.assertEquals(9999, attack);
    }
}
