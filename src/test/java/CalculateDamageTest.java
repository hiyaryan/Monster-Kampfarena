import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

/**
 * Method specific testing for calculateDamage.
 *
 * @author Ryan J Meneses
 * @version 1.0
 */
@RunWith(Parameterized.class)
public class CalculateDamageTest {
    private Class<BattleScenario> classUnderTest;

    @SuppressWarnings("unchecked")
    public CalculateDamageTest(Object classUnderTest) {
        this.classUnderTest = (Class<BattleScenario>) classUnderTest;
    }

    /**
     * Collection of classes to be tested.
     *
     * @return ArrayList
     */
    @Parameters
    public static Collection<Object[]> courseGradesUnderTest() {
        Object[][] classes = {
                {BattleScenario.class},
        };

        return Arrays.asList(classes);
    }

    private BattleScenario createBattleScenario(Mascotmon a, Mascotmon d) throws Exception {
        Constructor<BattleScenario> constructor =
                classUnderTest.getConstructor(Mascotmon.class, Mascotmon.class);
        System.out.println(constructor);
        return constructor.newInstance(a, d);
    }


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * GIVEN Battle between Bully and Ralphie on a sunny day with ground attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void bVsRSunnyGround() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    bVsRSunnyGround");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(80, "Ground");

        // Calculation: (80 * 1 * 1 * 1) - (65 * 1 * 1) == 15
        // 80 put into attack manually, no weather bonuses on either side, Ralphie has 65 defense

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        Assert.assertEquals(15, damage, 0.2);
    }

    /**
     * GIVEN Battle between Bully and Ralphie on a sunny day with ground attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsBSunnyGround() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsBSunnyGround");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(80, "Ground");

        //Calculation: (80 * 1.25 * 1 *  1) - (40 * 1 * 1) == 56
        // 80 put into attack manually, Ralphie gets bonus for ground attack no weather bonuses
        // or Type bonus

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("         Damage dealt: " + damage);
        Assert.assertEquals(60, damage, 0.2);
    }

    /**
     * Test Case 1: Boundary totalDamage = 0 Expected when pAttackDamage = 0 Battle Scenario: Battle
     * between pAttacker Sparky and pDefender Albert on a sunny day with fire attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void sVsASunnyFireEqualTo0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    sVsASunnyFireEqualTo0");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(0, "Fire");

        // Calculation: totalDamage = 0
        //      Special case, totalDamage = 0 when pAttack = 0
        // 0 put into attack manually, Sparky gets bonus for fire attack & weather bonuses & Type
        // bonus

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 1: totalDamage = 0 -> pAttackDamage = 0");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(0, damage, 0);
    }

    /**
     * Test Case 2-1: Boundary totalDamage = -1 Expected when totalDamage < 0 Battle between
     * pAttacker Albert and pDefender Sparky on a sunny day with water attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSSunnyWaterLessThan0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSSunnyWaterLessThan0");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(18, "Water");

        // Calculation: (x * 1.25 * 0.75 * 1.25) - (40 * 1.25 * 0.75) = -1
        //               x = 31.15 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test Case 2-1: totalDamage = 1 -> if totalDamage < 0");
        System.out.println("        Let totalDamage = -1");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(1, damage, 0.2);
    }

    /**
     * Test Case 2-1-1: Boundary totalDamage = -1 Expected when totalDamage < 0 Battle between
     * pAttacker Albert and pDefender Sparky on a sunny day with water attack with arbitrarily
     * applied bonuses to force a negative value.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSSunnyWaterForcedNegativeLessThan0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSSunnyWaterForcedNegativeLessThan0");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(18, "Water");

        // Calculation: (x * 1.25 * 0.75 * 1.25) - (40 * 1.25 * 0.75) = -1
        //               x = 31.15 // actual
        //
        //              (x * 1.25 * 1 * 1.25) - (40 * 1 * 0.75) = -1
        //               x = 18.56 // forced negative

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test Case 2-1-1: totalDamage = 1 -> if totalDamage < 0");
        System.out.println("        Let totalDamage = -1");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(1, damage, 0.2);
    }

    /**
     * Test Case 2-2: Boundary totalDamage = -1 Expected when totalDamage < 0 Battle between
     * pAttacker Albert and pDefender Sparky on a neutral day with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSNeutralNormalLessThan0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSNeutralNormalLessThan0");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(39, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = -1
        //               x = 39 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 2-2: totalDamage = 1 -> if totalDamage < 0");
        System.out.println("        Let totalDamage = -1");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(1, damage, 0.2);
    }

    /**
     * Test Case 2-2-1: Boundary totalDamage = -1 Expected when totalDamage < 0 Battle between
     * pAttacker Albert and pDefender Sparky on a neutral day with normal attack and debuff defender
     * weather bonus.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSNeutralNormalWeatherDebuffDefenderLessThan0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSNeutralNormalWeatherDebuffDefenderLessThan0");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(29, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = -1
        //               x = 39 // actual
        //
        //              (x * 1 * 1 * 1) - (40 * 0.75 * 0.75) = -1
        //               x = 29 // corrects imp[3]

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 2-2: totalDamage = 1 -> if totalDamage < 0");
        System.out.println("        Let totalDamage = -1");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(1, damage, 0.2);
    }

    /**
     * Test Case 2-2-2: Boundary totalDamage = -1 Expected when totalDamage < 0 Battle between
     * pAttacker Albert and pDefender Sparky on a neutral day with normal attack and debuff defender
     * weather bonus.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSNeutralNormalWeatherAndTypeDebuffDefenderLessThan0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSNeutralNormalWeatherAndTypeDebuffDefenderLessThan0");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(21, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = -1
        //               x = 39 // actual
        //
        //              (x * 1 * 1 * 1) - (40 * 0.75 * 0.75) = -1
        //               x = 21.5 // corrects imp[4]

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 2-2: totalDamage = 1 -> if totalDamage < 0");
        System.out.println("        Let totalDamage = -1");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(1, damage, 0.2);
    }

    /**
     * Test Case 2-3: Boundary totalDamage = -1 Expected when totalDamage < 0 Battle between
     * pAttacker Albert and pDefender Sparky on a rainy day with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSRainyWaterLessThan0() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSRainyWaterLessThan0");

        fight1.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(11, "Water");

        // Calculation: (x * 1.25 * 1.25 * 1.25) - (40 * 0.75 * 0.75) = -1
        //               x = 11 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 2-3: totalDamage = 1 -> if totalDamage < 0");
        System.out.println("        Let totalDamage = -1");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(1, damage, 0.2);
    }

    /**
     * Test Case 3-1-1: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Bully on a sunny day with ground attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsBSunnyGroundGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsBSunnyGroundGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(8031, "Ground");

        // Calculation: (x * 1.25 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 8031.2 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-1-1: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-1-2: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Bully on a sunny day with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsBSunnyNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsBSunnyNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(10039, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 10039

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-1-2: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-1: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Bully and pDefender Ralphie during a drought with normal attack. Note: All fail
     * indicating possible issue with Bully battling during a drought.
     *
     * @throws Exception test failure
     */
    @Test
    public void bVsRDroughtNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    bVsRDroughtNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(13440, "Normal");

        // Calculation: (x * 1 * 0.75 * 1) - (65 * 1.25 * 1) = 9999
        //               x = 13440.33 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-1: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-2: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Bully and pDefender Ralphie neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void bVsRNeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    bVsRNeutralNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10064, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (65 * 1 * 1) = 9999
        //               x = 10064 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-2: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-3: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Sparky and pDefender Ralphie during neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void sVsRNeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    sVsRNeutralNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10064, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (65 * 1 * 1) = 9999
        //               x = 10064 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-3: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-4: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Sparky during neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsSNeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsSNeutralNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10039, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 10039 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-4: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-5: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Albert during a neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsANeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsANeutralNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10039, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 8031.2 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-5: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-6: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Bully during neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsBNeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsBNeutralNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10039, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 10039 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-6: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-2-7: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Bully during a drought with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsBDroughtNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsBDroughtNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(8023, "Normal");

        // Calculation: (x * 1 * 1.25 * 1) - (40 * 0.75 * 1) = 9999
        //               x = 8023.2 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-2-7: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-3-1: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Albert and pDefender Ralphie during a drought with water attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsRDroughtWaterGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsRDroughtWaterGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.drought);
        Attack attack = new Attack(8064.2, "Water");

        // Calculation: (x * 1.25 * 1 * 1) - (65 * 1.25 * 1) = 9999
        //               x = 8064.2 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-3-1: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-3-2: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Albert and pDefender Sparky during neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsSNeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.SPARKY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsSRainyWaterGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10039, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 10039 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-3-2: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-3-3: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Albert and pDefender Bully during rainy weather with water attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsBRainyWaterGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsBRainyWaterGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(6424, "Water");

        // Calculation: (x * 1.25 * 1.25 * 1) - (40 * 1 * 1) = 9999
        //               x = 6424.96 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-3-3: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-3-4: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Albert and pDefender Bully during rainy weather with normal attack and swapping
     * weather bonus.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsBRainyNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsBRainyNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(8031, "Normal");

        // Calculation: (x * 1 * 1.25 * 1) - (40 * 1 * 1) = 9999
        //               x = 6424.96 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-3-4: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 3-3-5: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Albert and pDefender Sparky during neutral weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void aVsBNeutralNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.BULLY);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    aVsBNeutralNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.neutral);
        Attack attack = new Attack(10039, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1 * 1) = 9999
        //               x = 10039 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 3-3-5: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    // Additional tests added for 100% coverage
    // #############################################################################################

    /**
     * Test Case 4-1: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Albert during sunny weather with ground attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsADroughtGroundGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsADroughtGroundGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.sunny);
        Attack attack = new Attack(6414, "Ground");

        // Calculation: (x * 1.25 * 1 * 1.25) - (40 * 0.75 * 0.75) = 9999
        //               x = 6413.76 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 4-1: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }

    /**
     * Test Case 4-2: Boundary totalDamage = 9999 Expected when totalDamage > 0 Battle between
     * pAttacker Ralphie and pDefender Albert during rainy weather with normal attack.
     *
     * @throws Exception test failure
     */
    @Test
    public void rVsARainyNormalGreaterThanZero() throws Exception {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);

        BattleScenario fight1 = createBattleScenario(attacker1, defender1);
        System.out.println("    rVsARainyNormalGreaterThanZero");

        fight1.setEnvironment(Environment.Weather.rainy);
        Attack attack = new Attack(10049, "Normal");

        // Calculation: (x * 1 * 1 * 1) - (40 * 1.25 * 1) = 9999
        //               x = 10049 // actual

        double damage = fight1.calculateDamage(attack, attacker1, defender1);
        System.out.println("    Test case 4-1: totalDamage = x -> for x > 0");
        System.out.println("        Let totalDamage = 9999");
        System.out.println("        Damage dealt: " + damage);
        Assert.assertEquals(9999, damage, 2.5);
    }
    // #############################################################################################
}