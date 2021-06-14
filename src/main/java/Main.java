public class Main {
    /**
     * Main.
     *
     * @param args String
     */
    public static void main(String[] args) {

        Mascotmon attacker1 = new Mascotmon(Mascotmon.Name.SPARKY);
        Mascotmon defender1 = new Mascotmon(Mascotmon.Name.ALBERT);

        //Create First battle scenario with two mons
        BattleScenario fight1 = new BattleScenario(attacker1, defender1);
        //Set the weather
        fight1.setEnvironment(Environment.Weather.sunny);
        //Initiate battle
        fight1.initiateBattle();
        System.out.println("This is the end of the training simulation");

        Mascotmon attacker2 = new Mascotmon(Mascotmon.Name.BULLY);
        Mascotmon defender2 = new Mascotmon(Mascotmon.Name.RALPHIE);

        //Create Second battle scenario with two mons
        BattleScenario fight2 = new BattleScenario(attacker2, defender2);
        //Set the weather
        fight2.setEnvironment(Environment.Weather.rainy);
        //Initiate battle
        fight2.initiateBattle();
        System.out.println("This is the end of the training simulation");
    }
}