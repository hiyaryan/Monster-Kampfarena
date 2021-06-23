package narrator;

import narrator.narrative.DayZero;

public class Narration implements Narrator {
    private int day;
    private int time;
    private int counter;

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
