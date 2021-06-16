package mediator;

public class ConcreteMediator extends MediatorBase {
    public ColleagueA colleagueA;

    public ColleagueB colleagueB;

    @Override
    public void SendMessage(ColleagueBase caller) {
        if(caller == colleagueA)
            colleagueB.receive();
        else {
            colleagueA.receive();
        }
    }
}
