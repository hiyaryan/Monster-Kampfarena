package narrator;

import narrator.narrative.DayZero;

/**
 * Narration (Narration.java)
 *
 * This class narrates the day based on the day:time:counter of the runnable class WildeLandMediator. WildLandMediator
 * initializes this class every second passing the three updated time telling attributes. This class then initializes
 * the narrative classes with a time:counter based on the day.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 23, 2021
 */
public class Narration implements Narrator {
    private int day;
    private int time;
    private int counter;

    /**
     * This constructor takes a dates components and updates its attributes which are used to tell the system when to
     * narrate.
     *
     * @param day int
     * @param time int
     * @param counter int
     */
    public Narration(int day, int time, int counter) {
        this.day = day;
        this.time = time;
        this.counter = counter;
    }

    /**
     * Narrate the day.
     */
    @Override
    public void narrateDay() {
        switch (day) {
            case 0:
                getDayZero().narrateDayZero();
                break;
            default:
                break;
        }
    }

    @Override
    public DayZero getDayZero() {
        return new DayZero(time, counter);
    }
}
