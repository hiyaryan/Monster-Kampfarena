package factory.product;

import decorator.monster.CodeAMon;
import factory.Factory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrainer extends Factory {
    private Map<String, CodeAMon> codex;
    public static final int MAX_CODEX_SIZE = 6;

    public abstract String listMonsters();

    public abstract String listMonstersCompact();

    public abstract String statsToString();

    public abstract CodeAMon formBond(Monster monster);

    public AbstractTrainer() {
        this.codex = new HashMap<>();
    }

    public Map<String, CodeAMon> getCodex() {
        return this.codex;
    }

    public void setCodex(String name, CodeAMon codeAMon) {
        codex.put(name, codeAMon);
    }
}
