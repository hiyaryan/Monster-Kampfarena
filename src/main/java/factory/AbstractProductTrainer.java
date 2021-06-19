package factory;

import java.util.Map;

public abstract class AbstractProductTrainer {
    protected String name;
    protected Map<String, ConcreteProductMonster> codex;

    protected int hp;
    protected int mp;

    public abstract ConcreteProductTrainer getTrainer();

    public abstract Map<String, ConcreteProductMonster> getCodex();
    public abstract void setCodex(String name, ConcreteProductMonster monster);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract int getHP();
    public abstract void setHP(int hp);

    public abstract int getMP();
    public abstract void setMP(int mp);

    public abstract String listMonsters();

    public abstract String getStats();
}
