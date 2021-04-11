package controller;

import database.DataBaseServiceImpl;
import models.Hero;
import lombok.NoArgsConstructor;
import view.console.ConsoleCreateHeroView;
import view.gui.GuiCreateHeroView;

import java.util.List;

@NoArgsConstructor
public class Controller {

    private String view;
    private GuiCreateHeroView guiCreateHeroView;
    private ConsoleCreateHeroView consoleCreateHeroView;
    private Hero hero;
    private final DataBaseService dataBaseService = new DataBaseServiceImpl();

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
    }

    /**
     * Метод для создания нового перса
     */
    public void createHero()
    {
        hero = view.equals("gui") ? guiCreateHeroView.createHeroName() : consoleCreateHeroView.createHeroName();
        hero = view.equals("gui") ? guiCreateHeroView.createHeroClass(hero) : consoleCreateHeroView.createHeroClass(hero);
        //фактори на героев
        dataBaseService.addNewHero(hero);
    }

    /**
     * Метод для выбора существующего перса
     */
    public void chooseHero()
    {
        List<String> names = dataBaseService.getHeroesNames();
        if (names.isEmpty())
        {
            System.out.println("Hero not found. Create new one.");
            createHero();
        }
        System.out.println(names);
    }
}
