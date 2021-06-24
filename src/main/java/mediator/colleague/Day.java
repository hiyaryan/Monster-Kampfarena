package mediator.colleague;

public class Day extends WildeLand {
    public Day(String date) {
        super(date);
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
    public String howIsTheWeather(String date) {
        return "Today's forecast: \n   [" + whatTimeIsIt() + "] " + super.getWeather() + "\n";
    }
}
