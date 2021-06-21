package factory;

public class Factory extends AbstractFactory {
    @Override
    public AbstractTrainer createTrainer() {
        return new Trainer();
    }

    @Override
    public AbstractMonster createMonster() {
        return new Monster();
    }
}
