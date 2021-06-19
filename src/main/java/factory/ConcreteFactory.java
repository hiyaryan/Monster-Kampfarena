package factory;

public class ConcreteFactory extends AbstractFactory {
    @Override
    public AbstractProductTrainer createTrainer() {
        return new ConcreteProductTrainer();
    }

    @Override
    public AbstractProductMonster createMonster() {
        return new ConcreteProductMonster();
    }
}
