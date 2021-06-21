package decorator;

/**
 * Initial (Initial.java)
 *
 * This class decorates a monster into a Code-a-mon giving them a type.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 21, 2021
 */
public class Initial extends CodeAMonDecorator {
    public Initial(CodeAMon codeAMon) {
        super(codeAMon);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println(getMonster().getName() + " has become code-a-mon!");
    }

//    @Override
//    public void giveMonsterType(ConcreteProductMonster monster) {
//        String name = monster.getName();
//
//        switch (name) {
//            case "Wale":
//                monster.setType("Water");
//                break;
//            case "Kaht":
//                monster.setType("Poison");
//                break;
//            default:
//                System.out.println("Unidentified code-a-mon.");
//                break;
//        }
//    }
}