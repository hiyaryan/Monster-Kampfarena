package kampfarena;

import decorator.monster.CodeAMon;
import decorator.monster.MonsterDecorator;
import factory.product.Trainer;

import java.util.HashMap;
import java.util.Map;

/**
 * BattleMenu (BattleMenu.java)
 *
 * This class creates a battle menu for a specific trainer and their Code-a-mon.
 * The simulation will select an option from the menu and perform the actions.
 * A trainer has two options, Attack and CODEX. If CODEX is selected, the simulation
 * will select from a list of available Code-a-mon. When a code-a-mon is selected
 * a new menu will appear for the specific Code-a-mon. Menu's only exist in battle.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 24, 2021
 */
public class BattleMenu<T> {
    private T entity;
    private HashMap<String, Option> options;

    public BattleMenu() {
        System.out.println("\nNext up!\n");
    }

    /**
     * All entities are initialized with a basic attack option.
     */
    public BattleMenu(T entity) {
        System.out.println("   " + entity.toString());

        this.entity = entity;
        options = new HashMap<>();

        String op = "Attack";
        Option<String> attack = new Option<>(op);
        options.put(op, attack);
    }

    public HashMap<String, Option> getOptions() {
        return options;
    }

    /**
     * This method gets the trainer menu. It uses the option class to build
     * the list.
     *
     * @param trainer Trainer up in battle
     * @return Trainer menu
     */
    public BattleMenu<Trainer> builtTrainerMenu(Trainer trainer) {
        BattleMenu<Trainer> menu = new BattleMenu<>(trainer);

        String op = "CODEX";
        Option<Map<String, CodeAMon>> codex = new Option<>(trainer.getCodex());
        menu.options.put(op, codex);

        return menu;
    }

    /**
     * This method gets the code-a-mon menu. It uses the option class to build
     * the list.
     *
     * @param codeAMon Code-a-mon selected by the trainer
     * @return Code-a-mon menu
     */
    public BattleMenu<CodeAMon> buildCodeAMonMenu(CodeAMon codeAMon) {
        BattleMenu<CodeAMon> menu = new BattleMenu<>(codeAMon);

        String op = "Skills";
        Option<Map<String, MonsterDecorator.Skill>> skills = new Option<>(codeAMon.getSkills());
        menu.options.put(op, skills);

        return menu;
    }

    /**
     * Print a representation of the BattleMenu object.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("   === MENU ===\n");

        for(String key : options.keySet()) {
            sb.append("   + ").append(key).append("\n");
        }

        return sb.toString();
    }

    /**
     * This generic class sets up the menu options. A menu option may be
     * as single string or a list of skills or code-a-mon
     *
     * @param <T>
     */
    public static class Option<T> {
        T selection;

        public Option() {

        }

        public Option(T selection) {
            this.selection = selection;
        }

        public T getSelection() {
            return selection;
        }
    }
}
