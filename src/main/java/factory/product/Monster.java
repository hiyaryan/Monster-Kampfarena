package factory.product;

import decorator.monster.CodeAMon;
import decorator.monster.MonsterDecorator;
import decorator.wildeland.Weather;

import java.util.HashMap;
import java.util.Random;

/**
 * Monster (Monster.java)
 *
 * This class is a ConcreteProduct created from the AbstractFactory class and stores an initial monster
 * which is the core of a Code-a-mon.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Monster extends AbstractMonster implements CodeAMon {
    /**
     * Monster constructor accepts a name and builds a wild monster.
     *
     * @param name String
     */
    public Monster(String name) {
        if (name != null) {
            setName(name);
            setLvl(1);

            setMaxHp(new Random().nextInt(99) + 30);
            setMaxMp(new Random().nextInt(30) + 15);

            setHp(getMaxHp());
            setMp(getMaxMp());

            setStrength(new Random().nextInt(20) + 10);
            setMagic(new Random().nextInt(10) + 15);
            setDefense(new Random().nextInt(10) + 5);
            setHit(new Random().nextInt(15) + 5);
            setEvasion(new Random().nextInt(5) + 1);
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
        sb.append("HP:   ").append(super.getHp()).append("/").append(super.getMaxHp()).append("\n");
        sb.append("MP:   ").append(super.getMp()).append("/").append(super.getMaxMp()).append("\n");
        sb.append("STR:  ").append(getStrength()).append("\n");
        sb.append("MAG:  ").append(getMagic()).append("\n");
        sb.append("DEF:  ").append(getDefense()).append("\n");
        sb.append("HIT:  ").append(getHit()).append("\n");
        sb.append("EVA:  ").append(getEvasion()).append("\n");

        return sb.toString();
    }

    @Override
    public HashMap<String, MonsterDecorator.Skill> getSkills() {
        return null;
    }

    @Override
    public MonsterDecorator.Type getType() {
        return null;
    }

    @Override
    public MonsterDecorator.Type getTypeWeakness() {
        return null;
    }

    @Override
    public Weather.Type getWeatherStrength() {
        return null;
    }

    @Override
    public Weather.Type getWeatherWeakness() {
        return null;
    }

    @Override
    public String listSkills() {
        return getName() + " has no skills.";
    }

    @Override
    public String listSkillsCompact() {
        return getName() + " has no skills.";
    }

    @Override
    public int setWeatherBonus(int bonus) {
        return 1;
    }

    @Override
    public int getWeatherBonus() {
        return 1;
    }

    @Override
    public void init(Trainer trainer) {
        if (!isWild()) {
            System.out.println(getName() + " is looking at " + trainer.getName()
                    + " suspiciously.\n");

        } else {
            System.out.println(trainer.getName() + " has spotted a wild " + getName() + "!");
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
