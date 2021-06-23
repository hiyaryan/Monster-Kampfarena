package mediator;

public class Night extends WildeLand {
    public Night() {

    }

    @Override
    public String whatTimeIsIt(String date) {
        return "Night " + date;
    }
}
