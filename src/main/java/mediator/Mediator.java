package mediator;

// http://www.blackwasp.co.uk/Mediator.aspx
public interface Mediator {
    World mediateTime(int time);

    int getDay();

    int getTime();

    World getWorld();
}
