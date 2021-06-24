package kampfarena;

import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;

import java.util.Queue;

/**
 * Kampfarena (Kampfarena.java)
 *
 * This class is battle ground. This is the main point at which a trainer may register
 * to fight in battle. The Kampfarena is only open from 1t-2t. During this time trainers
 * may battle or register for battle.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 24, 2021
 */
public class Kampfarena {
    protected static Kampfarena kampfarena = new Kampfarena();
    protected static Mediator mediator = WildeLandMediator.getMediator();
    protected Battle battle;
    Queue<Trainer> registry;

    private Kampfarena() {

    }

    public static Kampfarena getKampfarena() {
        return kampfarena;
    }

    public boolean isRegistrationOpen() {
        System.out.println("ANNOUNCEMENT");

        if(mediator.getWildeLand().whatTimeIsIt().contains("1t") || mediator.getWildeLand().whatTimeIsIt().contains("2t")) {
            System.out.println("   Registration is now open at the Kampfarena!\n\n");
            return true;
        } else {
            System.out.println("   Registration is open from 1t-2t.\n\n");
            return false;
        }
    }

    public void registerForBattle() {
        if(isRegistrationOpen()) {

        }
    }
}
