package kampfarena;

import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;

import java.util.Queue;

public class Kampfarena {
    static Mediator mediator = WildeLandMediator.getMediator();
    protected Battle battle;
    Queue<Trainer> registry;

    public void openTheBattleGround() {
        if(mediator.getWildeLand().whatTimeIsIt().contains("1t") || mediator.getWildeLand().whatTimeIsIt().contains("2t")) {
            this.battle = Battle.initializeBattle();
        }
    }
}
