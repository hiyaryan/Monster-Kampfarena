
package decorator;

import factory.ConcreteProductMonster;

public class Type extends DecoratorBase {
    public Type(ComponentBase component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("Modified");
    }

    @Override
    public void giveMonsterType(ConcreteProductMonster monster) {
        String name = monster.getName();

        switch (name) {
            case "Wale":
                monster.setType("Water");
                break;
            case "Kaht":
                monster.setType("Poison");
                break;
            default:
                System.out.println("Unidentified code-a-mon.");
                break;
        }
    }
}