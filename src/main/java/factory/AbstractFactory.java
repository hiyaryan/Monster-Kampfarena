package factory;

// http://www.blackwasp.co.uk/AbstractFactory.aspx
public abstract class AbstractFactory {
    public abstract AbstractTrainer createTrainer();
    public abstract AbstractMonster createMonster();
}
