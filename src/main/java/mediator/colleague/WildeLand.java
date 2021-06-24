package mediator.colleague;

import decorator.wildeland.Environment;
import mediator.Mediator;
import mediator.WildeLandMediator;

/**
 * WildeLand (WildeLand.java)
 *
 * This abstract class represents the world of the Wilde Land. It's abstract methods are implemented
 * in its children classes Day and Night which is mediated by WildeLandMediator class. This class
 * only implements the narration of the simulation based on the time.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 23, 2021
 */
public abstract class WildeLand implements Environment {
    Mediator mediator = WildeLandMediator.getMediator();
    String date;

    public abstract String whatTimeIsIt();

    public abstract String howIsTheWeather(String date);

    public WildeLand(String date) {
        this.date = date;
    }

    @Override
    public String getWeather() {
        return mediator.getEnvironment().getWeather().toLowerCase();
    }
}
