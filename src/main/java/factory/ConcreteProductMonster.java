package factory;

import java.util.HashMap;
import java.util.Map;

public class ConcreteProductMonster extends AbstractProductMonster {
    public ConcreteProductMonster() {
        super.skills = new HashMap<>();
    }

    @Override
    public ConcreteProductMonster getMonster() {
        return this;
    }

    @Override
    public Map<String, ConcreteProductMonster> getSkills() {
        return super.skills;
    }

    @Override
    public void setSkills(String name, ConcreteProductMonster monster) {
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
        sb.append("Type: ").append(super.type).append("\n");
        sb.append("HP:   ").append(super.hp).append("\n");
        sb.append("MP:   ").append(super.mp).append("\n");

        return sb.toString();
    }
}
