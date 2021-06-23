package narrator;

import narrator.narrative.DayZero;

/**
 * AbstractNarrator
 */
public interface Narrator {
    void narrateDay();
    DayZero getDayZero();
}
