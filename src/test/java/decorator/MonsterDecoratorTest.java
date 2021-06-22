package decorator;

import factory.AbstractMonster;
import factory.AbstractTrainer;
import factory.Monster;
import factory.Trainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        monster = new Monster();
        trainer = new Trainer();
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
                + "Splash [lvl. 1 Water]\n");

        System.out.println("Actual:\n" + codeAMon.listSkills());

        Assert.assertEquals("--- " + codeAMon.getMonster().getName() +  " Skills ---\n"
                        + "Splash [lvl. 1 Water]\n"
                , codeAMon.listSkills());
    }
}
