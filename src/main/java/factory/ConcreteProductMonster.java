package factory;

public class ConcreteProductMonster extends AbstractProductMonster {
    @Override
    public ConcreteProductMonster getMonster() {
        return this;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public void setName(String name) {
        super.name = name;
    }
}
