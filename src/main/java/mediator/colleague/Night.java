package mediator.colleague;

public class Night extends WildeLand {
    public Night(String date) {
        super(date);
    }

    @Override
    public void isTheBattleGroundOpen() {
        super.kampfarena.isRegistrationOpen();
    }

    /**
     * This method is an implementation of the Mediator method.
     *
     * @return String "Night, day:time:counter"
     */
    @Override
    public String whatTimeIsIt() {
        return "Night " + super.date;
    }

    @Override
    public void howIsTheWeather(String date) {
        System.out.println("\nTonight's forecast: \n   [" + whatTimeIsIt() + "] " + super.getWeather() + "\n");
    }
}
