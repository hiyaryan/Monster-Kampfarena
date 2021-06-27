package kampfarena;

import decorator.monster.CodeAMon;
import decorator.monster.MonsterDecorator;
import factory.product.Monster;
import factory.product.Trainer;
import mediator.WildeLandMediator;
import singleton.Player;

import java.util.Map;
import java.util.Queue;
import java.util.Random;

/**
 * Battle (Battle.java)
 *
 * This class is where the battle takes place.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 24, 2021
 */
public class Battle {
    private static final Player player = Player.getPlayer();
    private static final WildeLandMediator mediator = WildeLandMediator.getMediator();
    private static final Kampfarena kampfarena = Kampfarena.getKampfarena();
    private Trainer trainer1;
    private Trainer trainer2;
    private Move<?, ?> move;

    /**
     * Constructor takes the trainers in queue for battle.
     *
     * @param registry Registration Queue
     */
    public Battle(Queue<Registration> registry) {
        this.trainer1 = registry.remove().getTrainer();
        this.trainer2 = registry.remove().getTrainer();

        System.out.println("   " + trainer1.getName().toUpperCase() + " vs " + trainer2.getName().toUpperCase() + "\n");
    }

    /**
     * Set move HashMap coupling.
     *
     * @param attacker   Trainer or Code-a-mon
     * @param attackType Attack or Skills
     */
    private void setAttackerMove(Object attacker, Object attackType) {
        this.move = new Move<>(attacker, attackType);
    }

    /**
     * This method provides the battle logic. The first trainer attacks, followed up by the second trainer. The type of
     * attack and the damage it deals are calculated in the damageTaken and attack methods.
     *
     * @throws InterruptedException Thread.sleep
     */
    public void battle() throws InterruptedException {
        while (true) {
            System.out.println(trainer1.getName() + " is up!");
            System.out.println(getStats(trainer1));

            Thread.sleep(new Random().nextInt(2000) + 2000);

            // Parameters are as follows:
            // (The attack value, The move pair formed in the attack, The damage applied to trainer)
            damageTaken(attack(trainer1), move, trainer2);

            if (isTheBattleOver()) {
                kampfarena.isABattleOngoing(false);
                break;
            }

            System.out.println(trainer2.getName() + " is up!");
            System.out.println(getStats(trainer2));

            Thread.sleep(new Random().nextInt(2000) + 2000);

            // Parameters are as follows:
            // (The attack, The move pair formed in the attack, The damage applied to trainer from the attack)
            damageTaken(attack(trainer2), move, trainer1);

            if (isTheBattleOver()) {
                kampfarena.isABattleOngoing(false);
                break;
            }
        }
    }

    /**
     * This method performs an attack, and returns a value based on the attackers strength and various environmental
     * effects.
     *
     * @param trainer A trainer is the only entity that issues an attack
     * @return attack damage
     */
    public int attack(Trainer trainer) {
        Object trainerSelection = player.getMenuSelection(trainer);

        // A trainer may choose to make an attack
        if (trainerSelection instanceof String) {
            System.out.println("   " + trainer.getName() + " is going for an attack!\n");

            // Set the Move pair Move<Trainer, String>
            setAttackerMove(trainer, "Attack");

            return trainer.getStrength();

            // A trainer may choose to select a Code-a-mon
        } else {
            CodeAMon codeAMon = ((CodeAMon) trainerSelection);
            System.out.println("\n   Go " + codeAMon.getMonster().getName() + "!\n");

            // If a trainer chooses to use a Code-a-mon, this builds the Code-a-mon's battle menu
            Object codeAMonSelection = player.getMenuSelection(codeAMon);

            // A Code-a-mon may attack based on the simulation
            if (codeAMonSelection instanceof String) {
                System.out.println("   " + codeAMon.getMonster().getName() + " is going for an attack!\n");

                // Set the Move pair Move<CodeAMon, String>
                setAttackerMove(codeAMon, "Attack");

                return codeAMon.getMonster().getStrength();

                // A Code-a-mon may use a skill based on the simulation
            } else {
                // Set the Move pair Move<CodeAMon, Skill>
                setAttackerMove(codeAMon, codeAMonSelection);

                if (codeAMon.getMonster().getMp() < ((MonsterDecorator.Skill) move.getAttackType()).getCost()) {
                    System.out.println("\n   " + codeAMon.getMonster().getName() + " does not have enough MP.\n");
                    return -1;
                }

                if (codeAMon.getType().equals(((MonsterDecorator.Skill) codeAMonSelection).getType())) {
                    System.out.println("\n   " + codeAMon.getMonster().getName()
                            + " is using a skill that matches its type!\n");

                    // Adjust MP, then return the damage with weather and type bonuses applied
                    adjustMp(((MonsterDecorator.Skill) move.getAttackType()).getCost(), codeAMon);
                    return (int) (2 * codeAMon.getMonster().getMagic() * codeAMon.getWeatherBonus());

                } else {
                    // Adjust MP, then return the damage with weather bonus
                    adjustMp(((MonsterDecorator.Skill) move.getAttackType()).getCost(), codeAMon);
                    return (int) (codeAMon.getMonster().getMagic() * codeAMon.getWeatherBonus());
                }
            }
        }
    }

    /**
     * This method takes an attack int and calculates the amount of damage taken based on the entities stats. If an
     * attack is landed, the method returns true, else false. During this time, a trainer may call upon any Code-a-mon
     * that is alive in its CODEX to take the damage for them.
     *
     * @param attack   Attack with all bonuses applied
     * @param move     Move is a coupling of the attacker and the attack type
     * @param defender A Trainer is defending
     */
    public void damageTaken(int attack, Move<?, ?> move, Trainer defender) {
        // If the attack is -1, an entity tried to attack without enough MP
        if (attack == -1) {
            return;
        }

        int damage;

        // Check if an attack was successfully made based on evasion and hit stats
        // Returns if false is returned
        if (!didTheAttackerHit(move, defender)) {
            return;
        }

        // If the attacker is a trainer calculate damage based on the trainer stats
        if (move.getAttacker() instanceof Trainer) {
            damage = attack - (defender.getDefense()
                    / ((Trainer) move.getAttacker()).getStrength());

            // If the attacker is a code-a-mon calculate damage based on the code-a-mon stats
        } else {
            damage = attack - (defender.getDefense()
                    / ((CodeAMon) move.getAttacker()).getMonster().getStrength());
        }

        // If the damage is less than 0, total damage taken is 1
        if (damage < 0) {
            damage = 1;
        }

        // The trainer selects the next Code-a-mon with enough HP to take the damage
        // If all code-a-mon are dead, the trainer will take the hit
        for (Map.Entry<String, CodeAMon> codeAMon : defender.getCodex().entrySet()) {
            if (codeAMon.getValue().getMonster().getHp() != 0) {
                System.out.println("\n" + codeAMon.getValue().getMonster().getName()
                        + " is taking the damage for " + defender.getName() + ".");

                // Check if the move is a skill
                if(move.getAttackType() instanceof MonsterDecorator.Skill) {

                    // If skill type of the code-a-mon attacker is the defender code-a-mon type reduce damage by 0.25x.
                    if (((MonsterDecorator.Skill) move.getAttackType()).getType()
                            == codeAMon.getValue().getType()) {

                        System.out.println("   " + codeAMon.getValue().getMonster().getName() + " is strong against "
                                + ((MonsterDecorator.Skill) move.getAttackType()).getType() + "!");

                        System.out.println("\n   Reduced damage 0.25x\n");

                        damage = (int) (damage * 0.25);

                        // Else if it is the code-a-mon weakness type increase damage by 1.75x.
                    } else if (((MonsterDecorator.Skill) move.getAttackType()).getType()
                            == codeAMon.getValue().getTypeWeakness()) {

                        System.out.println("   " + codeAMon.getValue().getMonster().getName() + " is weak against "
                                + ((MonsterDecorator.Skill) move.getAttackType()).getType() + "!");

                        System.out.println("\n   Increased damage 1.75x\n");

                        damage = (int) (damage * 1.75);
                    }
                }

                adjustHp(damage, codeAMon.getValue());

                if (codeAMon.getValue().getMonster().getHp() == 0) {
                    System.out.println("   " + codeAMon.getValue().getMonster().getName() + " has fainted.\n");

                } else {
                    System.out.println("   " + codeAMon.getValue().getMonster().getName() + " is still standing!\n");
                }

                System.out.println(defender.listMonstersCompact());

                return;
            }
        }

        // Trainer takes the hit if there are no Code-a-mon to take it for them.
        adjustHp(damage, defender);
    }


    /**
     * Calculates if an attack was landed based on the evasion and hit of the defender and attacker.
     *
     * Miss logic: If the at hit/evasion == 1, return false If Random(hit/evasion) == hit/evasion, return false
     * Otherwise, continue to calculate damage
     *
     * @param move     Move is a coupling of the attacker and the attack type
     * @param defender Trainer
     * @return was an attack landed?
     */
    public boolean didTheAttackerHit(Move<?, ?> move, Trainer defender) {

        // If the attacker is an instance of a Trainer, use trainer stats.
        if (move.getAttacker() instanceof Trainer) {
            int evade = new Random().nextInt(((((Trainer) move.getAttacker()).getHit())) / (defender.getEvasion()));

            // First possibility for a miss the hit and attack are equal
            if (((Trainer) move.getAttacker()).getHit() / (defender.getEvasion()) == 1) {
                System.out.println("\n   MISS!\n");
                return false;

                // Second possibility for a miss is random
            } else if (evade == (((Trainer) move.getAttacker()).getHit() / (defender.getEvasion()))) {
                System.out.println("\n   MISS!\n");
                return false;
            }

            // If the attacker is an instance of a code-a-mon, use code-a-mon stats.
        } else {
            int evade = new Random().nextInt(((((CodeAMon) move.getAttacker()).getMonster().getHit()))
                    / (defender.getEvasion()));

            // First possibility for a miss the hit and attack are equal
            if (((CodeAMon) move.getAttacker()).getMonster().getHit()
                    / (defender.getEvasion()) == 1) {
                System.out.println("\n   MISS!\n");
                return false;

                // Second possibility for a miss is random
            } else if (evade == (((CodeAMon) move.getAttacker()).getMonster().getHit() / (defender.getEvasion()))) {
                System.out.println("\n   MISS!\n");
                return false;
            }
        }

        // If no misses were made, return true indicating a hit was made.
        return true;
    }


    /**
     * This method accesses the entities stats and adjusts their HP. Formula: HP = HP - damage
     *
     * @param damage int
     * @param entity Trainer or CodeAMon
     */
    private void adjustHp(int damage, Object entity) {
        if (entity instanceof Trainer) {
            Trainer trainer = (Trainer) entity;

            int hp = trainer.getHp() - damage;
            if (hp < 0) {
                System.out.println("\n   OVERKILL!\n");
                hp = 0;
            }

            trainer.setHp(hp);
            System.out.println("   " + trainer.getName() + " has taken " + damage + " damage!\n");

        } else {
            CodeAMon codeAMon = (CodeAMon) entity;

            int hp = codeAMon.getMonster().getHp() - damage;
            if (hp < 0) {
                System.out.println("\n   OVERKILL!\n");
                hp = 0;
            }

            codeAMon.getMonster().setHp(hp);
            System.out.println("   " + codeAMon.getMonster().getName() + " has taken " + damage + " damage!\n");
        }
    }

    /**
     * This method accesses the entities stats and adjusts their MP. Formula: MP = MP - cost
     *
     * @param cost   int
     * @param entity Trainer or CodeAMon
     */
    private void adjustMp(int cost, Object entity) {
        if (entity instanceof Trainer) {
            Trainer trainer = (Trainer) entity;

            int mp = trainer.getMp() - cost;
            if (mp < 0) {
                mp = 0;
            }

            trainer.setMp(mp);

        } else {
            Monster codeAMon = (Monster) ((CodeAMon) entity).getMonster();

            int mp = codeAMon.getMp() - cost;
            if (mp < 0) {
                mp = 0;
            }

            codeAMon.setMp(mp);
        }
    }

    private boolean isTheBattleOver() {
        // If the battle ends due to it being late
        if (mediator.getDate().contains("0t")) {
            int tomorrow = mediator.getDay() + 1;

            if (isDead(trainer1)) {
                System.out.println("\nCONGRATULATIONS " + trainer2.getName().toUpperCase() + "!!\n");
                System.out.println("   You shall live to see another day.\n");

            } else if (isDead(trainer2)) {
                System.out.println("\nCONGRATULATIONS " + trainer1.getName().toUpperCase() + "!!\n");
                System.out.println("   You shall live to see another day.\n");

            } else {
                System.out.println("\nThe battle could not be resolved...");
                System.out.println("   Join us tomorrow at " + tomorrow + "d:1t:0c, the battle shall continue!\n");
            }

            return true;
        }

        if (trainer1.getHp() == 0) {
            return isDead(trainer1);

        } else if (trainer2.getHp() == 0) {
            return isDead(trainer2);
        }

        return false;
    }

    /**
     * Checks if a Trainer has fallen.
     *
     * @return boolean
     */
    public boolean isDead(Trainer trainer) {
        if (trainer.getHp() == 0) {
            System.out.println("\n\n" + trainer.getName() + " is dead.\n\n");
            player.getTrainers().remove(trainer.getName());

            return true;

        } else {
            return false;
        }
    }

    /**
     * This method prints the stats of the entity that is up in battle.
     *
     * @param entity Object is either a Trainer or CodeAMon
     * @return String
     */
    private String getStats(Object entity) {
        StringBuilder sb = new StringBuilder();
        if (entity instanceof Trainer) {
            Trainer trainer = (Trainer) entity;

            sb.append("\n   <<< ").append(trainer.getName()).append(" >>>\n");
            sb.append("     HP: ").append(trainer.getHp()).append("/").append(trainer.getMaxHp()).append("\n");
            sb.append("     MP: ").append(trainer.getMp()).append("/").append(trainer.getMaxMp()).append("\n");

        } else if (entity instanceof CodeAMon) {
            Monster codeAMon = (Monster) (((CodeAMon) entity).getMonster());

            sb.append("\n   <<< ").append(codeAMon.getName()).append(" >>>\n");
            sb.append("   HP: ").append(codeAMon.getHp()).append("/").append(codeAMon.getMaxHp()).append("\n");
            sb.append("   MP: ").append(codeAMon.getMp()).append("/").append(codeAMon.getMaxMp()).append("\n");
        }

        return sb.toString();
    }

    /**
     * This inner class forms a pair.
     *
     * @param <K>
     * @param <V>
     */
    private static class Move<K, V> {
        private final K k;
        private final V v;

        public Move(K k, V v) {
            this.k = k;
            this.v = v;
        }

        public K getAttacker() {
            return k;
        }

        public V getAttackType() {
            return v;
        }
    }
}