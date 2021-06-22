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

    @Before
    public void setUp() throws Exception {
        System.out.println("\n===============================");
        System.out.println("MonsterDecorator.java Test Suite");
        System.out.println("===============================");

        monster = new Monster();
        trainer = new Trainer();
    }

    @After
    public void tearDown() throws Exception {
        monster = null;
        trainer = null;
    }

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
