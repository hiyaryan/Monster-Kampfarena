package factory;

import java.util.Map;

public abstract class AbstractProductTrainer {
    protected String name;
    protected Map<String, ConcreteProductMonster> codex;

    public abstract ConcreteProductTrainer getTrainer();

    public abstract Map<String, ConcreteProductMonster> getCodex();
    public abstract void setCodex(String name, ConcreteProductMonster monster);

    public abstract String getName();
    public abstract void setName(String name);
    
    public abstract String listMonsters();
}
