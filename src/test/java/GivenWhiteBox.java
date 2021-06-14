import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

/**
 * Whitebox testing.
 *
 * @author Ryan J Meneses
 * @version 1.0
 */
public class GivenWhiteBox {
    @Before
    public void setUp() throws Exception {

    }

    /**
     * GIVEN This is not deterministic if total damage is calculated correctly. Try to find out why
     * and what the problem is. Then make changes so that the battle outcome IS deterministic! The
     * battle mechanic will need to change for this but you should try to make the least amount of
     * changes possible
     */
    @Test
    public void bVsRSunny() {
        // Initialize two Mascotmon
        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.RALPHIE);

        //Create First battle scenario with two mons
        BattleScenario fight1 = new BattleScenario(attacker1, defender1);
        //Set the weather
        fight1.setEnvironment(Environment.Weather.sunny);

        Mascotmon mon = fight1.fight();
        Assert.assertEquals(mon, attacker1);
    }

    // ******************************************* BattleScenario TEST SUITE
    // *******************************************

    // TEST fight()
    /*############################################################################################*/

    /**
     * testFightWithAVsRSunnyGroundToFirstIf Testing node coverage: <1,2,3,4,5,6,7,8,9,10,11,12,
     * 13,14,15,16,30> Testing to first if statement at node 14: mon2.stats.health <= 0.0
     */
    @Test
    public void testFightWithAVsRSunnyGroundToFirstIf() {
        // Initialize two Mascotmon
        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.RALPHIE);

        //Create First battle scenario with two mons
        BattleScenario fight = new BattleScenario(mon1, mon2);

        //Set the weather
        fight.setEnvironment(Environment.Weather.sunny);

        // Makes first if statement true: mon2.stats.health <= 0.0
        mon2.stats.health = 1;

        System.out.println("       testFightWithAVsRSunnyGroundToFirstIf");
        System.out.println("       Node coverage: <1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,30>");
        System.out.println("                      @14: mon2.stats.health <= 0.0");

        Mascotmon mon = fight.fight();
        Assert.assertEquals(mon, mon1);
    }

    /**
     * testFightWithRVsSRainyGroundToSecondIf Testing node coverage: <1,2,3,4,5,6,7,8,9,10,11,12,
     * 13,14,17,18,19,20,21,22,23,24,25,26,30> Testing to second if statement at node 24:
     * mon1.stats.health <= 0.0
     */
    @Test
    public void testFightWithRVsSRainyGroundToSecondIf() {
        // Initialize two Mascotmon
        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.RALPHIE);
        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.SPARKY);

        //Create First battle scenario with two mons
        BattleScenario fight = new BattleScenario(mon1, mon2);

        //Set the weather
        fight.setEnvironment(Environment.Weather.rainy);

        // Makes second if statement true: mon1.stats.health <= 0.0
        mon1.stats.health = 33.28125;

        System.out.println("       testFightWithRVsSRainyGroundToSecondIf");
        System.out.println(
                "       Node coverage: <1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,"
                        + "25,26,30>");
        System.out.println("                      @24: mon1.stats.health <= 0.0");

        Mascotmon mon = fight.fight();
        Assert.assertEquals(mon, mon2);
    }

    /**
     * testFightWithSVsBDroughtFireLoopToFirstIf Testing node AND edge coverage
     * <1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,27,28,29,
     * 7,8,9,10,11,12,13,14,15,16,30> Testing to first if statement at node 14 after 1x loop to node
     * 7: mon2.stats.health <= 0.0
     */
    @Test
    public void testFightWithSVsBDroughtFireLoopToFirstIf() {
        // // Initialize two Mascotmon
        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.BULLY);

        //Create First battle scenario with two mons
        BattleScenario fight = new BattleScenario(mon1, mon2);

        //Set the weather
        fight.setEnvironment(Environment.Weather.drought);

        // Makes first if statement true after 1 loop: mon1.stats.health <= 0.0
        mon2.stats.health = 115;

        System.out.println("       testFightWithSVsBDroughtFireLoopToFirstIf");
        System.out.println(
                "       Node coverage: <1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,"
                        + "27,28,29,\n"
                        + "                      7,8,9,10,11,12,13,14,15,16,30>");
        System.out.println("                      @7: true");
        System.out.println("                      @14: mon2.stats.health <= 0.0");

        Mascotmon mon = fight.fight();
        Assert.assertEquals(mon, mon1);
    }

    /**
     * testFightWithBVsANeutralNormalLoopToSecondIf Testing node AND edge coverage
     * <1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,27,28,29,
     * 7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,25,26,30> Testing to second if statement at node
     * 24 after 1x loop to node 7: mon1.stats.health <= 0.0
     */
    @Test
    public void testFightWithBVsANeutralNormalLoopToSecondIf() {
        // Initialize two Mascotmon
        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.ALBERT);

        //Create First battle scenario with two mons
        BattleScenario fight = new BattleScenario(mon1, mon2);

        //Set the weather
        fight.setEnvironment(Environment.Weather.drought);

        // Makes first if statement true after 1 loop: mon1.stats.health <= 0.0
        mon1.stats.health = 60;

        System.out.println("       testFightWithBVsANeutralNormalLoopToSecondIf");
        System.out.println(
                "       Node coverage: <1,2,3,4,5,6,7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,"
                        + "27,28,29,\n"
                        + "                      7,8,9,10,11,12,13,14,17,18,19,20,21,22,23,24,25,"
                        + "26,30>");
        System.out.println("                      @7: true");
        System.out.println("                      @24: mon1.stats.health <= 0.0");

        Mascotmon mon = fight.fight();
        Assert.assertEquals(mon, mon2);
    }

    // TEST attack()
    /*############################################################################################*/

    /**
     * testAttackWithAGround Testing attack method from Mascotmon called in fight method with
     * Albert. Note: attack was made deterministic to properly test fight random generator for
     * attackNumber is always 1-- for Albert: ground
     */
    @Test
    public void testAttackWithAGround() {
        // One Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.ALBERT);
        Attack attack1 = mon.attack();

        // Initialize an Attack
        Attack attack2 = new Attack(new Mascotmon(Mascotmon.Name.ALBERT).stats.attack, "Ground");

        System.out.println("       testAttackWithAGround");
        System.out.println("           " + "Expected stats.attack: " + attack2.damage);
        System.out.println("           " + "Actual stats.attack: " + attack1.damage);
        System.out.println("           " + "Expected Type: " + attack2.type);
        System.out.println("           " + "Actual Type: " + attack1.type);

        Assert.assertEquals(attack2.damage, attack1.damage, 0);
        Assert.assertEquals(attack2.type, attack1.type);
    }

    /**
     * testAttackWithRGround Testing attack method from Mascotmon called in fight method with
     * Ralphie. Note: attack was made deterministic to properly test fight random generator for
     * attackNumber is always 1-- for Ralphie: ground
     */
    @Test
    public void testAttackWithRGround() {
        // One Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.RALPHIE);
        Attack attack1 = mon.attack();

        // Initialize an Attack
        Attack attack2 = new Attack(new Mascotmon(Mascotmon.Name.RALPHIE).stats.attack, "Ground");

        System.out.println("       testAttackWithRGround");
        System.out.println("           " + "Expected stats.attack: " + attack2.damage);
        System.out.println("           " + "Actual stats.attack: " + attack1.damage);
        System.out.println("           " + "Expected Type: " + attack2.type);
        System.out.println("           " + "Actual Type: " + attack1.type);

        Assert.assertEquals(attack2.damage, attack1.damage, 0);
        Assert.assertEquals(attack2.type, attack1.type);
    }

    /**
     * testAttackWithSFire Testing attack method from Mascotmon called in fight method with Sparky.
     * Note: attack was made deterministic to properly test fight random generator for attackNumber
     * is always 1-- for Sparky: fire
     */
    @Test
    public void testAttackWithSFire() {
        // One Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.SPARKY);
        Attack attack1 = mon.attack();

        // Initialize an Attack
        Attack attack2 = new Attack(new Mascotmon(Mascotmon.Name.SPARKY).stats.attack, "Fire");

        System.out.println("       testAttackWithSFire");
        System.out.println("           " + "Expected stats.attack: " + attack2.damage);
        System.out.println("           " + "Actual stats.attack: " + attack1.damage);
        System.out.println("           " + "Expected Type: " + attack2.type);
        System.out.println("           " + "Actual Type: " + attack1.type);

        Assert.assertEquals(attack2.damage, attack1.damage, 0);
        Assert.assertEquals(attack2.type, attack1.type);
    }

    /**
     * testAttackWithBNormal Testing attack method from Mascotmon called in fight method with Bully.
     * Note: attack was made deterministic to properly test fight random generator for attackNumber
     * is always 1-- for Bully: normal
     */
    @Test
    public void testAttackWithBNormal() {
        // One Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.BULLY);
        Attack attack1 = mon.attack();

        // Initialize an Attack
        Attack attack2 = new Attack(new Mascotmon(Mascotmon.Name.BULLY).stats.attack, "Normal");

        System.out.println("       testAttackWithBNormal");
        System.out.println("           " + "Expected stats.attack: " + attack2.damage);
        System.out.println("           " + "Actual stats.attack: " + attack1.damage);
        System.out.println("           " + "Expected Type: " + attack2.type);
        System.out.println("           " + "Actual Type: " + attack1.type);

        Assert.assertEquals(attack2.damage, attack1.damage, 0);
        Assert.assertEquals(attack2.type, attack1.type);
    }
    /*############################################################################################*/
    /*############################################################################################*/


    // TEST Constructors
    /*############################################################################################*/

    /**
     * testBattleScenarioConstructor Testing BattleScenario constructor with Albert and Ralphie.
     */
    @Test
    public void testBattleScenarioConstructor() {
        // Two Mascotmon
        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.ALBERT);
        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.RALPHIE);

        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(mon1, mon2);

        System.out.println("       testBattleScenarioConstructor");
        System.out.println("           " + "Expected mon1: " + Mascotmon.Name.ALBERT.toString());
        System.out.println("           " + "Actual mon1: " + battleScenario.mon1.name.toString());
        System.out.println("           " + "Expected mon2: " + Mascotmon.Name.RALPHIE.toString());
        System.out.println("           " + "Actual mon2: " + battleScenario.mon2.name.toString());

        Assert.assertEquals(Mascotmon.Name.ALBERT.toString(), battleScenario.mon1.name.toString());
        Assert.assertEquals(Mascotmon.Name.RALPHIE.toString(), battleScenario.mon2.name.toString());
    }

    // TEST Setters
    /*############################################################################################*/

    /**
     * testMon1SetterWithAlbert Testing BattleScenario constructor with Albert and Ralphie.
     */
    @Test
    public void testMon1SetterWithAlbert() {
        // One Mascotmon
        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.ALBERT);

        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(null, null);
        battleScenario.setMon1(mon1);

        System.out.println("       testMon1SetterWithAlbert");
        System.out.println("           " + "Expected mon1: " + Mascotmon.Name.ALBERT.toString());
        System.out.println("           " + "Actual mon1: " + battleScenario.mon1.name.toString());

        Assert.assertEquals(Mascotmon.Name.ALBERT.toString(), battleScenario.mon1.name.toString());
    }

    /**
     * testBattleScenarioConstructor Testing BattleScenario constructor with Albert and Ralphie.
     */
    @Test
    public void testMon2SetterWithRalphie() {
        // One Mascotmon
        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.RALPHIE);

        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(null, null);
        battleScenario.setMon2(mon2);

        System.out.println("       testMon2SetterWithRalphie");
        System.out.println("           " + "Expected mon2: " + Mascotmon.Name.RALPHIE.toString());
        System.out.println("           " + "Actual mon2: " + battleScenario.mon2.name.toString());

        Assert.assertEquals(Mascotmon.Name.RALPHIE.toString(), battleScenario.mon2.name.toString());
    }
    /*############################################################################################*/
    /*############################################################################################*/


    // TEST setEnvironment(Environment.Weather)
    /*############################################################################################*/

    /**
     * testSetEnvironmentSunny Testing BattleScenario setEnvironment sunny.
     */
    @Test
    public void testSetEnvironmentSunny() {
        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(null, null);
        battleScenario.setEnvironment(Environment.Weather.sunny);

        System.out.println("       testSetEnvironmentSunny");
        System.out.println("           " + "Expected weather: " + Environment.Weather.sunny);
        System.out
                .println("           " + "Actual weather: " + battleScenario.battleWeather.weather);

        Assert.assertEquals(Environment.Weather.sunny, battleScenario.battleWeather.weather);
    }

    /**
     * testSetEnvironmentRainy Testing BattleScenario setEnvironment rainy.
     */
    @Test
    public void testSetEnvironmentRainy() {
        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(null, null);
        battleScenario.setEnvironment(Environment.Weather.rainy);

        System.out.println("       testSetEnvironmentRainy");
        System.out.println("           " + "Expected weather: " + Environment.Weather.rainy);
        System.out
                .println("           " + "Actual weather: " + battleScenario.battleWeather.weather);

        Assert.assertEquals(Environment.Weather.rainy, battleScenario.battleWeather.weather);
    }

    /**
     * testSetEnvironmentDrought Testing BattleScenario setEnvironment drought.
     */
    @Test
    public void testSetEnvironmentDrought() {
        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(null, null);
        battleScenario.setEnvironment(Environment.Weather.drought);

        System.out.println("       testSetEnvironmentDrought");
        System.out.println("           " + "Expected weather: " + Environment.Weather.drought);
        System.out
                .println("           " + "Actual weather: " + battleScenario.battleWeather.weather);

        Assert.assertEquals(Environment.Weather.drought, battleScenario.battleWeather.weather);
    }

    /**
     * testSetEnvironmentNeutral Testing BattleScenario setEnvironment neutral.
     */
    @Test
    public void testSetEnvironmentNeutral() {
        // Initialize BattleScenario object
        BattleScenario battleScenario = new BattleScenario(null, null);
        battleScenario.setEnvironment(Environment.Weather.neutral);

        System.out.println("       testSetEnvironmentNeutral");
        System.out.println("           " + "Expected weather: " + Environment.Weather.neutral);
        System.out
                .println("           " + "Actual weather: " + battleScenario.battleWeather.weather);

        Assert.assertEquals(Environment.Weather.neutral, battleScenario.battleWeather.weather);
    }

    // TEST Environment Constructor
    // SER316 TASK 2 SPOTBUGS FIX
    /*############################################################################################*/

    /**
     * testEnvironmentConstructorSunny Testing Environment Constructor sunny.
     */
    //    @Test
    //    public void testEnvironmentConstructorSunny() {
    //        // Initialize Environment object
    //        Environment environment = new Environment(Environment.Weather.sunny);
    //
    //        System.out.println("       testEnvironmentConstructorSunny");
    //        System.out.println("           " + "Expected weather: " + Environment.Weather.sunny);
    //        System.out.println("           " + "Actual weather: " + environment.weather);
    //
    //        System.out.println("           " + "Expected buffedType: " + "Fire");
    //        System.out.println("           " + "Actual buffedType: " + environment.buffedType);
    //        System.out.println("           " + "Expected buffModifier: " + 1.25);
    //        System.out.println("           " + "Actual buffModifier: "
    //        + environment.buffModifier);
    //
    //        System.out.println("           " + "Expected debuffedType: " + "Water");
    //        System.out.println("           " + "Actual debuffedType: "
    //        + environment.debuffedType);
    //        System.out.println("           " + "Expected debuffModifier: " + 0.75);
    //        System.out.println("           " + "Actual debuffModifier: "
    //        + environment.debuffModifier);
    //
    //        Assert.assertEquals(Environment.Weather.sunny, environment.weather);
    //
    //        Assert.assertEquals("Fire", environment.buffedType);
    //        Assert.assertEquals(1.25, environment.buffModifier, 0);
    //
    //        Assert.assertEquals("Water", environment.debuffedType);
    //        Assert.assertEquals(0.75, environment.debuffModifier, 0);
    //    }

    /**
     * testEnvironmentConstructorRainy Testing Environment Constructor rainy.
     */
    //    @Test
    //    public void testEnvironmentConstructorRainy() {
    //        // Initialize Environment object
    //        Environment environment = new Environment(Environment.Weather.rainy);
    //
    //        System.out.println("       testEnvironmentConstructorRainy");
    //        System.out.println("           " + "Expected weather: " + Environment.Weather.rainy);
    //        System.out.println("           " + "Actual weather: " + environment.weather);
    //
    //        System.out.println("           " + "Expected buffedType: " + "Water");
    //        System.out.println("           " + "Actual buffedType: " + environment.buffedType);
    //        System.out.println("           " + "Expected buffModifier: " + 1.25);
    //        System.out.println("           " + "Actual buffModifier: "
    //        + environment.buffModifier);
    //
    //        System.out.println("           " + "Expected debuffedType: " + "Fire");
    //        System.out.println("           " + "Actual debuffedType: "
    //        + environment.debuffedType);
    //        System.out.println("           " + "Expected debuffModifier: " + 0.75);
    //        System.out.println("           " + "Actual debuffModifier: "
    //        + environment.debuffModifier);
    //
    //        Assert.assertEquals(Environment.Weather.rainy, environment.weather);
    //
    //        Assert.assertEquals("Water", environment.buffedType);
    //        Assert.assertEquals(1.25, environment.buffModifier, 0);
    //
    //        Assert.assertEquals("Fire", environment.debuffedType);
    //        Assert.assertEquals(0.75, environment.debuffModifier, 0);
    //    }

    /**
     * testEnvironmentConstructorDrought Testing Environment Constructor drought.
     */
    //    @Test
    //    public void testEnvironmentConstructorDrought() {
    //        // Initialize Environment object
    //        Environment environment = new Environment(Environment.Weather.drought);
    //
    //        System.out.println("       testEnvironmentConstructorDrought");
    //        System.out.println("           " + "Expected weather: "
    //        + Environment.Weather.drought);
    //        System.out.println("           " + "Actual weather: " + environment.weather);
    //
    //        System.out.println("           " + "Expected buffedType: " + "Ground");
    //        System.out.println("           " + "Actual buffedType: " + environment.buffedType);
    //        System.out.println("           " + "Expected buffModifier: " + 1.25);
    //        System.out.println("           " + "Actual buffModifier: "
    //        + environment.buffModifier);
    //
    //        System.out.println("           " + "Expected debuffedType: " + "Normal");
    //        System.out.println("           " + "Actual debuffedType: "
    //        + environment.debuffedType);
    //        System.out.println("           " + "Expected debuffModifier: " + 0.75);
    //        System.out.println("           " + "Actual debuffModifier: "
    //        + environment.debuffModifier);
    //
    //        Assert.assertEquals(Environment.Weather.drought, environment.weather);
    //
    //        Assert.assertEquals("Ground", environment.buffedType);
    //        Assert.assertEquals(1.25, environment.buffModifier, 0);
    //
    //        Assert.assertEquals("Normal", environment.debuffedType);
    //        Assert.assertEquals(0.75, environment.debuffModifier, 0);
    //    }

    /**
     * testEnvironmentConstructorNeutral Testing Environment Constructor neutral.
     */
    //    @Test
    //    public void testEnvironmentConstructorNeutral() {
    //        // Initialize Environment object
    //        Environment environment = new Environment(Environment.Weather.neutral);
    //
    //        System.out.println("       testEnvironmentConstructorNeutral");
    //        System.out.println("           " + "Expected weather: "
    //        + Environment.Weather.neutral);
    //        System.out.println("           " + "Actual weather: " + environment.weather);
    //
    //        System.out.println("           " + "Expected buffedType: " + "");
    //        System.out.println("           " + "Actual buffedType: " + environment.buffedType);
    //        System.out.println("           " + "Expected buffModifier: " + 1);
    //        System.out.println("           " + "Actual buffModifier: "
    //        + environment.buffModifier);
    //
    //        System.out.println("           " + "Expected debuffedType: " + "");
    //        System.out.println("           " + "Actual debuffedType: "
    //        + environment.debuffedType);
    //        System.out.println("           " + "Expected debuffModifier: " + 1);
    //        System.out.println("           " + "Actual debuffModifier: "
    //        + environment.debuffModifier);
    //
    //        Assert.assertEquals(Environment.Weather.neutral, environment.weather);
    //
    //        Assert.assertEquals("", environment.buffedType);
    //        Assert.assertEquals(1, environment.buffModifier, 0);
    //
    //        Assert.assertEquals("", environment.debuffedType);
    //        Assert.assertEquals(1, environment.debuffModifier, 0);
    //    }
    /*############################################################################################*/
    /*############################################################################################*/


    // TEST initiateBattle()
    /*############################################################################################*/

    /**
     * testInitiateBattle Testing initiate battle.
     */
    //    @Test
    //    public void testInitiateBattle() {
    //        // Initialize two Mascotmon
    //        Mascotmon mon1 = new Mascotmon(Mascotmon.Name.SPARKY);
    //        Mascotmon mon2 = new Mascotmon(Mascotmon.Name.BULLY);
    //
    //        // Initialize BattleScenario object
    //        BattleScenario battleScenario = new BattleScenario(mon1, mon2);
    //
    //        // Initialize Environment
    //        battleScenario.battleWeather = new Environment(Environment.Weather.sunny);
    //
    //        // Force a quick fight
    //        battleScenario.mon2.stats.health = 69.375;
    //
    //        // Call method
    //        battleScenario.initiateBattle();
    //
    //        System.out.println("       testInitiateBattle");
    //        System.out.println("           " + "Expected mon1Stats.health: " + 90);
    //        System.out.println(
    //                "           " + "Actual mon1Stats.health: "
    //                + battleScenario.mon1Stats.health);
    //
    //        System.out.println("           " + "Expected weather: " + "sunny");
    //        System.out.println("           " + "Actual weather: "
    //                + battleScenario.battleWeather.weather.toString());
    //
    //        System.out.println("           " + "Expected mon1.name: " + "SPARKY");
    //        System.out.println(
    //                "           " + "Actual mon1.name: " + battleScenario.mon1.name.toString());
    //        System.out.println("           " + "Expected mon1.description: " + "The Sun Devil");
    //        System.out.println(
    //                "           " + "Actual mon1.description: "
    //                + battleScenario.mon1.description);
    //
    //        System.out.println("           " + "Expected mon1.name: " + "BULLY");
    //        System.out.println(
    //                "           " + "Actual mon1.name: " + battleScenario.mon2.name.toString());
    //        System.out.println("           " + "Expected mon1.description: " + "The Bull Dog");
    //        System.out.println(
    //                "           " + "Actual mon1.description: "
    //                + battleScenario.mon2.description);
    //
    //        System.out.println("           " + "Expected winner: " + "SPARKY");
    //        System.out
    //                .println("           " + "Actual winner: "
    //                + battleScenario.winner.name.toString());
    //        System.out.println("           " + "Expected winner.health: " + 90);
    //        System.out.println("           " + "Actual winner: "
    //        + battleScenario.winner.stats.health);
    //
    //        Assert.assertEquals(90, battleScenario.mon1Stats.health, 0);
    //
    //        Assert.assertEquals("sunny", battleScenario.battleWeather.weather.toString());
    //
    //        Assert.assertEquals("SPARKY", battleScenario.mon1.name.toString());
    //        Assert.assertEquals("The Sun Devil", battleScenario.mon1.description);
    //
    //        Assert.assertEquals("BULLY", battleScenario.mon2.name.toString());
    //        Assert.assertEquals("The Bull Dog", battleScenario.mon2.description);
    //
    //        Assert.assertEquals("SPARKY", battleScenario.winner.name.toString());
    //        Assert.assertEquals(90, battleScenario.winner.stats.health, 0);
    //    }

    // TEST Stats Constructor
    /*############################################################################################*/

    /**
     * testStatsConstructorAlbert Test Stats constructor with Albert.
     */
    @Test
    public void testStatsConstructorAlbert() {
        // Initialize one Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.ALBERT);

        // Initialize Stats object
        Stats stats = new Stats(mon.name);

        System.out.println("       testStatsConstructorAlbert");
        System.out.println("           " + "Expected stats.attack: " + 60);
        System.out.println("           " + "Actual stats.attack: " + stats.attack);

        System.out.println("           " + "Expected stats.defense: " + 40);
        System.out.println("           " + "Actual stats.defense: " + stats.defense);

        System.out.println("           " + "Expected stats.health: " + 100);
        System.out.println("           " + "Actual stats.health: " + stats.health);

        Assert.assertEquals(60, stats.attack, 0);
        Assert.assertEquals(40, stats.defense, 0);
        Assert.assertEquals(100, stats.health, 0);
    }

    /**
     * testStatsConstructorRalphie Test Stats constructor with Ralphie.
     */
    @Test
    public void testStatsConstructorRalphie() {
        // Initialize one Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.RALPHIE);

        // Initialize Stats object
        Stats stats = new Stats(mon.name);

        System.out.println("       testStatsConstructorRalphie");
        System.out.println("           " + "Expected stats.attack: " + 30);
        System.out.println("           " + "Actual stats.attack: " + stats.attack);

        System.out.println("           " + "Expected stats.defense: " + 65);
        System.out.println("           " + "Actual stats.defense: " + stats.defense);

        System.out.println("           " + "Expected stats.health: " + 105);
        System.out.println("           " + "Actual stats.health: " + stats.health);

        Assert.assertEquals(30, stats.attack, 0);
        Assert.assertEquals(65, stats.defense, 0);
        Assert.assertEquals(105, stats.health, 0);
    }

    /**
     * testStatsConstructorSparky Test Stats constructor with Sparky.
     */
    @Test
    public void testStatsConstructorSparky() {
        // Initialize one Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.SPARKY);

        // Initialize Stats object
        Stats stats = new Stats(mon.name);

        System.out.println("       testStatsConstructorSparky");
        System.out.println("           " + "Expected stats.attack: " + 70);
        System.out.println("           " + "Actual stats.attack: " + stats.attack);

        System.out.println("           " + "Expected stats.defense: " + 40);
        System.out.println("           " + "Actual stats.defense: " + stats.defense);

        System.out.println("           " + "Expected stats.health: " + 90);
        System.out.println("           " + "Actual stats.health: " + stats.health);

        Assert.assertEquals(70, stats.attack, 0);
        Assert.assertEquals(40, stats.defense, 0);
        Assert.assertEquals(90, stats.health, 0);
    }

    /**
     * testStatsConstructorBully Test Stats constructor with Bully.
     */
    @Test
    public void testStatsConstructorBully() {
        // Initialize one Mascotmon
        Mascotmon mon = new Mascotmon(Mascotmon.Name.BULLY);

        // Initialize Stats object
        Stats stats = new Stats(mon.name);

        System.out.println("       testStatsConstructorBully");
        System.out.println("           " + "Expected stats.attack: " + 40);
        System.out.println("           " + "Actual stats.attack: " + stats.attack);

        System.out.println("           " + "Expected stats.defense: " + 40);
        System.out.println("           " + "Actual stats.defense: " + stats.defense);

        System.out.println("           " + "Expected stats.health: " + 110);
        System.out.println("           " + "Actual stats.health: " + stats.health);

        Assert.assertEquals(40, stats.attack, 0);
        Assert.assertEquals(40, stats.defense, 0);
        Assert.assertEquals(110, stats.health, 0);
    }
    /*############################################################################################*/
    /*############################################################################################*/
}