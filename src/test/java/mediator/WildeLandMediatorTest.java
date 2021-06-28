package mediator;

import decorator.monster.CodeAMon;
import factory.product.Monster;
import factory.product.Trainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import singleton.Player;

public class WildeLandMediatorTest {
    Mediator mediator;
    Player player;

    /**
     * Setup a new mediator for every unit test.
     */
    @Before
    public void setUp() throws InterruptedException {
        System.out.println("\n===============================");
        System.out.println("    WildeLandMediator.java Test Suite");
        System.out.println("===============================");

        player = Player.getPlayer();
        mediator = WildeLandMediator.getMediator();

        Thread tick = new Thread((Runnable) mediator);
        tick.start();
    }

    /**
     * Set class attributes to null after every test.
     */
    @After
    public void tearDown() {

    }

    /**
     * Test Singleton accessibility.
     */
    @Test
    public void testWeatherBonusWithDockAndWale() {
        System.out.println("\n+TEST: testWeatherBonusWithDockAndWale\n");

        Trainer dock = player.buildTrainer(player, "Dock");
        Monster wildWale = player.buildMonster(player, "Wale");

        player.addTrainer(dock);
        player.addMonster(wildWale);

        CodeAMon wale = dock.formBond(wildWale);

        try {
            if (mediator.getEnvironment().getWeather().equals(wale.getWeatherStrength().toString())) {
                System.out.println("\nExpected: 1.50");
                System.out.println("Actual: " + wale.getWeatherBonus() + "\n");

                Assert.assertEquals(1.50, wale.getWeatherBonus(), 0);

            } else if (mediator.getEnvironment().getWeather().equals(wale.getWeatherWeakness().toString())) {
                System.out.println("\nExpected: 0.5");
                System.out.println("Actual: " + wale.getWeatherBonus() + "\n");

                Assert.assertEquals(0.50, wale.getWeatherBonus(), 0);

            } else {
                System.out.println("\nExpected: 1.0");
                System.out.println("Actual: " + wale.getWeatherBonus() + "\n");

                Assert.assertEquals(1.0, wale.getWeatherBonus(), 0);
            }

        } catch (NullPointerException npe) {
            Assert.assertEquals(1, dock.getCodex().size());
        }
    }
}

