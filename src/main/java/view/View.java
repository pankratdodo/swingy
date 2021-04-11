package view;

import controller.DataBaseService;
import models.Hero;

public interface View {
    boolean oldOrNewHero();

    Hero createHeroName();

    Hero createHeroClass(Hero hero);

    Hero chooseHeroName();
}
