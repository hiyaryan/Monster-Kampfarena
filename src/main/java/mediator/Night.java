package mediator;

public class Night extends WildeLand {
    public Night() {

    }

    /**
     * This method is an implementation of the Mediator method.
     *
     * @return String "Night, day:time:counter"
     */
    @Override
    public String whatTimeIsIt(String date) {
        return "Night " + date;
    }

    /**
     * This method is an implementation of the Environment decorator method.
     *
     * @return Night
     */
    @Override
    public WildeLand getEnvironment() {
        return this;
    }

    @Override
    public String howIsTheWeather(String time) {
        return "Tonight's forecast: \n   " + time + " neutral";
    }
}
