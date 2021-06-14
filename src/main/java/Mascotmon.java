import java.util.concurrent.ThreadLocalRandom;

public class Mascotmon {
    protected String description;
    protected Name name;
    protected Stats stats;
    protected double weatherBonus = 1.0;
    protected double typeBonus = 1.0;
    protected double attackBonus = 1.0;
    private int bufCounter = 0;

    /**
     * Constructor.
     *
     * @param name Name
     */
    public Mascotmon(Name name) {
        this.name = name;
        getStats();
        getDescription();
    }

    private void getStats() {
        stats = new Stats(name);
    }

    private void getDescription() {
        Description desc = new Description(name);
        this.description = desc.description;
    }

    /**
     * Method randomly determines an attack to use based on the defending Mascotmon and returns the
     * base damage of the attack selected. The self-buff (attackNumber 0) can only be used 3 times
     * during a battle.
     *
     * @return attack damage You can assume that this method uses the values it is supposed to use
     * and is correct.
     */
    public Attack attack() {
        int attackNumber;

        while (true) {
            // Make deterministic for Whitebox testing
            attackNumber = ThreadLocalRandom.current().nextInt(1, 2);
            if (attackNumber == 0 && bufCounter <= 2) {
                bufCounter++;
                break;
            } else if (attackNumber != 0) {
                break;
            }
        }

        String description = "";
        Attack attack = null;

        switch (name) {
            case ALBERT:
                if (attackNumber == 0) {
                    description = " uses Iron Scales, increasing defense stat by 10%";
                    stats.defense *= 1.10;
                    attack = new Attack(0, "None");
                } else if (attackNumber == 1) {
                    description = " uses Death Roll";
                    attack = new Attack(stats.attack, "Ground");
                } else if (attackNumber == 2) {
                    description = " uses Chomp";
                    attack = new Attack(stats.attack, "Normal");
                } else {
                    description = " uses Aqua Cannon";
                    attack = new Attack(stats.attack, "Water");
                }
                break;
            case RALPHIE:
                if (attackNumber == 0) {
                    description = " uses Iron Hide, increasing defense stat by 10%";
                    stats.defense *= 1.10;
                    attack = new Attack(0, "None");
                } else if (attackNumber == 1) {
                    description = " uses Ground Stomp";
                    attack = new Attack(stats.attack, "Ground");
                } else if (attackNumber == 2) {
                    description = " uses Headbutt";
                    attack = new Attack(stats.attack, "Normal");
                } else {
                    description = " uses Flaming Horn";
                    attack = new Attack(stats.attack, "Fire");
                }
                break;
            case SPARKY:
                if (attackNumber == 0) {
                    description = " uses Heat Up, increasing attack stat by 10%";
                    stats.attack *= 1.10;
                    attack = new Attack(0, "None");
                } else if (attackNumber == 1) {
                    description = " uses Inferno";
                    attack = new Attack(stats.attack, "Fire");
                } else if (attackNumber == 2) {
                    description = " uses Quick Attack";
                    attack = new Attack(stats.attack, "Normal");
                    System.out.println("Attack value: " + stats.attack);
                } else {
                    description = " uses Earthquake";
                    attack = new Attack(stats.attack, "Ground");
                }
                break;
            case BULLY:
                if (attackNumber == 0) {
                    description = " uses Sleep, increasing health stat by 10%";
                    double health = stats.health * 1.10;
                    stats.health = Math.round(health);
                    attack = new Attack(0, "None");
                } else if (attackNumber == 1) {
                    description = " uses Body Slam";
                    attack = new Attack(stats.attack, "Normal");
                } else if (attackNumber == 2) {
                    description = " uses Splash";
                    attack = new Attack(stats.attack, "Water");
                } else {
                    description = " uses Ground Pound";
                    attack = new Attack(stats.attack, "Ground");
                }
                break;
            default:
                break;
        }

        System.out.println(name.toString().toLowerCase() + description);
        return attack;
    }

    public enum Name {
        ALBERT, RALPHIE, SPARKY, BULLY
    }
}