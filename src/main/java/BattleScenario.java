public class BattleScenario {

    protected Mascotmon mon1;
    protected Mascotmon mon2;
    protected Stats mon1Stats;
    protected Environment battleWeather;

    protected Mascotmon winner; // Add for Whitebox Test: testInitiateBattle

    public BattleScenario(Mascotmon pMon1, Mascotmon pMon2) {
        setMon1(pMon1);
        setMon2(pMon2);
    }

    /**
     * Sets environment of the battlefield, and sets buff/debuff modifiers for all Mascotmons on the
     * field. If the Mascotmon's Type is buffed by the environment,they receive a 25% multiplier to
     * their attack and defense stat. If the Mascotmon's Type is debuffed by the environment, they
     * receive a reduction of 25% to their attack and defense stat.
     *
     * @param pWeather is the weather enum to use from Environment class
     */
    public void setEnvironment(Environment.Weather pWeather) {
        battleWeather = new Environment(pWeather);
    }

    /**
     * Initiate battle.
     */
    public void initiateBattle() {

        // initiate stats for mon1 and mon2
        mon1Stats = new Stats(mon1.name);
        System.out.println("Woooo: " + mon1Stats.health);

        System.out.println("\nWelcome everyone to the Mascotmon training arena!");
        System.out.println("It is a " + battleWeather.weather.toString().toLowerCase()
                + " day here at Frank Kush Field");
        System.out.println("Today, on the attacking team we have " + mon1.name + " "
                + mon1.description);
        System.out.println("Their opponent, on the defending team is " + mon2.name + " "
                + mon2.description);
        System.out.println(mon2.name + " prepares for the incoming attack");

        winner = fight();
        System.out.println(winner.name + " has won with " + winner.stats.health + " health left");
    }

    /**
     * Sample fight scenario of two rounds. Each Mascotmon uses one random attack per round; this
     * attack multiplier is used to calculate damage output against opposing mascotmon. NOTE:
     * Checkout commented line 101 to view this method without whitespace for CFG
     */
    public Mascotmon fight() {
        int round = 1;
        double damage1;
        double damage2;

        Attack attack1;
        Attack attack2;

        while (true) {
            //Mon 1's turn:
            System.out.println("\n" + mon1.name + " launches an attack against " + mon2.name + "!");
            attack1 = mon1.attack();

            //Calculate damage:
            damage1 = calculateDamage(attack1, mon1, mon2);
            System.out.println(damage1 + " damage dealt");

            //Adjust mon2's health:
            mon2.stats.health = mon2.stats.health - damage1;
            System.out.println(mon2.name + " has " + mon2.stats.health + " health left");

            //Battle terminating condition:
            if (mon2.stats.health <= 0.0) {
                System.out.println(mon2.name + " has fainted in round " + round);
                return mon1;
            }

            //Mon 2's turn:
            System.out.println("\n" + mon2.name + " launches an attack against " + mon1.name + "!");
            attack2 = mon2.attack();

            //Calculate damage:
            damage2 = calculateDamage(attack2, mon2, mon1);
            System.out.println(damage2 + " damage dealt");

            //Adjust mon1's health:
            mon1.stats.health = mon1.stats.health - damage2;
            System.out.println(mon1.name + " has " + mon1.stats.health + " health left");

            //Battle terminating condition:
            if (mon1.stats.health <= 0.0) {
                System.out.println(mon1.name + " has fainted in round " + round);
                return mon2;
            }
            round++;
        } //end while
    }

    public void setMon1(Mascotmon pMon) {
        mon1 = pMon;
    }

    public void setMon2(Mascotmon pMon) {
        mon2 = pMon;
    }

    /**
     * TO DO: Implement for Assignment 3 This method implements the calculation of damage for one
     * specific attack. One monster attacks with the given damage, the dealt damage is then
     * calculated through totalDamage = (pAttackDamage * pAttacker.attack * pAttacker.weatherBonus *
     * pAttacker.typeBonus) - (pDefender.stats.defense * pDefender.weatherBonus *
     * pDefender.typeBonus) If the initial pAttackDamage is 0, then the damage dealt is 0. If the
     * totalDamage calculated is negative, the totalDamage dealt should be 1. Any positive value is
     * the total damage dealt. Weather bonus: see the Environment which you can assume is correct.
     * You need to check though if the weather bonus is applied correctly, since maybe the method
     * does not use the environment correctly. or debuffed based on the weather. EG. fire monsters
     * have a stat advantage of +25% in sunny weather while they have a stat disadvantage of -25% in
     * the rain. If the attack chosen, matches the monsters Type, the attacker will get an extra 20%
     * on its attack. Type bonus: Certain monsters have an attack bonus against others: Fire against
     * Water: Water gains 25% while Fire looses 25% Fire against Ground: Fire gains 25% while Ground
     * looses 25% Ground against Water: Ground gains 25% while Water looses 25% Normal mon: never
     * gain any Type bonus and are weaker during droughts. These bonuses do not stack up, they are
     * just applied for every attack.
     *
     * @param pAttack   is the attack value given to the method where that attack value is based on
     *                  the monsters damage value
     * @param pAttacker the attacking monster
     * @param pDefender the defending monster (the defending monster will never get damage) to
     *                  calculate damage output.
     * @return total damage output
     */
    public double calculateDamage(Attack pAttack, Mascotmon pAttacker, Mascotmon pDefender) {
        double totalDamage;

        // If pAttack = 0; totalDamage = 0
        if (pAttack.damage == 0) {
            totalDamage = 0;

            return totalDamage;
        }

        // Set bonuses for attacker
        switch (pAttacker.name) {
            case ALBERT:
                if (pAttack.type.equals("Water")) {
                    pAttacker.attackBonus = 1.25;

                    if (pDefender.name == Mascotmon.Name.SPARKY) {
                        pAttacker.typeBonus = 1.25;
                    }
                }

                if (battleWeather.weather == Environment.Weather.sunny) {
                    pAttacker.weatherBonus = 0.75;

                } else if (battleWeather.weather == Environment.Weather.rainy) {
                    pAttacker.weatherBonus = 1.25;
                }

                break;
            case RALPHIE:
                if (pAttack.type.equals("Ground")) {
                    pAttacker.attackBonus = 1.25;

                    if (pDefender.name == Mascotmon.Name.ALBERT) {
                        pAttacker.typeBonus = 1.25;
                    }
                }

                if (battleWeather.weather == Environment.Weather.drought) {
                    pAttacker.weatherBonus = 1.25;
                }

                break;
            case SPARKY:
                if (pAttack.type.equals("Fire")) {
                    pAttacker.attackBonus = 1.25;

                    if (pDefender.name == Mascotmon.Name.RALPHIE) {
                        pAttacker.typeBonus = 1.25;
                    }
                }

                if (battleWeather.weather == Environment.Weather.rainy) {
                    pAttacker.weatherBonus = 0.75;

                } else if (battleWeather.weather == Environment.Weather.sunny) {
                    pAttacker.weatherBonus = 1.25;
                }

                break;
            case BULLY:
                if (battleWeather.weather == Environment.Weather.drought) {
                    pAttacker.weatherBonus = 0.75;
                }

                break;
            default:

                break;
        }

        // Calculate attack based on bonuses applied to Attacker
        double attack = pAttack.damage * pAttacker.attackBonus * pAttacker.weatherBonus
                * pAttacker.typeBonus;

        // Set bonuses for defender
        switch (pDefender.name) {
            case ALBERT:
                if (pAttack.type.equals("Ground")) {
                    pDefender.typeBonus = 0.75;
                }

                if (battleWeather.weather == Environment.Weather.sunny) {
                    pDefender.weatherBonus = 0.75;

                } else if (battleWeather.weather == Environment.Weather.rainy) {
                    pDefender.weatherBonus = 1.25;
                }
                break;
            case RALPHIE:
                if (pAttack.type.equals("Fire")) {
                    pDefender.typeBonus = 0.75;
                }

                if (battleWeather.weather == Environment.Weather.drought) {
                    pDefender.weatherBonus = 1.25;
                }

                break;
            case SPARKY:
                if (pAttack.type.equals("Water")) {
                    pDefender.typeBonus = 0.75;
                }

                if (battleWeather.weather == Environment.Weather.rainy) {
                    pDefender.weatherBonus = 0.75;

                } else if (battleWeather.weather == Environment.Weather.sunny) {
                    pDefender.weatherBonus = 1.25;
                }

                break;
            case BULLY:
                if (battleWeather.weather == Environment.Weather.drought) {
                    pDefender.weatherBonus = 0.75;
                }

                break;
            default:

                break;
        }

        // Calculate defense based on bonuses applied to Defender
        double defense = pDefender.stats.defense * pDefender.weatherBonus * pDefender.typeBonus;

        // Calculate total damage
        totalDamage = attack - defense;

        // If totalDamage is less than 0, totalDamage = 1
        if (totalDamage < 0) {
            totalDamage = 1;

            return totalDamage;

            // If totalDamage > x, where x > 0, totalDamage = x
        } else {
            return totalDamage;
        }
    }
}