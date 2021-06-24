package factory;

import factory.product.AbstractMonster;
import factory.product.AbstractTrainer;
import factory.product.Monster;
import factory.product.Trainer;

public class Factory extends AbstractFactory {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int mp;
    protected int maxMp;
    protected int lvl;

    @Override
    public AbstractTrainer createTrainer(String name) {
        return new Trainer(name);
    }

    @Override
    public AbstractMonster createMonster(String name) {
        return new Monster(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return this.maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
