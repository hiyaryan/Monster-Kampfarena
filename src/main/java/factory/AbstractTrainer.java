package factory;

import java.util.Map;

public abstract class AbstractTrainer {
    protected String name;
    protected Map<String, Monster> codex;

    protected int hp;
    protected int mp;

    public abstract Trainer getTrainer();

    public abstract Map<String, Monster> getCodex();
    public abstract void setCodex(String name, Monster monster);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract int getHP();
    public abstract void setHP(int hp);

    public abstract int getMP();
    public abstract void setMP(int mp);

    public abstract String listMonsters();

    public abstract String getStats();
}
