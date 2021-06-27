package singleton;

import decorator.monster.CodeAMon;
import factory.AbstractFactory;
import factory.Factory;
import factory.product.AbstractMonster;
import factory.product.AbstractTrainer;
import factory.product.Monster;
import factory.product.Trainer;
import kampfarena.BattleMenu;
import kampfarena.Kampfarena;
import mediator.WildeLandMediator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Player (Player.java)
 *
 * This class uses the Singleton design pattern simulating a client as a player.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Player {
    private static Player player = new Player();
    private static WildeLandMediator mediator = WildeLandMediator.getMediator();
    private Kampfarena kampfarena;
    private AbstractFactory factory;
    private HashMap<String, Trainer> trainers;
    private HashMap<String, Monster> monsters;
    /**
     * Instantiate a ConcreteFactory.
     */
    private Player() {
        System.out.println("A player has joined the simulation.");

        this.kampfarena = Kampfarena.getKampfarena();

        this.factory = new Factory();
        this.trainers = new HashMap<>();
        this.monsters = new HashMap<>();
    }

    /**
     * Getter returns a single instance of this class.
     *
     * @return Player
     */
    public static Player getPlayer() {
        return player;
    }

    /**
     * Getter returns the only instance of Kampfarena.
     *
     * @return Kapmfarena
     */
    public Kampfarena getKampfarena() {
        return kampfarena;
    }

    /**
     * Create a new trainer.
     *
     * @return trainer
     */
    public AbstractTrainer getAbstractTrainer(String name) {
        return factory.createTrainer(name);
    }

    /**
     * Create a new code-a-mon.
     *
     * @return monster
     */
    public AbstractMonster getAbstractMonster(String name) {
        return factory.createMonster(name);
    }

    /**
     * This class builds a new trainer and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name       String: Name of trainer
     * @return ConcreteProductTrainer
     */
    public Trainer buildTrainer(Player controller, String name) {
        AbstractTrainer trainer = controller.getAbstractTrainer(name);
        return (Trainer) trainer;
    }

    /**
     * This class build a new monster and returns an instance of it.
     *
     * @param controller Player: Instance of this player
     * @param name       String: Name of monster
     * @return ConcreteProductMonster
     */
    public Monster buildMonster(Player controller, String name) {
        AbstractMonster monster = controller.getAbstractMonster(name);
        return (Monster) monster;
    }

    /**
     * Add a trainer to the Wilde Land.
     *
     * @param trainer Trainer
     */
    public void addTrainer(Trainer trainer) {
        this.trainers.put(trainer.getName(), trainer);
    }

    public HashMap<String, Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Add a wild monster to the Wilde Land.
     *
     * @param monster Monster
     */
    public void addMonster(Monster monster) {
        this.monsters.put(monster.getName(), monster);
    }

    public HashMap<String, Monster> getMonsters() {
        return monsters;
    }

    /**
     * Print team data.
     *
     * @param trainer Trainer
     */
    public void printTeamData(Trainer trainer) {
        // Print trainer data
        trainer.statsToString();
        System.out.println(trainer.listMonsters());
    }

    /**
     * This method is selected when the trainer chooses to go to sleep. This is currently the only way a trainer
     * and its Code-a-mon may heal their hp from battle. Healing hp is based on the trainers max hp and is randomized.
     * a
     */
    public void healHp(Trainer trainer) {
        int hp = new Random().nextInt((int) (Math.ceil(trainer.getMaxHp() * 0.5)));

        // Healing the trainer
        trainer.setHp(trainer.getHp() + hp);
        System.out.println("   +" + hp + " HP");

        // Ensure healing does not surpass max hp
        if(trainer.getHp() > trainer.getMaxHp()) {
            trainer.setHp(trainer.getMaxHp());
        }

        // More for Code-a-mon
        for(Map.Entry<String, CodeAMon> codeAMon : trainer.getCodex().entrySet()) {

            hp = new Random()
                    .nextInt((int) (Math.ceil(codeAMon.getValue().getMonster().getMaxHp() * 0.75)));

            codeAMon.getValue().getMonster()
                    .setHp(codeAMon.getValue().getMonster().getHp() + hp);
            System.out.println("      +" + hp + " HP");

            // Ensure healing does not surpass max hp
            if(codeAMon.getValue().getMonster().getHp() > codeAMon.getValue().getMonster().getMaxHp()) {
                codeAMon.getValue().getMonster().setHp(codeAMon.getValue().getMonster().getMaxHp());
            }
        }
    }

    /**
     * This method is selected when the trainer chooses to go to sleep. This is currently the only way a trainer
     * and its Code-a-mon may heal their mp from battle. Healing mp is based on the trainers max mp and is randomized.
     */
    public void healMp(Trainer trainer) {
        int mp = new Random().nextInt((int) (Math.ceil(trainer.getMaxMp() * 0.25)));

        // Healing the trainer
        trainer.setMp(trainer.getMp() + mp);
        System.out.println("   +" + mp + " MP");

        // Ensure healing does not surpass max mp
        if(trainer.getMp() > trainer.getMaxMp()) {
            trainer.setMp(trainer.getMaxMp());
        }

        // More for Code-a-mon
        for(Map.Entry<String, CodeAMon> codeAMon : trainer.getCodex().entrySet()) {

            mp = new Random()
                    .nextInt((int) (Math.ceil(codeAMon.getValue().getMonster().getMaxMp() * 0.50)));

            codeAMon.getValue().getMonster()
                    .setMp(codeAMon.getValue().getMonster().getMp() + mp);
            System.out.println("      +" + mp + " MP");

            // Ensure healing does not surpass max mp
            if(codeAMon.getValue().getMonster().getMp() > codeAMon.getValue().getMonster().getMaxMp()) {
                codeAMon.getValue().getMonster().setMp(codeAMon.getValue().getMonster().getMaxMp());
            }
        }
    }

    /**
     * This method provides an opportunity for trainers to explore the Wilde Land and tame more monsters. It functions
     * exactly like the block of code.
     *
     * @throws InterruptedException Thread.sleep
     */
    public void exploreTheWildeLand(String trainerName) throws InterruptedException {
        // Form bonds
        int lottery;
        int counter = mediator.getCounter();
        /*
         * Variable counter functions as a stopwatch, the following while loop uses this
         * time and compares it to the current counter, when it detects one counter has passed
         * it breaks out of the loop before the thread goes to sleep in the if statement.
         * The difference can either be 1, e.g. 15-14, -15 e.g. 0-15.
         */
        while (true) {
            lottery = new Random().nextInt(12);

            try {
                // Trainer indexes who are Code-a-mon in their CODEX's
                // Initial Code-a-mon a chosen using a lottery system
                String monsterName = "";
                int i = 0;
                for (String n : player.getMonsters().keySet()) {
                    if (i == lottery) {
                        monsterName = n;
                    }
                    i++;
                }

                player.getTrainers().get(trainerName).formBond(player.getMonsters().get(monsterName));

                if (lottery == 0) {
                    lottery++;
                }

                // Here is where it checks the stopwatch (counter) vs the actual counter (d:t:c)
                if ((mediator.getCounter() - counter) != 1 && (mediator.getCounter() - counter) != -15) {
                    break;
                }

                // The more monsters a trainer tames the less chances of taming more
                Thread.sleep(lottery * player.getTrainers().get(trainerName).getCodex().size());

            } catch (NullPointerException npe) {
                System.out.println(player
                        .getTrainers().get(trainerName).getName() + "'s Codex is full.\n");
            }
        }

        System.out.println(player.getTrainers().get(trainerName).listMonsters());
    }

    /**
     * Register for battle.
     */
    public void registerTrainers() {
        kampfarena.registerForBattle(player.getTrainers());
    }

    /**
     * This method starts a battle at the Kampfarena.
     */
    public void startBattle() throws InterruptedException {
        kampfarena.initiateBattle();
    }

    /**
     * Randomly select an option from the menu for both the trainer and their Code-a-mon.
     *
     * @param entity A Trainer or Code-a-mon
     * @return BattleMenu selection
     */
    public Object getMenuSelection(Object entity) {
        BattleMenu<?> battleMenu = new BattleMenu<>(null);

        if (entity instanceof Trainer) {
            // Generate a Trainer Battle Menu
            System.out.println("   " + ((Trainer) entity).getName() + "\n");
            battleMenu = battleMenu.buildTrainerMenu(player.getTrainers().get(((Trainer) entity).getName()));

        } else {
            // Generate a Code-a-mon Battle Menu
            System.out.println("   " + ((CodeAMon) entity).getMonster().getName() + "\n");
            battleMenu = battleMenu.buildCodeAMonMenu((CodeAMon) entity);
        }

        System.out.println(battleMenu.toString());

        /* The variable i is a pseudo index--as the keys in the HashMap are looped over, the random selection
         * generated checks against the incremented index, if it matches, that option in the HashMap is selected.
         */
        int i = 0;
        int selection;
        try {
            selection = new Random().nextInt(battleMenu.getOptions().size());
        } catch (IllegalArgumentException iae) {
            selection = 0;
        }

        for (String str : battleMenu.getOptions().keySet()) {
            if (selection == i) {
                // The entity has chosen to attack.
                if (battleMenu.getOptions().get(str).getSelection() instanceof String) {
                    System.out.println("  -> " + str + "\n");
                    return battleMenu.getOptions().get(str).getSelection();

                    // The entity has chosen to use its special skills.
                } else if (battleMenu.getOptions().get(str).getSelection() instanceof HashMap) {

                    // An entity chooses which special to use in the same way it it chooses an option from the menu
                    i = 0;
                    try {
                        selection = new Random()
                                .nextInt(((HashMap<?, ?>) battleMenu.getOptions().get(str).getSelection()).size());
                    } catch (IllegalArgumentException iae) {
                        selection = 0;
                    }

                    for (Object obj : ((HashMap<?, ?>) battleMenu.getOptions().get(str).getSelection()).keySet()) {
                        if (selection == i) {

                            System.out.println("  -> " + str + "\n");

                            if (entity instanceof Trainer) {
                                System.out.println(((Trainer) entity).listMonstersCompact());
                            } else {
                                System.out.println(((CodeAMon) entity).listSkillsCompact());
                            }

                            System.out.println("  -> " + obj.toString());

                            // This returns a CODEX or Skills HashMap depending on the entity
                            return ((HashMap<?, ?>) battleMenu.getOptions().get(str).getSelection()).get(obj);
                        }
                    }
                    i++;
                }
            }
            i++;
        }

        return battleMenu.toString();
    }
}