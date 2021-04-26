package models.hero;

import utils.ErrorCode;
import utils.exceptions.InvalidDataException;

public class HeroFactory {

    public Hero newHero(Hero hero)
    {
        hero.setLevel(1);
        hero.setMaxHp(1000);
        hero.setActualHp(1000);
        hero.setArtefactName("no one");
        hero.setArtefactAttack(0);
        hero.setX((hero.getLevel() * 5 + 10) / 2 - 1);
        hero.setY((hero.getLevel() * 5 + 10) / 2 - 1);
        hero.setBeforeX((hero.getLevel() * 5 + 10) / 2 - 1);
        hero.setBeforeY((hero.getLevel() * 5 + 10) / 2 - 1);
        hero.setExp(0);
        switch (hero.getClas())
        {
            case "Leon":
                return createLeon(hero);
            case "Jessie":
                return createJessie(hero);
            case "Edgar":
                return createEdgar(hero);
            case "Poco":
                return createPoco(hero);
            default:
                throw new InvalidDataException("Invalid race", ErrorCode.INVALID_DATA_ERROR.getCode());
        }
    }

    private Hero createLeon(Hero hero)
    {
        hero.setAttack(100);
        hero.setDefense(65);
        return hero;
    }

    private Hero createJessie(Hero hero)
    {
        hero.setAttack(80);
        hero.setDefense(70);
        return hero;
    }

    private Hero createEdgar(Hero hero)
    {
        hero.setAttack(155);
        hero.setDefense(40);
        return hero;
    }

    private Hero createPoco(Hero hero)
    {
        hero.setAttack(60);
        hero.setDefense(80);
        return hero;
    }
}
