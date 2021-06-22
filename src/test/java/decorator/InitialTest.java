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

    @Before
    public void setUp() throws Exception {
        System.out.println("\n===============================");
        System.out.println("    Initial.java Test Suite");
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
    public void testInitSkillMethodWithWale() {
        System.out.println("+TEST: testInitSkillMethodWithWale\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Wale");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        MonsterDecorator.Skill skill = initial.initSkill("Splash", "Water");

        Assert.assertEquals("Splash", skill.getName());
        Assert.assertEquals("Water", skill.getType());
        Assert.assertEquals(1, skill.getLevel());
    }

    @Test
    public void testInitSkillMethodWithKaht() {
        System.out.println("+TEST: testInitSkillMethodWithKaht\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Kaht");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        MonsterDecorator.Skill skill = initial.initSkill("Scratch", "Poison");

        Assert.assertEquals("Scratch", skill.getName());
        Assert.assertEquals("Poison", skill.getType());
        Assert.assertEquals(1, skill.getLevel());
    }

    @Test
    public void testInitMethodWithWale() {
        System.out.println("+TEST: testInitMethodWithWale\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Wale");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        Initial temp = new Initial(null);
        MonsterDecorator.Skill skill = temp.initSkill("Splash", "Water");

        Assert.assertEquals("Wale", codeAMon.getMonster().getName());
        Assert.assertEquals("Water", initial.getType());
        Assert.assertEquals(skill.getName(), initial.initSkill("Splash", "Water").getName());
    }

    @Test
    public void testInitMethodWithKaht() {
        System.out.println("+TEST: testInitMethodWithKaht\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Kaht");

        Initial initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        Initial temp = new Initial(null);
        MonsterDecorator.Skill skill = temp.initSkill("Scratch", "Poison");

        Assert.assertEquals("Kaht", codeAMon.getMonster().getName());
        Assert.assertEquals("Poison", initial.getType());
        Assert.assertEquals(skill.getName(), initial.initSkill("Scratch", "Poison").getName());
    }

    @Test
    public void testInitMethodWithUnknownMonster() {
        System.out.println("+TEST: testInitMethodWithUnknownMonster\n");

        CodeAMon codeAMon = (Monster) monster;
        codeAMon.getMonster().setName("Unknown");

        MonsterDecorator initial = new Initial(codeAMon);
        initial.init((Trainer) trainer);

        Assert.assertEquals(0, initial.skills.size());
    }
}
