package factory;

import decorator.CodeAMon;
import decorator.Initial;

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
    public Map<String, CodeAMon> getCodex() {
        return super.codex;
    }

    @Override
    public void setCodex(String name, CodeAMon codeAMon) {
        super.codex.put(name, codeAMon);
    }

    /**
     * This method forms a contract between the trainer and the monster. Once
     * the contract has been formed, the monster becomes a code-a-mon and is
     * initialized with a type and set of skills.
     *
     * @param monster
     * @return
     */
    @Override
    public CodeAMon formBond(Monster monster) {
        CodeAMon codeAMon = new Initial(monster);
        codeAMon.init(this);

        setCodex(monster.name, codeAMon);
        return codeAMon;
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
            sb.append(super.codex.get(name).getStats()).append("\n");
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
