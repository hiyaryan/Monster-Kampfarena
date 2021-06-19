package factory;

public abstract class AbstractProductMonster {
    protected String name;

    public abstract ConcreteProductMonster getMonster();

    public abstract String getName();

    public abstract void setName(String name);
}
