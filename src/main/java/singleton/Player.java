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

import java.util.HashMap;
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
    private static Player controller = new Player();
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
        this.trainers= new HashMap<>();
        this.monsters = new HashMap<>();
    }

    /**
     * Getter returns a single instance of this class.
     *
     * @return Player
     */
    public static Player getController() {
        return controller;
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
     * @param name String: Name of trainer
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
     * @param name String: Name of monster
     * @return ConcreteProductMonster
     */
    public Monster buildMonster(Player controller, String name) {
        AbstractMonster monster = controller.getAbstractMonster(name);
        return (Monster) monster;
    }

    /**
     * Add a trainer to the Wilde Land
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
     * Add a wile monster to the Wilde Land.
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
     * Register for battle
     *
     * @return
     */
    public void registerTrainers() {
        kampfarena.registerForBattle(controller.getTrainers());
    }

    /**
     * This method starts a battle at the Kampfarena.
     */
    public void startBattle() {
        kampfarena.initiateBattle();
    }

    /**
     * Randomly select an option from the menu.
     */
    public <T> Object getMenuSelection(Trainer trainer) {
        BattleMenu<Trainer> battleMenu = new BattleMenu<>();
        battleMenu = battleMenu.buildTrainerMenu(controller.getTrainers().get(trainer.getName()));

        int i = 0;
        int selection = new Random().nextInt(battleMenu.getOptions().size());
        for(String str : battleMenu.getOptions().keySet()) {
            if(selection == i) {
                if (battleMenu.getOptions().get(str).getSelection() instanceof String) {
                    return battleMenu.getOptions().get(str).getSelection();

                } else if (battleMenu.getOptions().get(str).getSelection() instanceof HashMap) {

                    i = 0;
                    selection = new Random().nextInt(((HashMap<?, ?>) battleMenu.getOptions().get(str).getSelection()).size());
                    for (Object mon : ((HashMap<?, ?>) battleMenu.getOptions().get(str).getSelection()).keySet()) {
                        CodeAMon codeAMon = (CodeAMon) ((HashMap<?, ?>) battleMenu.getOptions().get(str).getSelection()).get(mon);

                        if(selection == i) {
                            return codeAMon;
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