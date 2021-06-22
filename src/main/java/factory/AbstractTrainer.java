package factory;

import decorator.CodeAMon;

import java.util.Map;

public abstract class AbstractTrainer {
    protected String name;
    protected Map<String, CodeAMon> codex;

    protected int hp;
    protected int mp;

    public abstract Trainer getTrainer();

    public abstract Map<String, CodeAMon> getCodex();
    public abstract void setCodex(String name, CodeAMon codeAMon);

    public abstract CodeAMon formBond(Monster monster);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract int getHP();
    public abstract void setHP(int hp);

    public abstract int getMP();
    public abstract void setMP(int mp);

    public abstract String listMonsters();

    public abstract String getStats();
}
