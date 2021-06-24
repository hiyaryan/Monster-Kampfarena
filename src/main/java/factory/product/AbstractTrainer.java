package factory.product;

import decorator.monster.CodeAMon;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTrainer {
    private String name;
    private Map<String, CodeAMon> codex;
    private int hp;
    private int mp;

    public final static int MAX_CODEX_SIZE = 6;

    public abstract String listMonsters();
    public abstract String statsToString();
    public abstract CodeAMon formBond(Monster monster);

    public AbstractTrainer() {
        this.codex = new HashMap<>();
    }

    public Map<String, CodeAMon> getCodex() {
        return codex;
    }

    public void setCodex(String name, CodeAMon codeAMon) {
        codex.put(name, codeAMon);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }
}
