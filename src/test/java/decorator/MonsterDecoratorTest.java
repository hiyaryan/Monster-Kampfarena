package decorator;

import decorator.monster.CodeAMon;
import decorator.monster.Initial;
import factory.product.AbstractMonster;
import factory.product.AbstractTrainer;
import factory.product.Monster;
import factory.product.Trainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import singleton.Player;

public class MonsterDecoratorTest {
    AbstractMonster monster;
    AbstractTrainer trainer;

    /**
     * Setup a new monster and trainer for every unit test.
     */
    @Before
    public void setUp() {
        System.out.println("\n===============================");
        System.out.println("MonsterDecorator.java Test Suite");
        System.out.println("===============================");

        monster = new Monster(null);
        trainer = new Trainer(null);
    }

    /**
     * Set class attributes to null after every test.
     */
    @After
    public void tearDown() {
        monster = null;
        trainer = null;
    }

    /**
     * This unit test tests the MonsterDecorator constructor. If a wild monster is passed to
     * the constructor, it is expected to have no skills as the constructor decorates only
     * code-e-mon.
     */
    @Test
    public void testMonsterDecoratorWithAWildMonster() {
        System.out.println("+TEST: testMonsterDecoratorWithAWildMonster\n");

        Initial initial = new Initial((Monster) monster);
        initial.getMonster().setName("Wale");

        System.out.println("Expected: "
                + "--- " + initial.getMonster().getName()
                +  " Skills ---");

        System.out.println("Actual: " + initial.listSkills());

        Assert.assertEquals("--- " + initial.getMonster().getName() +  " Skills ---\n",
                initial.listSkills());
    }

    /**
     * This unit test tests the MonsterDecorator constructor. If a code-a-mon is passed to the
     * constructor, it is expected to have skills as the constructor decorates the code-a-mon
     * with initial skills.
     */
    @Test
    public void testMonsterDecoratorWithATamedMonster() {
        System.out.println("+TEST: testMonsterDecoratorWithATamedMonster\n");

        monster.setName("Wale");
        CodeAMon codeAMon = trainer.formBond((Monster) monster);

        System.out.println("Expected:\n"
                + "--- " + codeAMon.getMonster().getName()
                +  " Skills ---\n"
                + "Splash [Type: Water]\n");

        System.out.println("Actual:\n" + codeAMon.listSkills());

        Assert.assertEquals("--- " + codeAMon.getMonster().getName() +  " Skills ---\n"
                        + "Splash [Type: WATER]\n", codeAMon.listSkills());
    }

    @Test
    public void testInitWithAnUntamedMonster() {
        monster = new Monster("Wale");

        trainer = new Trainer("Dock");
        trainer.formBond((Monster) monster);

        System.out.println("Expected: Dock");
        System.out.println("Actual: " + monster.getTrainer().getName() + "\n");

        Assert.assertEquals("Dock", monster.getTrainer().getName());
    }

    @Test
    public void testInitWithTamedWale() {
        monster = new Monster("Wale");

        trainer = new Trainer("Dock");
        trainer.formBond((Monster) monster);

        trainer = new Trainer("Tomm");
        trainer.formBond((Monster) monster);

        System.out.println("Unexpected: Tomm");
        System.out.println("Actual: " + monster.getTrainer().getName() + "\n");

        Assert.assertNotEquals("Tomm", monster.getTrainer().getName());
    }

    @Test
    public void testDeclareSkillAndGiveTypeBranches() {
        Player player = Player.getPlayer();

        Monster wildWale = new Monster("Wale");
        Monster wildKaht = new Monster("Kaht");
        Monster wildPyth = new Monster("Pyth");
        Monster wildJaxx = new Monster("Jaxx");
        Monster wildCoco = new Monster("Coco");
        Monster wildAntt = new Monster("Antt");
        Monster wildGith = new Monster("Gith");
        Monster wildServ = new Monster("Serv");
        Monster wildClie = new Monster("Clie");
        Monster wildDesi = new Monster("Desi");
        Monster wildExml = new Monster("Exml");
        Monster wildAdle = new Monster("Adle");

        player.addMonster(wildKaht);
        player.addMonster(wildWale);
        player.addMonster(wildPyth);
        player.addMonster(wildJaxx);
        player.addMonster(wildCoco);
        player.addMonster(wildAntt);
        player.addMonster(wildGith);
        player.addMonster(wildServ);
        player.addMonster(wildClie);
        player.addMonster(wildDesi);
        player.addMonster(wildExml);
        player.addMonster(wildAdle);

        Trainer dock = new Trainer("Dock");
        player.addTrainer(dock);

        dock.formBond(wildKaht);
        dock.formBond(wildWale);
        dock.formBond(wildPyth);
        dock.formBond(wildJaxx);
        dock.formBond(wildCoco);
        dock.formBond(wildAntt);

        Trainer tomm = new Trainer("Tomm");
        player.addTrainer(tomm);

        tomm.formBond(wildGith);
        tomm.formBond(wildServ);
        tomm.formBond(wildClie);
        tomm.formBond(wildDesi);
        tomm.formBond(wildExml);
        tomm.formBond(wildAdle);

        System.out.println("\nExpected: 6");
        System.out.println("Actual: " + dock.getCodex().size() + "\n");

        System.out.println("\nExpected: 6");
        System.out.println("Actual: " + tomm.getCodex().size() + "\n");

        Assert.assertEquals(6, dock.getCodex().size());
        Assert.assertEquals(6, tomm.getCodex().size());
    }
}
