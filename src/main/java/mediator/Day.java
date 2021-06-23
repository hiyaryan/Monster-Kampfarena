package mediator;

public class Day extends WildeLand {
    public Day() {

    }

    @Override
    public String whatTimeIsIt(String date) {
        return "Day, " + date;
    }
}
