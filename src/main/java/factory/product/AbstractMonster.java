package factory.product;

import factory.Factory;

public abstract class AbstractMonster extends Factory {
    private boolean isWild;
    private Trainer trainer;

    public abstract String statsToString();

    public boolean isWild() {
        return !this.isWild;
    }

    public void isWild(boolean isWild) {
        this.isWild = !isWild;
    }

    public Trainer getTrainer() {
        return this.trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
