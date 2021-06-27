package mediator;

import decorator.monster.CodeAMon;
import decorator.wildeland.Environment;
import decorator.wildeland.Weather;
import factory.product.Trainer;
import mediator.colleague.Day;
import mediator.colleague.Night;
import mediator.colleague.WildeLand;
import narrator.Narration;
import narrator.Narrator;
import singleton.Player;

import java.util.Map;

/**
 * WildeLandMediator (WildeLandMediator.java)
 *
 * This Runnable singleton class runs in a separate thread and keeps track of the time and weather. The methods in this
 * class are synchronized so that the rest of the program may pull only the attributes updated in this thread; in this
 * way this class can mediate between day and night where different actions may be performed.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 22, 2021
 */
public class WildeLandMediator implements Mediator, Runnable {
    private static WildeLandMediator mediator = new WildeLandMediator();
    private WildeLand wildeLand;
    private Environment environment;
    private String date;
    private int time;
    private int day;
    private int counter;

    private WildeLandMediator() {
        System.out.println("      The monsters of the Wilde Land are flocking.\n");

        this.date = "0d:1t:0c";
        this.time = 1;
        this.day = 0;
    }

    public static WildeLandMediator getMediator() {
        return mediator;
    }

    /**
     * This is the core method of this class which mediates between day and night depending on the time--i.e. this
     * method delivers to the overall application what actions may take place at any given time.
     *
     * @param time day:time:counter
     * @return return Night or Day
     */
    @Override
    public WildeLand mediateTime(int time) {
        if (time > 0 && time < 3) {
            return new Day(this.date);

        } else {
            return new Night(this.date);
        }
    }

    /**
     * This method mediates the weather. Depending on the time, it chooses which time, day or night, and decorates it
     * using the Weather class inside the decorator package.
     *
     * @return Environment
     */
    @Override
    public synchronized Environment mediateWeather() {
        return new Weather(this.wildeLand.whatTimeIsIt());
    }

    /**
     * This method mediates the bonuses for all the Code-a-mon inside all of the Trainers CODEXs. It compares the
     * current weather with a Code-a-mon's weather strength or weakness. If weak, the bonus is 0.5, if strong, 1.5, else
     * if no effect it remains 1 or returns to 1 if a previous bonus was applied. This is checked twice a day when the
     * run method checks in with the WildeLand class about the weather.
     */
    private void mediateWeatherBonuses(String weather) throws NullPointerException {
        Player player = Player.getPlayer();

        for (Map.Entry<String, Trainer> trainer : player.getTrainers().entrySet()) {
            for (Map.Entry<String, CodeAMon> codeAMon : trainer.getValue().getCodex().entrySet()) {

                if (codeAMon.getValue().getWeatherStrength().toString().equals(weather)) {
                    codeAMon.getValue().setWeatherBonus(1.5);

                    System.out.println(codeAMon.getValue().getCodeAMon().getName()
                            + " has gained a weather buff!");
                    System.out.println("   x1.5\n");

                } else if (codeAMon.getValue().getWeatherWeakness().toString().equals(weather)) {
                    codeAMon.getValue().setWeatherBonus(0.5);

                    System.out.println(codeAMon.getValue().getCodeAMon().getName()
                            + " has been debuffed by the weather!");
                    System.out.println("   x0.5\n");

                } else {
                    codeAMon.getValue().setWeatherBonus(1.0);
                }
            }
        }
    }

    @Override
    public synchronized WildeLand getWildeLand() {
        return this.wildeLand;
    }

    @Override
    public Environment getEnvironment() {
        return this.environment;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public synchronized int getDay() {
        return this.day;
    }

    @Override
    public synchronized int getTime() {
        return this.time;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    /**
     * This method prints the game time in the Wilde Land.
     */
    @Override
    public void printWildeLandTime() {
        System.out.println("CLOCK");
        System.out.println("   The current time is " + getWildeLand().whatTimeIsIt() + ".\n\n");

        if (this.time == 3 || this.time == 0) {
            System.out.println("   \"Tick. Tock.\" An impending battle awaits you.\n\n");
        }
    }

    /**
     * This is the main method of this class. This method keeps track of the time in a separate thread. It is invoked in
     * the Main class that runs the simulation in parallel. Depending on the time of this parallel thread determines
     * what actions may occur on the Main thread.
     *
     * Time is read in the following way day:time:counter
     *
     * Days: There are 8 days total--starting at day 0, ending at day 7. Times: There are 4 times throughout the
     * day--starting at time 0, ending at time 3 Counter: There are 16 counters throughout the day--starting at counter
     * 0, ending at counter 15
     *
     * After the 8th day the program is terminated. After the 4th time, the next day begins. After the 15th counter, the
     * next time begins. From 3-0 it is night, from 1-2 is is day.
     */
    @Override
    public void run() {
        Narrator narrator;

        while (day != 8) {
            long start = System.currentTimeMillis() / 1000;
            this.counter = 0;

            while (time != 4) {
                this.wildeLand = mediateTime(this.time);
                // System.out.println(this.wildeLand.whatTimeIsIt());

                // Check the weather and announcements twice a day
                // Set the weather bonuses for the Code-a-mon
                if ((this.time == 1 && counter == 0) || (this.time == 3 && counter == 0)) {
                    // Print the weather forecast
                    this.environment = mediateWeather();
                    this.wildeLand.howIsTheWeather(this.date);

                    try {
                        this.mediateWeatherBonuses(this.wildeLand.getWeather());

                    } catch (NullPointerException npe) {
                        System.out.println("\nThere are no Code-a-mon to buff.\n");
                    }

                    this.wildeLand.isTheBattleGroundOpen();
                }

                narrator = new Narration(day, time, counter);
                narrator.narrateDay();

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (counter == 15) {
                    this.time++;

                    counter = 0;
                    start = System.currentTimeMillis() / 1000;

                } else {
                    long stop = System.currentTimeMillis() / 1000;
                    counter = (int) (stop - start);
                }

                this.date = day + "d:" + time + "t:" + counter + "c";
            }

            this.time = 0;
            this.day++;
        }
    }
}
