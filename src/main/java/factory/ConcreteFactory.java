package factory;

public class ConcreteFactory extends AbstractFactory {
    public ConcreteFactory() {
        System.out.println("Instantiating a concrete factory...");
    }

    @Override
    public AbstractProductTrainer createTrainer() {
        return new ConcreteProductTrainer();
    }

    @Override
    public AbstractProductMonster createMonster() {
        return new ConcreteProductMonster();
    }
}
