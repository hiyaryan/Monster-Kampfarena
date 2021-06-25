package mediator.colleague;

public class Day extends WildeLand {
    public Day(String date) {
        super(date);
    }

    @Override
    public void isTheBattleGroundOpen() {
        super.kampfarena.isRegistrationOpen();
    }

    /**
     * This method is an implementation of the Mediator method.
     *
     * @return String "Day, day:time:counter"
     */
    @Override
    public String whatTimeIsIt() {
        return "Day " + super.date;
    }

    @Override
    public void howIsTheWeather(String date) {
        System.out.println("\nToday's forecast: \n   [" + whatTimeIsIt() + "] " + super.getWeather() + "\n");
    }
}
