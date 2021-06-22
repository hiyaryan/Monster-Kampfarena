package decorator;

import factory.AbstractMonster;
import factory.AbstractTrainer;
import factory.Monster;
import factory.Trainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InitialTest {
    AbstractMonster monster;
    AbstractTrainer trainer;

    /**
     * Setup a new monster and trainer for every unit test.
     */
    @Before
    public void setUp() {
        System.out.println("\n===============================");
        System.out.println("    Initial.java Test Suite");
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
     * This unit test tests the initSkill method with the code-a-mon Wale. The initSkill method
     * decorates a tamed monster, i.e. a code-a-mon, with an initial skill provided a type
     * Testing initSkill tests the inner class Skill.
     */
    @Test
    public void testInitSkillMethodWithWale() {
        System.out.println("+TEST: testInitSkillMethodWithWale\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Wale");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        MonsterDecorator.Skill skill = initial.initSkill("Splash", "Water");

        System.out.println("Expected: Splash Water 1");
        System.out.println("Actual: " + skill.getName()
                + " " + skill.getType()
                + " " + skill.getLevel() + "\n");

        Assert.assertEquals("Splash", skill.getName());
        Assert.assertEquals("Water", skill.getType());
        Assert.assertEquals(1, skill.getLevel());
    }

    /**
     * This unit test tests the initSkill method with the code-a-mon Kaht. The initSkill method
     * decorates a tamed monster, i.e. a code-a-mon, with an initial skill provided a type
     * Testing initSkill tests the inner class Skill.
     */
    @Test
    public void testInitSkillMethodWithKaht() {
        System.out.println("+TEST: testInitSkillMethodWithKaht\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Kaht");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        MonsterDecorator.Skill skill = initial.initSkill("Scratch", "Poison");

        System.out.println("Expected: Scratch Poison 1");
        System.out.println("Actual: " + skill.getName()
                + " " + skill.getType()
                + " " + skill.getLevel() + "\n");

        Assert.assertEquals("Scratch", skill.getName());
        Assert.assertEquals("Poison", skill.getType());
        Assert.assertEquals(1, skill.getLevel());
    }

    /**
     * This unit test tests the init method with the code-a-mon Wale. The init method decorates a
     * tamed monster, i.e. a code-a-mon with an initial skill, type, and skill level.
     * In the process of testing init the following are also tested: the two methods
     * declareSkill, and giveMonsterType, the inner class Skill, and the HashMap skills.
     */
    @Test
    public void testInitMethodWithWale() {
        System.out.println("+TEST: testInitMethodWithWale\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Wale");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        Initial temp = new Initial(null);
        MonsterDecorator.Skill skill = temp.initSkill("Splash", "Water");

        System.out.println("Expected: Wale Water 1 Splash");
        System.out.println("Actual: "
                + codeAMon.getMonster().getName()
                + " " + initial.getType()
                + initial.getSkills().get(initial.initSkill("Splash", "Water").getName()).getLevel() + " "
                + initial.getSkills().get(initial.initSkill("Splash", "Water").getName()).getName()
                + "\n");

        Assert.assertEquals("Wale", codeAMon.getMonster().getName());
        Assert.assertEquals("Water", initial.getType());
        Assert.assertEquals(skill.getLevel(),
                initial.getSkills().get(initial.initSkill("Splash", "Water").getName()).getLevel());
        Assert.assertEquals(skill.getName(),
                initial.getSkills().get(initial.initSkill("Splash", "Water").getName()).getName());
    }

    /**
     * This unit test tests the init method with the code-a-mon Kaht. The init method decorates a
     * tamed monster, i.e. a code-a-mon with an initial skill, type, and skill level.
     * In the process of testing init the following are also tested: the two methods
     * declareSkill, and giveMonsterType, the inner class Skill, and the HashMap skills.
     */
    @Test
    public void testInitMethodWithKaht() {
        System.out.println("+TEST: testInitMethodWithKaht\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Kaht");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        Initial temp = new Initial(null);
        MonsterDecorator.Skill skill = temp.initSkill("Scratch", "Poison");

        System.out.println("Expected: Kaht Poison 1 Scratch");
        System.out.println("Actual: "
                + codeAMon.getMonster().getName()
                + " " + initial.getType() + " "
                + initial.getSkills().get(initial.initSkill("Scratch", "Poison").getName()).getLevel() + " "
                + initial.getSkills().get(initial.initSkill("Scratch", "Poison").getName()).getName()
                + "\n");

        Assert.assertEquals("Kaht", codeAMon.getMonster().getName());
        Assert.assertEquals("Poison", initial.getType());
        Assert.assertEquals(skill.getLevel(),
                initial.getSkills().get(initial.initSkill("Scratch", "Poison").getName()).getLevel());
        Assert.assertEquals(skill.getName(),
                initial.getSkills().get(initial.initSkill("Scratch", "Poison").getName()).getName());
    }

    /**
     * This unit test tests the init method with an unknown code-a-mon. It is expected for an
     * unknown code-a-mon to not gain any skills. Without any skills, the HashMap size is 0.
     */
    @Test
    public void testInitMethodWithUnknownMonster() {
        System.out.println("+TEST: testInitMethodWithUnknownMonster\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Unknown");

        MonsterDecorator initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        System.out.println("\nExpected: skills.size == 0");
        System.out.println("Actual: skills.size == " + initial.skills.size() + "\n");

        Assert.assertEquals(0, initial.skills.size());
    }

    /**
     * This unit test tests the statsToString method to check proper format
     */
    @Test
    public void testStatsToString() {
        System.out.println("+TEST: testStatsToString\n");

        Initial initial = new Initial((Monster) monster);

        StringBuilder sb = new StringBuilder("   >>> " + null + " <<<\n");
        sb.append("   Type: ").append("null").append("\n");
        sb.append("   HP:   ").append("0").append("\n");
        sb.append("   MP:   ").append("0");

        System.out.println("\nExpected: " + sb.toString());
        System.out.println("Actual: " + initial.statsToString());

        Assert.assertEquals(sb.toString(), initial.statsToString());
    }
}
