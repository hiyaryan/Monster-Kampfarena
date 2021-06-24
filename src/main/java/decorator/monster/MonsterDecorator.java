package decorator.monster;

import factory.product.AbstractMonster;
import factory.product.Monster;
import factory.product.Trainer;

import java.util.HashMap;

public abstract class MonsterDecorator implements CodeAMon {
    private CodeAMon monster;
    protected HashMap<String, Skill> skills; // initialized in Initial

    protected abstract void boostStats();
    protected abstract void giveType();
    protected abstract void declareSkill();

    public MonsterDecorator(CodeAMon monster) {
        this.monster = monster;
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

        if(monster.getMonster().isWild()) {

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
        skills.put(skill.getName(), skill);;
    }

    /**
     * This inner class provides the data structure for a skill
     */
    public static class Skill {
        private final String NAME;
        private final String TYPE;
        private int level;

        public Skill(String name, String type) {
            this.NAME = name;
            this.TYPE = type;
            this.level = 1;
        }

        public String getName() {
            return NAME;
        }

        public String getType() {
            return TYPE;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
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