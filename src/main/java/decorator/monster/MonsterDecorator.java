package decorator.monster;

import factory.product.AbstractMonster;
import factory.product.Monster;
import factory.product.Trainer;

import java.util.HashMap;

public abstract class MonsterDecorator implements CodeAMon {
    private CodeAMon monster;
    protected HashMap<String, Skill> skills; // Initialized in child class Initial
    protected int weatherBonus; // A multiplier for weather bonuses
    protected int exp;

    protected abstract void boostStats();

    protected abstract void giveType();

    protected abstract void declareSkill();

    public MonsterDecorator(CodeAMon monster) {
        this.monster = monster;
        this.weatherBonus = 1;
    }

    @Override
    public AbstractMonster getMonster() {
        return (Monster) monster;
    }

    @Override
    public void init(Trainer trainer) {
        monster.init(trainer); // Invokes the init method in factory.Monster

        System.out.println("   " + trainer.getName() + " is trying to form a bond with "
                + monster.getMonster().getName() + ".\n");

        if (monster.getMonster().isWild()) {

            System.out.println(monster.getMonster().getName()
                    + " is forming a bond with "
                    + trainer.getName()
                    + "...");

        } else {
            System.out.println(monster.getMonster().getName()
                    + " is already bonded with "
                    + monster.getMonster().getTrainer().getName() + ".\n");
        }
    }

    @Override
    public HashMap<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Skill skill) {
        skills.put(skill.getName(), skill);
        ;
    }

    @Override
    public int setWeatherBonus(int bonus) {
        return bonus;
    }

    @Override
    public int getWeatherBonus() {
        return 0;
    }

    /**
     * This inner class provides the data structure for a skill.
     */
    public static class Skill {
        private final String name;
        private final Type type;
        private final int cost;

        /**
         * Skill constructor.
         *
         * @param name String
         * @param type String
         * @param cost int
         */
        public Skill(String name, Type type, int cost) {
            this.name = name;
            this.type = type;
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public Type getType() {
            return type;
        }

        public int getCost() {
            return cost;
        }
    }

    /**
     * This enum lists the elemental types.
     */
    public enum Type {
        FIRE,
        LIGHTNING,
        BLIZZARD,
        WATER,
        BIO,
        DARK,
        LIGHT,
        NONE
    }
}