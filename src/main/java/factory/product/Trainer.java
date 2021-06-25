package factory.product;

import decorator.monster.CodeAMon;
import decorator.monster.Initial;

import java.util.Random;

public class Trainer extends AbstractTrainer {
    public Trainer(String name) {
        if (name != null) {
            setName(name);
            setLvl(1);

            setMaxHp(new Random().nextInt(30) + 1);
            setMaxMp(new Random().nextInt(15) + 1);

            setHp(getMaxHp());
            setMp(getMaxMp());

            setStrength(new Random().nextInt(10) + 1);
            setMagic(new Random().nextInt(15) + 1);
            setDefense(new Random().nextInt(5) + 1);
            setHit(new Random().nextInt(5) + 1);
            setEvasion(new Random().nextInt(1) + 1);
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
        if(getCodex().size() != MAX_CODEX_SIZE) {

            CodeAMon codeAMon = new Initial(monster);

            if(codeAMon.getMonster().isWild()) {
                codeAMon.init(this);
                monster.isWild(false);

                setCodex(monster.getName(), codeAMon);
                System.out.println(codeAMon.listSkills());

            } else {
                // A trainer has tried to tame a monster already in their CODEX
                if (getCodex().containsKey(codeAMon.getMonster().getName())) {
                    System.out.println(codeAMon.getMonster().getName() + " is already in your CODEX!");

                } else {
                    // A trainer has tried to tame a monster already bonded with another trainer
                    System.out.println("Thieves shall not be tolerated at the Wilde Land.");
                    System.out.println("   -HP x0.75");
                    System.out.println("      -MP x0.75\n");
                    setHp((int) (getHp() * 0.75));
                    setMp((int) (getMp() * 0.75));
                    System.out.println(statsToString());
                }
            }

            return codeAMon;

        } else {
            return null;
        }
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
        sb.append("HP:   ").append(getHp()).append("/").append(getHp()).append("\n");
        sb.append("MP:   ").append(getMp()).append("/").append(getMp()).append("\n");
        sb.append("STR:  ").append(getStrength()).append("\n");
        sb.append("MAG:  ").append(getMagic()).append("\n");
        sb.append("DEF:  ").append(getDefense()).append("\n");
        sb.append("HIT:  ").append(getHit()).append("\n");
        sb.append("EVA:  ").append(getEvasion()).append("\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return getName();
    }
}
