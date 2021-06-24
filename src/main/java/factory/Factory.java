package factory;

import factory.product.AbstractMonster;
import factory.product.AbstractTrainer;
import factory.product.Monster;
import factory.product.Trainer;

public class Factory extends AbstractFactory {
    @Override
    public AbstractTrainer createTrainer(String name) {
        return new Trainer(name);
    }

    @Override
    public AbstractMonster createMonster(String name) {
        return new Monster(name);
    }
}
