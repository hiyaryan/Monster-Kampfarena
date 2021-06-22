package decorator;

import factory.Monster;
import factory.Trainer;

import java.util.HashMap;

public abstract class MonsterDecorator implements CodeAMon {
    private CodeAMon monster;
    protected HashMap<String, Skill> skills;

    public MonsterDecorator(CodeAMon monster) {
        this.monster = monster;
    }

    @Override
    public Monster getMonster() {
        return (Monster) monster;
    }

    @Override
    public void init(Trainer trainer) {
        monster.init(trainer);

        System.out.println(monster.getMonster().getName()
                + " is forming a bond with "
                + trainer.getName()
                + "...");
    }

    public HashMap<String, Skill> getSkills() {
        return skills;
    }

    public abstract String getType();

    public abstract void setType(String type);

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