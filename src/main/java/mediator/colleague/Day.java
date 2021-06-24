package mediator.colleague;

public class Day extends WildeLand {
    public Day() {

    }

    /**
     * This method is an implementation of the Mediator method.
     *
     * @return String "Day, day:time:counter"
     */
    @Override
    public String whatTimeIsIt(String date) {
        return "Day " + date;
    }

    @Override
    public String howIsTheWeather(String time) {
        return "Today's forecast: \n   " + time + " NEUTRAL\n";
    }

    @Override
    public String getWeather() {
        return null;
    }
}
