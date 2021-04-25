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
    public Hero printMap(Hero hero, List<Enemy> enemies, int map_size) {
        return new Hero();

    }

    @Override
    public Hero move(Hero hero, List<Enemy> enemies, int map_size){
        return new Hero();
    }

    @Override
    public boolean readyToFight(Hero hero, Enemy enemy) {
        return false;
    }

    @Override
    public Hero enemyIsDead(Hero hero, Enemy enemy, int heroHp, int enemyHp) {
        return new Hero();

    }

    @Override
    public void dead() {

    }

    @Override
    public void lvlUp(Hero hero) {
    }

    @Override
    public void win() {

    }
}
