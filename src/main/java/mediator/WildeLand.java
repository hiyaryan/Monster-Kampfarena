package mediator;

import decorator.wildeland.Environment;

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
    public abstract String whatTimeIsIt(String date);
}
