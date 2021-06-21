package factory;

import java.util.HashMap;
import java.util.Map;

public class ConcreteProductTrainer extends AbstractProductTrainer {
    public ConcreteProductTrainer() {
        super.codex = new HashMap<>();
    }

    @Override
    public ConcreteProductTrainer getTrainer() {
        return this;
    }

    @Override
    public Map<String, ConcreteProductMonster> getCodex() {
        return super.codex;
    }

    @Override
    public void setCodex(String name, ConcreteProductMonster monster) {
        super.codex.put(name, monster);
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
    public String listMonsters() {
        StringBuilder sb = new StringBuilder("--- " + super.name + " CODEX ---\n");

        for(String name : codex.keySet()) {
            sb.append(" -> ").append(super.codex.get(name).getName()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getStats() {
        StringBuilder sb = new StringBuilder(">>> " + super.name + " <<<\n");
        sb.append("Type: ").append("Trainer").append("\n");
        sb.append("HP:   ").append(super.hp).append("\n");
        sb.append("MP:   ").append(super.mp).append("\n");

        return sb.toString();
    }
}
