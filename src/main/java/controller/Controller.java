package controller;

import database.DataBaseServiceImpl;
import models.enemy.Enemy;
import models.enemy.EnemyFactory;
import models.hero.Hero;
import lombok.NoArgsConstructor;
import models.hero.HeroFactory;
import view.console.ConsoleCreateHeroView;
import view.gui.GuiCreateHeroView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class Controller {

    private String view;
    private GuiCreateHeroView guiCreateHeroView;
    private ConsoleCreateHeroView consoleCreateHeroView;
    private Hero hero;
    private final DataBaseService dataBaseService = new DataBaseServiceImpl();
    private List<Enemy> enemies = new ArrayList<>();

    /**
     * Метод выбирает, с каким видом будем работать
     * @param arg аргумент, который подал пользователь
     */
    public void chooseView(String arg)
    {
        if (arg.equals("gui"))
        {
            view = "gui";
            guiCreateHeroView = new GuiCreateHeroView();
        }
        else if (arg.equals("console"))
        {
            view = "console";
            consoleCreateHeroView = new ConsoleCreateHeroView();
        }
        else {
            System.err.println("You have to use only one of arguments: console or gui.");
            System.exit(-1);
        }
        oldOrNewHero();
    }

    /**
     * Метод спрашивает, хочет пользователь создать нового или выбрать старого игрока
     */
    public void oldOrNewHero()
    {
        dataBaseService.establishConnection();
        if (view.equals("gui") && guiCreateHeroView.oldOrNewHero() ||
                view.equals("console") && consoleCreateHeroView.oldOrNewHero())
            createHero();
        else
            chooseHero();
        firstPrintMap();
    }

    /**
     * Метод для создания нового перса
     */
    public void createHero()
    {
        hero = view.equals("gui") ? guiCreateHeroView.createHeroName() : consoleCreateHeroView.createHeroName();
        hero = view.equals("gui") ? guiCreateHeroView.createHeroClass(hero) : consoleCreateHeroView.createHeroClass(hero);
        hero = new HeroFactory().newHero(hero);
        dataBaseService.addNewHero(hero);
    }

    /**
     * Метод для выбора существующего перса
     */
    public void chooseHero()
    {
        List<String> names = dataBaseService.getHeroesNames();
        String heroName = view.equals("gui") ? guiCreateHeroView.chooseHeroName(names) : consoleCreateHeroView.chooseHeroName(names);
        if (heroName == null)
            createHero();
        else
            hero = dataBaseService.findByName(heroName);
    }

    /**
     * Метод рисования карты, героев и врагов
     */
    public void firstPrintMap()
    {
        int map_size = hero.getLevel() * 5 + 10;
        Random random = new Random();
        EnemyFactory factory = new EnemyFactory();
        for (int i = 0; i < map_size / 2; i += 1)
        {
            enemies.add(factory.newEnemy(random.nextInt(4 - 1) + 1, map_size));
        }
        if (view.equals("gui")) {
            guiCreateHeroView.printMap(hero, enemies, map_size);
        } else {
            consoleCreateHeroView.printMap(hero, enemies, map_size);
        }
        move(map_size);
    }

    /**
     * Метод отвечает за движение героя по карте
     */
    public void move(int map_size)
    {
        hero = checkFight(hero);
        if (view.equals("gui")) {
            guiCreateHeroView.move(hero, enemies, map_size);
        } else {
            consoleCreateHeroView.move(hero, enemies, map_size);
        }
    }

    /**
     * Проверяем, возможна ли драка
     * @param hero герой
     * @return герой
     */
    public Hero checkFight(Hero hero) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == hero.getX() && enemy.getY() == hero.getY()) {
                if (view.equals("gui")) {
                    if (guiCreateHeroView.readyToFight(hero, enemy))
                        return fight(hero, enemy);
                    else{
                        hero.setX(hero.getBeforeX());
                        hero.setY(hero.getBeforeY());
                    }
                } else {
                    if(consoleCreateHeroView.readyToFight(hero, enemy))
                        return fight(hero, enemy);
                    else{
                        hero.setX(hero.getBeforeX());
                        hero.setY(hero.getBeforeY());
                    }
                }
            }
        }
        return hero;
    }

    /**
     * Драка
     * @param hero герой
     * @param enemy враг
     * @return герой
     */
    public Hero fight(Hero hero, Enemy enemy)
    {
        enemies.remove(enemy);
        int enemyHp;
        int heroHp;
        if (hero.getArtefactName().equals("weapon")) {
            enemyHp = enemy.getActualHp() - hero.getAttack() + enemy.getDefence() - hero.getArtefactAttack();
            heroHp = hero.getActualHp() - enemy.getAttack() + hero.getDefence();
        }
        else if (hero.getArtefactName().equals("armor")) {
            heroHp = hero.getActualHp() - enemy.getAttack() + hero.getDefence() + hero.getArtefactAttack();
            enemyHp = enemy.getActualHp() - hero.getAttack() + enemy.getDefence();
        }
        else {
            heroHp = hero.getActualHp() - enemy.getAttack() + hero.getDefence();
            enemyHp = enemy.getActualHp() - hero.getAttack() + enemy.getDefence();
        }
        if (heroHp <= 0)
        {
            if (view.equals("gui")) {
                guiCreateHeroView.dead();
            } else {
                consoleCreateHeroView.dead();
            }
        }
        if (enemyHp <= 0) {
            if (view.equals("gui")) {
                hero = guiCreateHeroView.fight(hero, enemy, heroHp, enemyHp);
            } else {
                hero = consoleCreateHeroView.fight(hero, enemy, heroHp, enemyHp);
            }
        }
        else {
            enemy.setActualHp(enemyHp);
            enemies.add(enemy);
            hero.setX(hero.getBeforeX());
            hero.setY(hero.getBeforeY());
        }
        hero.setActualHp(heroHp);
        return hero;
    }
    //todo: после переноса не работает do you wanna fight
}
