package decorator.monster;

import factory.product.Trainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Initial (Initial.java)
 *
 * This class decorates a monster into a Code-a-mon giving them a type and initial skills.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 21, 2021
 */
public class Initial extends MonsterDecorator {
    private String type;

    public Initial(CodeAMon codeAMon) {
        super(codeAMon);
        super.skills = new HashMap<>();
    }

    /**
     * The init method is the main method of this class. This method establishes a connection between trainer and
     * monster which awakens the monster to their code-a-mon potential.
     *
     * @param trainer Trainer
     */
    @Override
    public void init(Trainer trainer) {
        super.init(trainer);

        if (getMonster().isWild()) {
            getMonster().setTrainer(trainer);

            System.out.println("    "
                    + getMonster().getName()
                    + " has become a Code-a-mon!\n");

            boostStats();
            giveType();
            declareSkill();
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void boostStats() {
        System.out.println(getMonster().getName()
                + " has discovered its inner strength!");

        System.out.println("   +HP 1.2x");
        System.out.println("      +MP 1.2x\n");

        getMonster().setMaxHp((int) (1.2 * getMonster().getMaxHp()));
        getMonster().setMaxMp((int) (1.2 * getMonster().getMaxMp()));
    }

    /**
     * This method gives a newly awakened code-e-mon a type based on their original monster name.
     */
    @Override
    protected void giveType() {
        String name = getMonster().getName();

        switch (name) {
            case "Wale":
            case "Adle":
                setType(Type.WATER.toString());
                break;
            case "Kaht":
            case "Antt":
                setType(Type.BIO.toString());
                break;
            case "Pyth":
            case "Clie":
                setType(Type.BLIZZARD.toString());
                break;
            case "Jaxx":
            case "Serv":
                setType(Type.LIGHTNING.toString());
                break;
            case "Coco":
            case "Gith":
                setType(Type.FIRE.toString());
                break;
            case "Desi":
                setType(Type.DARK.toString());
                break;
            case "Exml":
                setType(Type.LIGHT.toString());
                break;
            default:
                System.out.println("Unidentified code-a-mon.");
                break;
        }

        System.out.println(name
                + "'s type is revealed.\n    "
                + getType() + "!\n");
    }

    /**
     * Initialize the new code-a-mon's first skill.
     */
    @Override
    protected void declareSkill() {
        String name = getMonster().getName();
        Skill skill = null;

        switch (name) {
            case "Wale":
                skill = initSkill("Splash", this.type, 4);
                break;
            case "Kaht":
                skill = initSkill("Scratch", this.type, 4);
                break;
            case "Pyth":
                skill = initSkill("Stare", Type.NONE.toString(), 2);
                break;
            case "Jaxx":
                skill = initSkill("Strike", Type.NONE.toString(), 2);
                break;
            case "Coco":
                skill = initSkill("Bounce", Type.NONE.toString(), 2);
                break;
            case "Antt":
                skill = initSkill("Bite", this.type, 4);
                break;
            case "Gith":
                skill = initSkill("Ignite", this.type, 8);
                break;
            case "Serv":
                skill = initSkill("Shock", this.type, 8);
                break;
            case "Clie":
                skill = initSkill("Flurry", this.type, 8);
                break;
            case "Desi":
                skill = initSkill("Expel", this.type, 16);
                break;
            case "Exml":
                skill = initSkill("Repel", this.type, 16);
                break;
            case "Adle":
                skill = initSkill("Mist", this.type, 8);
                break;
            default:
                System.out.println("Unidentified code-a-mon.");
                break;
        }

        if (skill != null) {
            setSkills(skill);

        } else {
            System.out.println("A skill cannot be set for an unidentified Code-a-mon.");
        }
    }

    /**
     * Declare the new code-a-mons first skill.
     *
     * @param name Skill name
     * @param type Skill type
     * @return New Skill object
     */
    public Skill initSkill(String name, String type, int cost) {
        return new Skill(name, type, cost);
    }

    /**
     * List the new code-a-mons-skills.
     *
     * @return String Skill list
     */
    @Override
    public String listSkills() {
        StringBuilder sb = new StringBuilder("--- " + getMonster().getName() + " Skills ---\n");

        for (Map.Entry<String, Skill> skill : skills.entrySet()) {
            sb.append(skill.getValue().getName())
                    .append(" [Type: ").append(skill.getValue().getType()).append("]\n");
        }

        return sb.toString();
    }

    /**
     * List the new code-a-mons-skills. -4 MP Scratch
     *
     * @return String Skill list
     */
    @Override
    public String listSkillsCompact() {
        StringBuilder sb = new StringBuilder("   === Skills ===\n");

        for (Map.Entry<String, Skill> skill : skills.entrySet()) {
            sb.append("   -").append(skill.getValue().getCost()).append(" MP ")
                    .append(skill.getValue().getName()).append("\n");
        }

        return sb.toString();
    }

    /**
     * Returns the stats of a code-a-mon.
     *
     * @return String stat list
     */
    @Override
    public String statsToString() {
        StringBuilder sb = new StringBuilder("   >>> " + getMonster().getName() + " <<<\n");
        sb.append("   Type: ").append(getType()).append("\n");
        sb.append("   Lvl:  ").append(getMonster().getLvl()).append("\n");
        sb.append("   HP:   ").append(getMonster().getHp()).append("/").append(getMonster().getMaxHp()).append("\n");
        sb.append("   MP:   ").append(getMonster().getMp()).append("/").append(getMonster().getMaxMp()).append("\n");

        return sb.toString();
    }
}