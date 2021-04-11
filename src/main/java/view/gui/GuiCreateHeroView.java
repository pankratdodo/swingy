package view.gui;

import models.Hero;
import view.View;

public class GuiCreateHeroView implements View {
    @Override
    public boolean oldOrNewHero() {
        return false;
    }

    @Override
    public Hero createHeroName() {
        return null;
    }

    @Override
    public Hero createHeroClass(Hero hero) {
        return null;
    }

    @Override
    public Hero chooseHeroName() {
        return null;
    }
}
