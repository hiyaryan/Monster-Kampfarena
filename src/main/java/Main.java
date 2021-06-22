import singleton.Player;

/**
 * Main (Main.java)
 *
 * This runs the simulation.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 19, 2021
 */
public class Main {
    public static void main(String[] args) {
        Player controller = Player.getController();
        controller.initializeTheWildeLand();
    }
}