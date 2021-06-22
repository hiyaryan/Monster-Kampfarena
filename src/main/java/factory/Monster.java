package factory;

import decorator.CodeAMon;

/**
 * Monster (Monster.java)
 *
 * This class is a ConcreteProduct created from the AbstractFactory class
 * and is
 */
public class Monster extends AbstractMonster implements CodeAMon {
    @Override
    public Monster getMonster() {
        return this;
    }

    @Override
    public String statsToString() {
        StringBuilder sb = new StringBuilder("   >>> " + super.getName() + " <<<\n");
        sb.append("   Type: ").append("None").append("\n");
        sb.append("   HP:   ").append(super.getHp()).append("\n");
        sb.append("   MP:   ").append(super.getMp());

        return sb.toString();
    }

    @Override
    public String listSkills() {
        return getName() + " has no skills.";
    }

    @Override
    public void init(Trainer trainer) {
        System.out.println(getName() + " is wild.\n");
    }
}
