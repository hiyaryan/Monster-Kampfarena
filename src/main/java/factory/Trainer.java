package factory;

import decorator.CodeAMon;
import decorator.Initial;

import java.util.Random;

public class Trainer extends AbstractTrainer {
    public Trainer(String name) {
        if (name != null) {
            setName(name);

            setHp(new Random().nextInt(30) + 1);
            setMp(new Random().nextInt(15) + 1);
        }
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
        monster.isTamed(true);

        setCodex(monster.getName(), codeAMon);
        return codeAMon;
    }

    @Override
    public String listMonsters() {
        StringBuilder sb = new StringBuilder("--- " + getName() + " CODEX ---\n");

        for(String name : getCodex().keySet()) {
            sb.append(super.getCodex().get(name).statsToString()).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String statsToString() {
        StringBuilder sb = new StringBuilder(">>> " + getName() + " <<<\n");
        sb.append("Type: ").append("Trainer").append("\n");
        sb.append("HP:   ").append(getHp()).append("\n");
        sb.append("MP:   ").append(getMp()).append("\n");

        return sb.toString();
    }
}
