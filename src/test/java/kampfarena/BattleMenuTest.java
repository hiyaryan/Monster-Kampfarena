package kampfarena;

import decorator.monster.CodeAMon;
import factory.product.AbstractMonster;
import factory.product.AbstractTrainer;
import factory.product.Monster;
import factory.product.Trainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class BattleMenuTest {
    AbstractMonster monster;
    AbstractTrainer trainer;
    CodeAMon codeAMon;
    BattleMenu<?> battleMenu;
    HashMap<String, BattleMenu.Option<?>> options;

    /**
     * Setup a new monster and trainer for every unit test.
     */
    @Before
    public void setUp() {
        System.out.println("\n===============================");
        System.out.println("    BattleMenu.java Test Suite");
        System.out.println("===============================");

        monster = new Monster("Monster");
        trainer = new Trainer("Trainer");

        codeAMon = trainer.formBond((Monster) monster);

        battleMenu = new BattleMenu<>(null);
        options = new HashMap<>();
    }

    /**
     * Set class attributes to null after every test.
     */
    @After
    public void tearDown() {
        monster = null;
        trainer = null;
        battleMenu = null;
        options = null;
    }

    /**
     * Test battle menu creation for null entity.
     */
    @Test
    public void testBattleMenuConstructorWithNullEntity() {
        System.out.println("\n+TEST: testBattleMenuConstructorWithNullEntity\n");

        battleMenu = new BattleMenu<>(null);

        System.out.println("\nExpected: null");
        System.out.println("Actual: " + battleMenu.getEntity() + "\n");

        Assert.assertNull(battleMenu.getEntity());
    }

    /**
     * Test battle menu creation for trainer entity.
     */
    @Test
    public void testBattleMenuConstructorWithTrainerEntity() {
        System.out.println("\n+TEST: testBattleMenuConstructorWithTrainerEntity\n");
        battleMenu = new BattleMenu<>((Trainer) trainer);

        System.out.println("\nExpected: Trainer");
        System.out.println("Actual: " + battleMenu.getEntity() + "\n");
        System.out.println("Expected: Attack");
        System.out.println("Actual: " + battleMenu.getOptions().get("Attack").getSelection() + "\n");

        Assert.assertEquals(trainer, battleMenu.getEntity());
        Assert.assertEquals("Attack", battleMenu.getOptions().get("Attack").getSelection());
    }

    /**
     * Test battle menu creation for code-a-mon entity.
     */
    @Test
    public void testBattleMenuConstructorWithCodeAMonEntity() {
        System.out.println("\n+TEST: testBattleMenuConstructorWithCodeAMonEntity\n");

        battleMenu = new BattleMenu<>(codeAMon);

        System.out.println("\nExpected: " + codeAMon);
        System.out.println("Actual: " + battleMenu.getEntity() + "\n");

        System.out.println("Expected: Attack");
        System.out.println("Actual: " + battleMenu.getOptions().get("Attack").getSelection() + "\n");

        Assert.assertEquals(codeAMon, battleMenu.getEntity());
        Assert.assertEquals("Attack", battleMenu.getOptions().get("Attack").getSelection());
    }

    /**
     * Test build battle menu for trainer entity.
     */
    @Test
    public void testBuildTrainerMenu() {
        System.out.println("\n+TEST: testBuildTrainerMenu\n");

        battleMenu = battleMenu.buildTrainerMenu((Trainer) trainer);

        System.out.println("\nExpected: Trainer");
        System.out.println("Actual: " + battleMenu.getEntity() + "\n");

        System.out.println("Expected: Attack");
        System.out.println("Actual: " + battleMenu.getOptions().get("Attack").getSelection() + "\n");

        System.out.println("Expected: " + trainer.getCodex());
        System.out.println("Actual: " + battleMenu.getOptions().get("CODEX").getSelection() + "\n");

        StringBuilder sb = new StringBuilder("   === MENU ===\n");
        sb.append("   + Attack\n").append("   + CODEX\n");
        System.out.println("Expected: " + sb.toString());
        System.out.println("Actual: " + battleMenu.toString() + "\n");

        Assert.assertEquals(trainer, battleMenu.getEntity());
        Assert.assertEquals("Attack", battleMenu.getOptions().get("Attack").getSelection());
        Assert.assertEquals(trainer.getCodex(), battleMenu.getOptions().get("CODEX").getSelection());
        Assert.assertEquals(sb.toString(), battleMenu.toString());
    }

    /**
     * Test build battle menu for code-a-mon entity.
     */
    @Test
    public void testBuildCodeAMonMenu() {
        System.out.println("\n+TEST: testBuildCodeAMonMenu\n");

        battleMenu = battleMenu.buildCodeAMonMenu(codeAMon);

        System.out.println("\nExpected: " + codeAMon);
        System.out.println("Actual: " + battleMenu.getEntity() + "\n");

        System.out.println("Expected: Attack");
        System.out.println("Actual: " + battleMenu.getOptions().get("Attack").getSelection() + "\n");

        System.out.println("Expected: " + codeAMon.getSkills());
        System.out.println("Actual: " + battleMenu.getOptions().get("Skills").getSelection() + "\n");

        StringBuilder sb = new StringBuilder("   === MENU ===\n");
        sb.append("   + Skills\n").append("   + Attack\n");
        System.out.println("Expected: " + sb.toString());
        System.out.println("Actual: " + battleMenu.toString() + "\n");

        Assert.assertEquals(codeAMon, battleMenu.getEntity());
        Assert.assertEquals("Attack", battleMenu.getOptions().get("Attack").getSelection());
        Assert.assertEquals(codeAMon.getSkills(), battleMenu.getOptions().get("Skills").getSelection());
        Assert.assertEquals(sb.toString(), battleMenu.toString());
    }

    /**
     * Test build battle menu for monster entity.
     */
    @Test
    public void testInnerGenericOptionClass() {
        System.out.println("\n+TEST: testInnerGenericOptionClass\n");

        battleMenu = new BattleMenu<>("Option");
        System.out.println("Expected: Attack");
        System.out.println("Actual: " + battleMenu.getOptions().get("Attack").getSelection() + "\n");

        Assert.assertEquals("Attack", battleMenu.getOptions().get("Attack").getSelection());
    }
}
