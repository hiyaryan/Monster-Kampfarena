package mediator;

import decorator.Environment;
import decorator.Weather;
import narrator.Narration;
import narrator.Narrator;

/**
 * WildeLandMediator (WildeLandMediator.java)
 *
 * This Runnable singleton class runs in a separate thread and keeps track of the time and weather.
 * The methods in this class are synchronized so that the rest of the program may pull only
 * the attributes updated in this thread; in this way this class can mediate between day and night
 * where different actions may be performed.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 22, 2021
 */
public class WildeLandMediator implements Mediator, Runnable {
    private static WildeLandMediator mediator = new WildeLandMediator();
    private WildeLand wildeLand;
    private String date;
    private int time;
    private int day;

    public WildeLandMediator() {
        this.date = "0d:1t:0c";
        this.time = 1;
        this.day = 0;
    }

    public static WildeLandMediator getMediator() {
        return mediator;
    }

    /**
     * This is the core method of this class which mediates between day and night depending
     * on the time--i.e. this method delivers to the overall application what actions may take
     * place at any given time.
     *
     * @param time day:time:counter
     * @return return Night or Day
     */
    @Override
    public synchronized WildeLand mediateTime(int time) {
        if(time > 0 && time < 3) {
            return new Day();

        } else {
            return new Night();
        }
    }

    @Override
    public Environment mediateWeather() {
        return new Weather(this.wildeLand, this.wildeLand.whatTimeIsIt(this.date));
    }


    @Override
    public synchronized WildeLand getWildeLand() {
        return this.wildeLand;
    }

    @Override
    public synchronized String getDate() {
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

    /**
     * This is the main method of this class. This method keeps track of the time in a separate thread.
     * It is invoked in the Main class that runs the simulation in parallel. Depending on the time of this
     * parallel thread determines what actions may occur on the Main thread.
     *
     * Time is read in the following way
     * day:time:counter
     *
     * Days: There are 8 days total--starting at day 0, ending at day 7.
     * Times: There are 4 times throughout the day--starting at time 0, ending at time 3
     * Counter: There are 16 counters throughout the day--starting at counter 0, ending at counter 15
     *
     * After the 8th day the program is terminated. After the 4th time, the next day begins. After
     * the 15th counter, the next time begins. From 3-0 it is night, from 1-2 is is day.
     */
    @Override
    public void run() {
        Narrator narrator;

        while(day != 8) {
            Environment environment;
            long start = System.currentTimeMillis() / 1000;
            int counter = 0;

            while (time != 4) {
                this.wildeLand = mediateTime(this.time);

                // Check the weather twice a day
                if((this.time == 1 && counter == 0) || (this.time == 3 && counter == 0)) {
                    environment = mediateWeather();
                    System.out.println(environment.howIsTheWeather(this.wildeLand.whatTimeIsIt(this.date)));
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
