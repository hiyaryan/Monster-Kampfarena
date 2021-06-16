package mediator;

public abstract class ColleagueBase {
    protected  MediatorBase mediator;

    public ColleagueBase(MediatorBase mediator) {
        this.mediator = mediator;
    }
}
