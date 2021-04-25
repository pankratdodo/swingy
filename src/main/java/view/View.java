package view;

import models.enemy.Enemy;
import models.hero.Hero;

import java.util.List;

public interface View {
    /**
     * Выбираем сделать ли новго героя или выбрать среди старых
     * @return false - старый, true - новый
     */
    boolean oldOrNewHero();

    /**
     * Создание героя
     * @return герой
     */
    Hero createHeroName();

    /**
     * Создание класса героя
     * @param hero герой
     * @return герой с классом
     */
    Hero createHeroClass(Hero hero);

    /**
     * Метод позволяет выбрать игрока
     * @param names лист имен в бд
     * @return имя игрока или null, если нужно создать нового
     */
    String chooseHeroName(List<String> names);

    /**
     * Рисует карту
     * @param hero герой
     * @param enemies враги
     * @param map_size размер карты
     */
    Hero printMap(Hero hero, List<Enemy> enemies, int map_size);

    /**
     * Движения по карте
     * @param hero герой
     * @param enemies враги
     * @param map_size размер карты
     * @return герой в новом положении
     */
    Hero move(Hero hero, List<Enemy> enemies, int map_size);

    /**
     * Если герой готов драться
     * @param hero герой
     * @param enemy враг
     * @return true - готов драться, false - отступаем!
     */
    boolean readyToFight(Hero hero, Enemy enemy);

    /**
     * Если враг умер
     * @param hero герой
     * @param enemy враг
     * @param heroHp здоровье героя
     * @param enemyHp здоровье врага
     * @return герой в новом положении
     */
    Hero enemyIsDead(Hero hero, Enemy enemy, int heroHp, int enemyHp);

    /**
     * Если герой умер
     */
    void dead();

    /**
     * Если уровень увеличивается
     * @param hero герой
     */
    void lvlUp(Hero hero);

    /**
     * Если игрок выиграл
     */
    void win();
}
