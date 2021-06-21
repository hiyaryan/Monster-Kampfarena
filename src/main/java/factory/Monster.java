package factory;

import decorator.CodeAMon;

import java.util.HashMap;
import java.util.Map;

public class Monster extends AbstractMonster implements CodeAMon {
    public Monster() {
        super.skills = new HashMap<>();
    }

//    @Override
//    public Monster getMonster() {
//        return this;
//    }

    @Override
    public Monster getMonster() {
        return this;
    }

    @Override
    public Map<String, Monster> getSkills() {
        return super.skills;
    }

    @Override
    public void setSkills(String name, Monster monster) {
        super.skills.put(name, monster);
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public void setName(String name) {
        super.name = name;
    }

    @Override
    public int getHP() {
        return super.hp;
    }

    @Override
    public void setHP(int hp) {
        super.hp = hp;
    }

    @Override
    public int getMP() {
        return super.mp;
    }

    @Override
    public void setMP(int mp) {
        super.mp = mp;
    }

    @Override
    public int getLvl() {
        return super.lvl;
    }

    @Override
    public void setLvl(int lvl) {
        super.lvl = lvl;
    }

    @Override
    public String getType() {
        return super.type;
    }

    @Override
    public void setType(String type) {
        super.type = type;
    }

    @Override
    public String listSkills() {
        StringBuilder sb = new StringBuilder("--- " + super.name +  " Skills ---\n");

        for(String name : skills.keySet()) {
            sb.append(" -> ").append(super.skills.get(name).getName()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getStats() {
        StringBuilder sb = new StringBuilder(">>> " + super.name + " <<<\n");
        sb.append("Initial: ").append(super.type).append("\n");
        sb.append("HP:   ").append(super.hp).append("\n");
        sb.append("MP:   ").append(super.mp).append("\n");

        return sb.toString();
    }

    @Override
    public void operation() {
        System.out.println(name + " is wild.");
    }
}
