package factory;

import java.util.Map;

public abstract class AbstractMonster {

    protected String name;
    protected Map<String, Monster> skills;

    protected int hp;
    protected int mp;

    protected int lvl;
    protected String type;

    public abstract Map<String, Monster> getSkills();
    public abstract void setSkills(String name, Monster monster);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract int getHP();
    public abstract void setHP(int hp);

    public abstract int getMP();
    public abstract void setMP(int mp);

    public abstract int getLvl();
    public abstract void setLvl(int lvl);

    public abstract String getType();
    public abstract void setType(String type);

    public abstract String listSkills();

    public abstract String getStats();
}
