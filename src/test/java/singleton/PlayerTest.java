package singleton;

import factory.product.Monster;
import factory.product.Trainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    Player player;

    /**
     * Setup a new monster and trainer for every unit test.
     */
    @Before
    public void setUp() {
        System.out.println("\n===============================");
        System.out.println("    PlayerTest.java Test Suite");
        System.out.println("===============================");

        player = Player.getPlayer();
    }

    /**
     * Set class attributes to null after every test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Test exploreTheWildeLand method.
     */
    @Test
    public void testExploreTheWildeLand() throws InterruptedException {
        System.out.println("\n+TEST: testExploreTheWildeLand\n");

        Monster wildWale = new Monster("Wale");
        Monster wildKaht = new Monster("Kaht");
        Monster wildPyth = new Monster("Pyth");
        Monster wildJaxx = new Monster("Jaxx");
        Monster wildCoco = new Monster("Coco");
        Monster wildAntt = new Monster("Antt");
        Monster wildGith = new Monster("Gith");
        Monster wildServ = new Monster("Serv");
        Monster wildClie = new Monster("Clie");
        Monster wildDesi = new Monster("Desi");
        Monster wildExml = new Monster("Exml");
        Monster wildAdle = new Monster("Adle");

        player.addMonster(wildKaht);
        player.addMonster(wildWale);
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

        Trainer dock = new Trainer("Dock");
        dock.setMaxMp(9999);
        dock.setMp(9990);

        player.addTrainer(dock);

        while (dock.getCodex().size() == 0) {
            player.exploreTheWildeLand(dock.getName());
        }

        System.out.println("\nExpected: true");
        System.out.println("Actual: " + (dock.getCodex().size() > 0) + "\n");

        Assert.assertTrue(dock.getCodex().size() > 0);
    }

    /**
     * Test healHp method.
     */
    @Test
    public void testHealHpMethod() {
        System.out.println("\n+TEST: testHealHpMethod\n");

        Monster wildWale = new Monster("Wale");
        wildWale.setMaxHp(9999);
        wildWale.setHp(9990);

        Monster wildKaht = new Monster("Kaht");
        wildKaht.setMaxHp(9999);
        wildKaht.setHp(9990);

        Trainer dock = new Trainer("Dock");
        dock.setMaxHp(9999);
        dock.setHp(9990);

        dock.formBond(wildWale);
        dock.formBond(wildKaht);

        player.healHp(dock);

        System.out.println("\nExpected: 9999");
        System.out.println("Actual: " + dock.getHp() + "\n");

        Assert.assertEquals(9999, dock.getHp());
    }

    /**
     * Test healMp method.
     */
    @Test
    public void testHealMpMethod() {
        System.out.println("\n+TEST: testHealMpMethod\n");

        Monster wildWale = new Monster("Wale");
        Monster wildKaht = new Monster("Kaht");

        Trainer dock = new Trainer("Dock");
        dock.setMaxMp(9999);
        dock.setMp(9990);

        dock.formBond(wildWale);
        dock.formBond(wildKaht);

        player.healMp(dock);

        System.out.println("\nExpected: 9999");
        System.out.println("Actual: " + dock.getMp() + "\n");

        Assert.assertEquals(9999, dock.getMp());
    }
}
