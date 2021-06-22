package factory;

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
