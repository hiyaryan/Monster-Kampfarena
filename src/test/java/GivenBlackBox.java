import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Blackbox testing.
 *
 * @author Ryan J Meneses
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class GivenBlackBox {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

        /**
         * EXAMPLE
         */
        @Test
        public void bVsRSunnyGround() throws Exception {

//            Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
//            Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);
//
//            BattleScenario fight1 = createBattleScenario(attacker1, defender1);
//            System.out.println("    bVsRSunnyGround");
//
//            fight1.setEnvironment(Environment.Weather.sunny);
//            Attack attack = new Attack(80, "Ground");
//
//            // Calculation: (80 * 1 * 1 * 1) - (65 * 1 * 1) == 15
//            // 80 put into attack manually, no weather bonuses on either side, Ralphie has 65
//            // defense
//
//            double damage = fight1.calculateDamage(attack, attacker1, defender1);
//            System.out.println("         Damage dealt: " + damage);
//            Assert.assertEquals(15, damage, 0.2);
        }

        /**
         * EXAMPLE
         */
        @Test
        public void rVsBSunnyGround() throws Exception {

//            Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
//            Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);
//
//            BattleScenario fight1 = createBattleScenario(attacker1, defender1);
//            System.out.println("    rVsBSunnyGround");
//
//            fight1.setEnvironment(Environment.Weather.sunny);
//            Attack attack = new Attack(80, "Ground");
//
//            //Calculation: (80 * 1.2 * 1 *  1) - (40 * 1 * 1) == 56
//            // 80 put into attack manually, Ralphie gets bonus for ground attack no weather
//            // bonus or Type bonus
//
//            double damage = fight1.calculateDamage(attack, attacker1, defender1);
//            System.out.println("         Damage dealt: " + damage);
//            Assert.assertEquals(56, damage, 0.2);
        }
}