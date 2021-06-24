package kampfarena;

import singleton.Player;

public class Battle {
Player controller = Player.getController();

    protected Battle () {

    }

    public void battle() {
        System.out.println(controller.getMenuSelection());
    }
}
