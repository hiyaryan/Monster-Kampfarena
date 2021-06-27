package decorator.monster;

import decorator.wildeland.Weather;
import factory.product.AbstractMonster;
import factory.product.Trainer;

import java.util.HashMap;

/**
 * This interface represents a Code-a-mon.
 *
 * @author Ryan Meneses
 * @version 1.0
 * @since June 21, 2021
 */
// http://www.blackwasp.co.uk/Decorator.aspx
public interface CodeAMon {

    AbstractMonster getMonster();

    void init(Trainer trainer);

    String statsToString();

    MonsterDecorator.Type getType();

    MonsterDecorator.Type getTypeWeakness();

    Weather.Type getWeatherStrength();

    Weather.Type getWeatherWeakness();

    HashMap<String, MonsterDecorator.Skill> getSkills();

    String listSkills();

    String listSkillsCompact();

    int getExp();

    void setExp(int exp);

    double getWeatherBonus();

    void setWeatherBonus(double bonus);
}