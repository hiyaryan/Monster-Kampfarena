package mediator;

public abstract class World {
    public abstract String whatTimeIsIt();

    /**
     * Narrate the day.
     *
     * @param day     days in the Wilde Land
     * @param time    partitions the day
     * @param counter partitions the time
     */
    public void narrateDay(int day, int time, int counter) {
        switch (day) {
            case 0:
                narrateDayZero(time, counter);
                break;
            default:
                break;
        }
    }

    /**
     * Narrate day zero.
     *
     * @param time    partitions the day
     * @param counter partitions the time
     */
    public void narrateDayZero(int time, int counter) {
        switch (time) {
            case 1:
                switch (counter) {
                    case 3:
                        System.out.println("\n   Welcome to the Wilde Land!\n\n");
                        break;
                    case 5:
                        System.out.println("\n   Watch the most significant event of a new " + "trainers journey...");
                        System.out.println("      the forming of bonds between trainer and monster.\n\n");
                        break;
                    case 15:
                        System.out.println("\n   What a beautiful sight...");
                        System.out.println("        A monster awakening into Code-a-mon.\n\n");
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (counter) {
                    case 7:
                        System.out.println("\n   Hang tight everyone...");
                        System.out.println("        You will soon witness the might of the monsters of the Wilde Land.\n\n");
                        break;
                    case 13:
                        System.out.println("\n   Registration is now open at the Kampfarena!\n\n");
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
