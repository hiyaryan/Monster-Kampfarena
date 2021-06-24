package mediator;

import decorator.wildeland.Environment;
import mediator.colleague.WildeLand;

/**
 * Mediator (Mediator.java)
 *
 * This interface is implemented in WildeLandMediator.java.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 23, 2021
 */
public interface Mediator {
    WildeLand mediateTime(int time);

    Environment mediateWeather();

    String getDate();

    int getDay();

    int getTime();

    WildeLand getWildeLand();
}
