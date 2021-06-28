package decorator.monster;

import factory.product.AbstractMonster;
import factory.product.Monster;
import factory.product.Trainer;

import java.util.HashMap;

/**
 * MonsterDecorator (MonsterDecorator.java)
 *
 * MonsterDecorator decorates a monster or Code-a-mon by their level. A monster becoming a Code-a-mon is decorated with
 * the Initial child class, and Code-a-mon are further decorated when they evolve.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 21, 2021
 */
public abstract class MonsterDecorator implements CodeAMon {
    private CodeAMon codeAMon;
    protected HashMap<String, Skill> skills; // Initialized in child class Initial
    protected double weatherBonus; // A multiplier for weather bonuses: 0.5, 1, or 1.5
    protected int exp;

    protected abstract void boostStats();

    protected abstract void giveType();

    protected abstract void declareSkill();

    /**
     * Constructor builds the shared attributes between all evolutions.
     *
     * @param codeAMon Code-a-mon
     */
    public MonsterDecorator(CodeAMon codeAMon) {
        this.codeAMon = codeAMon;
        this.weatherBonus = 1;
    }

    @Override
    public AbstractMonster getMonster() {
        return (Monster) codeAMon;
    }

    @Override
    public void init(Trainer trainer) {
        codeAMon.init(trainer); // Invokes the init method in factory.Monster

        System.out.println("   " + trainer.getName() + " is trying to form a bond with "
                + codeAMon.getMonster().getName() + ".\n");

        if (codeAMon.getMonster().isWild()) {

            System.out.println(codeAMon.getMonster().getName()
                    + " is forming a bond with "
                    + trainer.getName()
                    + "...");

        } else {
            System.out.println(codeAMon.getMonster().getName()
                    + " is already bonded with "
                    + codeAMon.getMonster().getTrainer().getName() + ".\n");
        }
    }

    @Override
    public HashMap<String, Skill> getSkills() {
        return skills;
    }

    public void setSkills(Skill skill) {
        skills.put(skill.getName(), skill);
    }

    @Override
    public int getExp() {
        return exp;
    }

    @Override
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * This method is called after every battle. It checks how much EXP is stored in the exp attribute and levels up the
     * Code-a-mon based on the following logic.
     *
     * lvl++ if exp == 100 * lvl
     */
    @Override
    public void levelUp() {
        while (this.exp == 100 * codeAMon.getMonster().getLvl()) {
            this.exp = this.exp - 100 * codeAMon.getMonster().getLvl();

            codeAMon.getMonster().setLvl(codeAMon.getMonster().getLvl() + 1);
            System.out.println("\n" + codeAMon.getMonster().getName() + ", LEVEL UP!\n");

            improveStats();
            System.out.println(codeAMon.getMonster().statsToString());
        }
    }

    /**
     * This method works with levelUp method improving the stats of the code-a-mon based on the formula.
     *
     * For each stat: (Stat) + (Stat / lvl)
     */
    private void improveStats() {
        codeAMon.getMonster().setMaxHp(codeAMon.getMonster().getMaxHp()
                + (codeAMon.getMonster().getMaxHp() / codeAMon.getMonster().getLvl()));

        codeAMon.getMonster().setMaxMp(codeAMon.getMonster().getMaxMp()
                + (codeAMon.getMonster().getMaxMp() / codeAMon.getMonster().getLvl()));

        codeAMon.getMonster().setStrength(codeAMon.getMonster().getStrength()
                + (codeAMon.getMonster().getStrength() / codeAMon.getMonster().getLvl()));

        codeAMon.getMonster().setMagic(codeAMon.getMonster().getMagic()
                + (codeAMon.getMonster().getMagic() / codeAMon.getMonster().getLvl()));

        codeAMon.getMonster().setDefense(codeAMon.getMonster().getDefense()
                + (codeAMon.getMonster().getDefense() / codeAMon.getMonster().getLvl()));

        codeAMon.getMonster().setHit(codeAMon.getMonster().getHit()
                + (codeAMon.getMonster().getHit() / codeAMon.getMonster().getLvl()));

        codeAMon.getMonster().setEvasion(codeAMon.getMonster().getEvasion()
                + (codeAMon.getMonster().getEvasion() / codeAMon.getMonster().getLvl()));
    }

    @Override
    public void setWeatherBonus(double weatherBonus) {
        this.weatherBonus = weatherBonus;
    }

    @Override
    public double getWeatherBonus() {
        return this.weatherBonus;
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