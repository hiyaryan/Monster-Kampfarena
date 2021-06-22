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

//    @Override
//    public String getName() {
//        return super.name;
//    }
//
//    @Override
//    public void setName(String name) {
//        super.name = name;
//    }

//    @Override
//    public int getHP() {
//        return super.hp;
//    }
//
//    @Override
//    public void setHP(int hp) {
//        super.hp = hp;
//    }
//
//    @Override
//    public int getMP() {
//        return super.mp;
//    }
//
//    @Override
//    public void setMP(int mp) {
//        super.mp = mp;
//    }

//    @Override
//    public void setIsTamed(boolean isTamed) {
//        super.isTamed = isTamed;
//    }
//
//    @Override
//    public boolean isTamed() {
//        return super.isTamed;
//    }

//    @Override
//    public int getLvl() {
//        return super.lvl;
//    }
//
//    @Override
//    public void setLvl(int lvl) {
//        super.lvl = lvl;
//    }

    @Override
    public String statsToString() {
        StringBuilder sb = new StringBuilder(">>> " + super.getName() + " <<<\n");
        sb.append("Type: ").append("None").append("\n");
        sb.append("HP:   ").append(super.getHp()).append("\n");
        sb.append("MP:   ").append(super.getMp());

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
