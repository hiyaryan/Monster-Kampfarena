package factory;

// http://www.blackwasp.co.uk/AbstractFactory.aspx
public abstract class AbstractFactory {
    public abstract AbstractProductTrainer createTrainer();
    public abstract AbstractProductMonster createMonster();
}
