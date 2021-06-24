package kampfarena;

import mediator.Mediator;
import mediator.WildeLandMediator;

public class Registration {
    static Mediator mediator = WildeLandMediator.getMediator();

    /**
     *
     */
    public void register() {
        if(mediator.getWildeLand().whatTimeIsIt().contains("1t") || mediator.getWildeLand().whatTimeIsIt().contains("2t")) {

        } else {
            System.out.println("Sorry we're closed!");
            System.out.println("   Registration is open everyday from 1t-2t.");
        }
    }
}
