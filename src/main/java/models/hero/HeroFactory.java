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
        hero.setX(7);
        hero.setY(7);
        hero.setBeforeX(7);
        hero.setBeforeY(7);
        switch (hero.getClas())
        {
            case "Леон":
                return createLeon(hero);
            case "Джесси":
                return createJessie(hero);
            case "Эдраг":
                return createEdgar(hero);
            case "Поко":
                return createPoko(hero);
            default:
                throw new InvalidDataException("Invalid race", ErrorCode.INVALID_DATA_ERROR.getCode());
        }
    }

    private Hero createLeon(Hero hero)
    {
        hero.setExp(200);
        hero.setAttack(100);
        hero.setDefence(60);
        return hero;
    }

    private Hero createJessie(Hero hero)
    {
        hero.setExp(200);
        hero.setAttack(120);
        hero.setDefence(55);
        return hero;
    }

    private Hero createEdgar(Hero hero)
    {
        hero.setExp(200);
        hero.setAttack(130);
        hero.setDefence(40);
        return hero;
    }

    private Hero createPoko(Hero hero)
    {
        hero.setExp(200);
        hero.setAttack(140);
        hero.setDefence(30);
        return hero;
    }
}
