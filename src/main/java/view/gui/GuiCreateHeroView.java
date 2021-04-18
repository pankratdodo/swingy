package view.gui;

import models.enemy.Enemy;
import models.hero.Hero;
import view.View;

import java.util.List;

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
    public String chooseHeroName(List<String> names) {
        return null;
    }

    @Override
    public void firstPrintMap(Hero hero, List<Enemy> enemies, int map_size) {

    }
}
