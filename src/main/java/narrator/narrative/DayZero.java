package narrator.narrative;

public class DayZero {
    private int time;
    private int counter;

    public DayZero(int time, int counter) {
        this.time = time;
        this.counter = counter;
    }

    /**
     * Narrate day zero.
     */
    public void narrateDayZero() {
        switch (time) {
            case 1:
                narrateHourOne();
                break;
            case 2:
                narrateHourTwo();
                break;
            default:
                break;
        }
    }

    /**
     * Narrate hour one of day one.
     */
    public void narrateHourOne() {
        switch (counter) {
            case 3:
                System.out.println("\n   Welcome to the Wilde Land!\n\n");
                break;
            case 5:
                System.out.println("\n   Watch the most significant event of a new trainers journey...");
                System.out.println("        the forming of bonds between trainer and monster.\n\n");
                break;
            case 15:
                System.out.println("\n   What a beautiful sight...");
                System.out.println("        a monster awakening into Code-a-mon.\n\n");
                break;
            default:
                break;
        }
    }

    /**
     * Narrate hour two of day one.
     */
    public void narrateHourTwo() {
        switch (counter) {
            case 7:
                System.out.println("\n   It is about that time...");
                System.out.println("        for you to witness the might of the monsters of the Wilde Land.\n\n");
                break;
            case 13:
                System.out.println("\n   Registration is now open at the Kampfarena!\n\n");
                break;
            default:
                break;
        }
    }
}
