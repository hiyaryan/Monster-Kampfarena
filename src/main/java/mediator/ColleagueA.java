package mediator;

public class ColleagueA extends ColleagueBase {
    public ColleagueA(MediatorBase mediator) {
        super(mediator);
    }

    public void send() {
        System.out.println("ColleagueA sent message");
        mediator.SendMessage(this);
    }

    public void receive() {
        System.out.println("ColleagueA received message");
    }
}
