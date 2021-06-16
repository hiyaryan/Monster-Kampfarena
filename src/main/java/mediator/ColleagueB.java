package mediator;

public class ColleagueB extends ColleagueBase {
    public ColleagueB(MediatorBase mediator) {
        super(mediator);
    }

    public void send() {
        System.out.println("ColleagueB sent message");
        mediator.SendMessage(this);
    }

    public void receive() {
        System.out.println("ColleagueB received message");
    }
}
