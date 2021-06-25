package kampfarena;

import factory.product.Trainer;
import mediator.Mediator;
import mediator.WildeLandMediator;

import java.util.HashMap;
import java.util.LinkedList;
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
    Queue<Registration> registry;

    private Kampfarena() {
        System.out.println("\n   An arena has been built off in the distance...");
        registry = new LinkedList<>();
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

    /**
     * Register trainers for battle.
     *
     * @param trainers HashMap
     */
    public void registerForBattle(HashMap<String, Trainer> trainers) {
        if(isRegistrationOpen()) {

            boolean alreadyRegistered = false;
            for(String name : trainers.keySet()) {

                for(Registration r : registry) {
                    if (r.getTrainer().getName().contains(name)) {
                        alreadyRegistered = true;
                        break;
                    }
                }

                if(!alreadyRegistered) {
                    Registration registration = new Registration();
                    registry.add(registration.register(trainers.get(name)));

                } else {
                    System.out.println(name + " is already registered for the next battle.");
                }
            }
        }
    }

    /**
     * Initiate a new battle.
     */
    public void initiateBattle() throws InterruptedException {
        if(mediator.getWildeLand().whatTimeIsIt().contains("1t") || mediator.getWildeLand().whatTimeIsIt().contains("2t")) {
            if(registry.size() >= 2) {
                new Battle(registry).battle();

            } else {
                System.out.println("At least two trainers must be registered to commence a battle.");
                System.out.println("   Currently there is " + registry.size() + " trainer registered.\n");
            }

        } else {
            System.out.println("The arena is open from 1t-2t.");
            System.out.println("   Get some rest!\n");
        }
    }
}
