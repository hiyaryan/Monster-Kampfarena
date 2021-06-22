package factory;

public abstract class AbstractMonster {
    private String name;
    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int lvl;
    protected boolean isTamed;
    private Trainer trainer;

    public abstract String statsToString();

    public AbstractMonster() {
        this.isTamed = false;
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

    public int getMaxHp() {
        return this.maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMp() {
        return this.mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getMaxMp() {
        return this.maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public boolean isTamed() {
        return !this.isTamed;
    }

    public void isTamed(boolean tamed) {
        this.isTamed = tamed;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public int getLvl() {
        return this.lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
