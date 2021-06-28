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
     * Test healHp method.
     */
    @Test
    public void testHealHpMethod() {
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
