package decorator.monster;

import factory.product.AbstractMonster;
import factory.product.Trainer;

/**
 * This interface represents a Code-a-mon
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

    String listSkills();
}