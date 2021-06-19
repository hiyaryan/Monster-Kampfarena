package factory;

// http://www.blackwasp.co.uk/AbstractFactory.aspx
public abstract class AbstractFactory {
    public AbstractFactory() {
        System.out.println("Declaring an abstract factory...");
    }

    public abstract AbstractProductTrainer createTrainer();
    public abstract AbstractProductMonster createMonster();
}
