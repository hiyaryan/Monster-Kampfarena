package kampfarena;

import factory.product.Trainer;
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
    private Trainer trainer;

    public Registration() {
        System.out.println("A trainer has just registered!\n");
    }

    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Register sets the trainer in this registration.
     */
    public Registration register(Trainer trainer) {
        if(mediator.getWildeLand().whatTimeIsIt().contains("1t") || mediator.getWildeLand().whatTimeIsIt().contains("2t")) {
            this.trainer = trainer;
            return this;

        } else {
            System.out.println("Sorry we're closed!");
            System.out.println("   Registration is open everyday from 1t-2t.");
            return null;
        }
    }
}
