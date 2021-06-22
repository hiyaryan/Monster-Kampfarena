package factory;

public abstract class AbstractMonster {

    private String name;

    private int hp;
    private int maxHp;

    private int mp;
    private int maxMp;

    private int lvl;

    protected boolean isTamed = false;

//    public abstract String getName();
//    public abstract void setName(String name);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    public abstract int getHP();
//    public abstract void setHP(int hp);


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

//    public abstract int getMP();
//    public abstract void setMP(int mp);

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

//    public abstract void setIsTamed(boolean isTamed);
//    public abstract boolean isTamed();

    public boolean isTamed() {
        return isTamed;
    }

    public void setTamed(boolean tamed) {
        isTamed = tamed;
    }


//    public abstract int getLvl();
//    public abstract void setLvl(int lvl);


    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public abstract String statsToString();
}
