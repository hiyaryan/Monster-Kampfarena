package narrator;

import narrator.narrative.DayZero;

/**
 * Narrator (Narrator.java)
 *
 * This interface is implemented in Narration.java.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 23, 2021
 */
public interface Narrator {
    void narrateDay();

    DayZero getDayZero();
}
