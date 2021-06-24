package factory.product;

import decorator.monster.CodeAMon;

import java.util.Random;

/**
 * Monster (Monster.java)
 *
 * This class is a ConcreteProduct created from the AbstractFactory class
 * and is
 */
public class Monster extends AbstractMonster implements CodeAMon {
    public Monster(String name) {
        if(name != null) {
            setName(name);
            setLvl(1);

            setMaxHp(new Random().nextInt(99) + 30);
            setMaxMp(new Random().nextInt(30) + 15);

            setHp(getMaxHp());
            setMp(getMaxMp());
        }
    }

    @Override
    public Monster getMonster() {
        return this;
    }

    @Override
    public String statsToString() {
        StringBuilder sb = new StringBuilder(">>> " + super.getName() + " <<<\n");
        sb.append("Type: ").append("None").append("\n");
        sb.append("HP:   ").append(super.getMaxHp()).append("\n");
        sb.append("MP:   ").append(super.getMaxMp()).append("\n");

        return sb.toString();
    }

    @Override
    public String listSkills() {
        return getName() + " has no skills.";
    }

    @Override
    public void init(Trainer trainer) {
        if(!isWild()) {
            System.out.println(getName() + " is looking at " + trainer.getName()
                    + " suspiciously.\n");

        } else {
            System.out.println(trainer.getName() + " has spotted a wild " + getName() + "!");
        }
    }
}
