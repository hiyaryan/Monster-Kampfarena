package mediator;

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
        return "Day, " + date;
    }

    /**
     * This method is an implementation of the Environment decorator method.
     *
     * @return Day
     */
    @Override
    public WildeLand getEnvironment() {
        return this;
    }

    @Override
    public String howIsTheWeather(String time) {
        return "Today's forecast: \n   " + time + " neutral";
    }
}
