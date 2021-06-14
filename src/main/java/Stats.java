public class Stats {

    double attack;
    double defense;
    double health;

    /**
     * Constructor.
     *
     * @param name Name
     */
    public Stats(Mascotmon.Name name) {
        switch (name) {
            case ALBERT:
                attack = 60;
                defense = 40;
                health = 100;
                break;
            case RALPHIE:
                attack = 30;
                defense = 65;
                health = 105;
                break;
            case SPARKY:
                attack = 70;
                defense = 40;
                health = 90;
                break;
            case BULLY:
                attack = 40;
                defense = 40;
                health = 110;
                break;
            default:
                attack = 0;
                defense = 0;
                health = 0;
                break;
        }
    }
}