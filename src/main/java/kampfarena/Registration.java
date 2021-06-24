package kampfarena;

import mediator.Mediator;
import mediator.WildeLandMediator;

/**
 * Registration (Registration.java)
 *
 * This class provides the data structure for the registry.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 24, 2021
 */
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
