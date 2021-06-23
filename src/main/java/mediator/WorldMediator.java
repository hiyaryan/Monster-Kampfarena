package mediator;

/**
 * WorldMediator (WorldMediator.java)
 *
 * This singleton class runs in a separate thread and keeps track of the time and weather.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 22, 2021
 */
public class WorldMediator implements Mediator, Runnable {
    private static WorldMediator mediator = new WorldMediator();
    private World world;
    private int time;
    private int day;

    public static WorldMediator getMediator() {
        return mediator;
    }

    @Override
    public synchronized World mediateTime(int time) {
        if(time > 0 && time < 3) {
            return new Day();

        } else {
            return new Night();
        }
    }

    @Override
    public synchronized int getDay() {
        return this.day;
    }

    @Override
    public synchronized int getTime() {
        return this.time;
    }

    public synchronized void setWorld(World world) {
        this.world = world;
    }

    /**
     * This method returns a World object.
     *
     * @return World
     */
    @Override
    public synchronized World getWorld() {
        return this.world;
    }

    /**
     * This method keeps track of the time in a separate thread.
     */
    @Override
    public void run() {
        this.time = 1;
        this.day = 0;

        while(day != 8) {

            long start = System.currentTimeMillis() / 1000;
            int counter = 0;

            while (time != 7) {
                setWorld(mediateTime(this.time));
                world.narrateDay(day, time, counter);

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

//                System.out.println(day + "d:" + time + "t:" + counter + "c");
            }

            this.time = 0;
            this.day++;
        }
    }
}
