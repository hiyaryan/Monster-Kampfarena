import factory.AbstractFactory;
import factory.AbstractProductMonster;
import factory.AbstractProductTrainer;
import factory.ConcreteFactory;

public class Main {
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory();

        AbstractProductTrainer trainer = factory.createTrainer();
        AbstractProductMonster monster = factory.createMonster();
    }
}