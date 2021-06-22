package decorator;

import factory.AbstractMonster;
import factory.Monster;
import factory.Trainer;

import java.util.HashMap;

public abstract class MonsterDecorator implements CodeAMon {
    private CodeAMon monster;
    private Trainer trainer;
    protected HashMap<String, Skill> skills; // initialized in Initial

    public MonsterDecorator(CodeAMon monster) {
        this.monster = monster;
    }

    @Override
    public AbstractMonster getMonster() {
        return (Monster) monster;
    }

    @Override
    public void init(Trainer trainer) {
        if(monster.getMonster().isTamed()) {
            monster.init(trainer); // Invokes the init method in factory.Monster

            System.out.println(monster.getMonster().getName()
                    + " is forming a bond with "
                    + trainer.getName()
                    + "...");


        } else {
            System.out.println(monster.getMonster().getName()
                    + " is already bonded with "
                    + monster.getMonster().getTrainer().getName() + ".");
        }
    }

    public HashMap<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Skill skill) {
        skills.put(skill.getName(), skill);;
    }

    public abstract void giveMonsterType();

    public abstract void declareSkill();

    /**
     * This inner class provides the data structure for a skill
     */
    static class Skill {
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
}