package controller;

import database.DataBaseServiceImpl;
import models.enemy.Enemy;
import models.enemy.EnemyFactory;
import models.hero.Hero;
import lombok.NoArgsConstructor;
import models.hero.HeroFactory;
import utils.ErrorCode;
import utils.HeroValidator;
import utils.exceptions.CreateDataException;
import utils.exceptions.ReadDataErrorException;
import view.console.ConsoleCreateHeroView;
import view.gui.GuiCreateHeroView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
public class Controller {

    private String view;
    private GuiCreateHeroView guiCreateHeroView;
    private ConsoleCreateHeroView consoleCreateHeroView;
    private Hero hero = new Hero();
    private final DataBaseService dataBaseService = new DataBaseServiceImpl();
    HeroValidator validator = new HeroValidator();
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
        String name;
        while (true) {
            name = view.equals("gui") ? guiCreateHeroView.createHeroName() : consoleCreateHeroView.createHeroName();
            hero.setName(name);
            if (!validator.validateHeroConstraintConsole(hero)) {
                continue;
            }
            else {
                break;
            }
        }
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
        getEnemies(map_size);
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
        saveEnemies();
        while (true) {
            dataBaseService.updateHero(hero);
            if (view.equals("gui")) {
                hero = guiCreateHeroView.move(hero, enemies, map_size);
                checkEndOfMap(map_size);
                checkFight();
                guiCreateHeroView.printMap(hero, enemies, map_size);
            } else {
                hero = consoleCreateHeroView.move(hero, enemies, map_size);
                checkEndOfMap(map_size);
                checkFight();
                consoleCreateHeroView.printMap(hero, enemies, map_size);
            }
            if (hero.getExp() >= 1000) {
                lvlUp();
                break;
            }
        }
    }

    /**
     * Сохраняем врагов
     */
    void saveEnemies()
    {
        File file = new File("src/main/resources/" + hero.getName());
        try {
            if (file.exists() || file.createNewFile()) {
                    FileOutputStream writeData = new FileOutputStream(file);
                    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

                    writeStream.writeObject(enemies);
                    writeStream.flush();
                    writeStream.close();
            }
        } catch (IOException e) {
            throw new CreateDataException("Cant create file with enemies", ErrorCode.CREATE_DATA_ERROR.getCode());
        }
    }

    void getEnemies(int map_size)
    {
        File file = new File("src/main/resources/" + hero.getName());
        if (file.exists())
        {
            try {
                FileInputStream readData = new FileInputStream(file);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                enemies = (ArrayList<Enemy>) readStream.readObject();
                readStream.close();
            }
            catch (IOException | ClassNotFoundException e)
            {
                throw new ReadDataErrorException("Cant read file with enemies", ErrorCode.READ_DATA_ERROR_EXCEPTION.getCode());
            }
        }
        else {
            Random random = new Random();
            EnemyFactory factory = new EnemyFactory();
            for (int i = 0; i < map_size / 2; i += 1) {
                enemies.add(factory.newEnemy(random.nextInt(4 - 1) + 1, map_size));
            }
        }
    }

    /**
     * Проверяем, не ушли ли мы за границу карты
     * @param map_size размер карты
     * @return герой с корректным положением на карте
     */
    public void checkEndOfMap(int map_size)
    {
        if (hero.getX() >= map_size || hero.getY() >= map_size ||
                hero.getX() < 0 || hero.getY() < 0) {
            System.err.println("Impossible to go outside.");
            hero.setX(hero.getBeforeX());
            hero.setY(hero.getBeforeY());
        }
    }

    /**
     * Проверяем, возможна ли драка
     */
    public void checkFight() {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == hero.getX() && enemy.getY() == hero.getY()) {
                if (view.equals("gui")) {
                    if (guiCreateHeroView.readyToFight(hero, enemy)) {
                        fight(enemy);
                        break;
                    }
                    else{
                        hero.setX(hero.getBeforeX());
                        hero.setY(hero.getBeforeY());
                        hero.setActualHp(hero.getActualHp() - 50);
                    }
                } else {
                    if(consoleCreateHeroView.readyToFight(hero, enemy)) {
                        fight(enemy);
                        break;
                    }
                    else{
                        hero.setX(hero.getBeforeX());
                        hero.setY(hero.getBeforeY());
                        hero.setActualHp(hero.getActualHp() - 50);
                    }
                }
            }
        }
    }

    /**
     * Драка
     * @param enemy враг
     */
    public void fight(Enemy enemy)
    {
        enemies.remove(enemy);
        int enemyHp;
        int heroHp;
        if (hero.getArtefactName().equals("weapon")) {
            enemyHp = enemy.getActualHp() - hero.getAttack() + enemy.getDefence() - hero.getArtefactAttack();
            heroHp = hero.getActualHp() - enemy.getAttack() + hero.getDefense();
        }
        else if (hero.getArtefactName().equals("armor")) {
            heroHp = hero.getActualHp() - enemy.getAttack() + hero.getDefense() + hero.getArtefactAttack();
            enemyHp = enemy.getActualHp() - hero.getAttack() + enemy.getDefence();
        }
        else {
            heroHp = hero.getActualHp() - enemy.getAttack() + hero.getDefense();
            enemyHp = enemy.getActualHp() - hero.getAttack() + enemy.getDefence();
        }
        if (heroHp > hero.getActualHp())
            heroHp = hero.getActualHp() - 1;
        if (enemyHp > enemy.getActualHp())
            enemyHp = enemy.getActualHp() - 1;
        if (heroHp <= 0)
        {
            if (view.equals("gui")) {
                guiCreateHeroView.dead();
            } else {
                consoleCreateHeroView.dead();
            }
        }
        if (enemyHp <= 0) {
            hero.setExp(hero.getExp() + enemy.getMaxHp());
            if (view.equals("gui")) {
                hero = guiCreateHeroView.enemyIsDead(hero, enemy, heroHp, enemyHp);
            } else {
                hero = consoleCreateHeroView.enemyIsDead(hero, enemy, heroHp, enemyHp);
            }
        }
        else {
            enemy.setActualHp(enemyHp);
            enemies.add(enemy);
            hero.setX(hero.getBeforeX());
            hero.setY(hero.getBeforeY());
        }
        hero.setActualHp(heroHp);
        saveEnemies();
    }

    /**
     * Если нужно увеличить лвл
     */
    public void lvlUp()
    {
        if (hero.getLevel() == 5) {
            dataBaseService.deleteHero(hero);
            if (view.equals("gui")) {
                guiCreateHeroView.win();
            } else {
                consoleCreateHeroView.win();
            }
        }
        hero.setLevel(hero.getLevel() + 1);
        hero.setAttack(hero.getAttack() + hero.getAttack() / 10);
        hero.setDefense(hero.getDefense() + + hero.getDefense() / 10);
        hero.setX((hero.getLevel() * 5 + 10) / 2 - 1);
        hero.setY((hero.getLevel() * 5 + 10) / 2 - 1);
        hero.setBeforeX(hero.getX());
        hero.setBeforeY(hero.getY());
        hero.setExp(0);
        hero.setActualHp(hero.getLevel() * 1000 + (hero.getLevel() - 1) * (hero.getLevel() - 1) * 450);
        new File("src/main/resources/" + hero.getName()).delete();
        if (view.equals("gui")) {
            guiCreateHeroView.lvlUp(hero);
        } else {
            consoleCreateHeroView.lvlUp(hero);
        }
        firstPrintMap();
    }
}
