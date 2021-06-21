package factory;

import java.util.HashMap;
import java.util.Map;

public class Trainer extends AbstractTrainer {
    public Trainer() {
        super.codex = new HashMap<>();
    }

    @Override
    public Trainer getTrainer() {
        return this;
    }

    @Override
    public Map<String, Monster> getCodex() {
        return super.codex;
    }

    @Override
    public void setCodex(String name, Monster monster) {
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
        sb.append("Initial: ").append("Trainer").append("\n");
        sb.append("HP:   ").append(super.hp).append("\n");
        sb.append("MP:   ").append(super.mp).append("\n");

        return sb.toString();
    }
}
