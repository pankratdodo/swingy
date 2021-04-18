package view;

import models.enemy.Enemy;
import models.hero.Hero;

import java.util.List;

public interface View {
    boolean oldOrNewHero();

    Hero createHeroName();

    Hero createHeroClass(Hero hero);

    /**
     * Метод позволяет выбрать игрока
     * @param names лист имен в бд
     * @return имя игрока или null, если нужно создать нового
     */
    String chooseHeroName(List<String> names);

    void firstPrintMap(Hero hero, List<Enemy> enemies, int map_size);
}
