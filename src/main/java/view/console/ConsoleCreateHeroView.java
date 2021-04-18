package view.console;

import models.enemy.Enemy;
import models.hero.Hero;
import utils.HeroValidator;
import view.View;

import java.util.List;
import java.util.Scanner;

public class ConsoleCreateHeroView implements View {
    HeroValidator validator = new HeroValidator();

    @Override
    public boolean oldOrNewHero()
    {
        System.out.println("Print:\n0 - to exit\n1 - to choose old hero\n2 - to create new hero\n");
        Scanner in = new Scanner(System.in);
        while(true) {
            String res = in.nextLine();
            switch (res) {
                case "0":
                    System.exit(0);
                case "1":
                    return false;
                case "2":
                    return true;
                default:
                    System.out.println("Choose number 0 - 2.");
                    break;
            }
        }
    }

    @Override
    public Hero createHeroName() {
        while (true) {
            System.out.println("Print name of your hero:\n");
            Scanner in = new Scanner(System.in);
            Hero hero = new Hero();
            hero.setName(in.nextLine());
            if (!validator.validateHeroConstraintConsole(hero)) {
                continue;
            }
            else
                return hero;
        }
    }

    @Override
    public Hero createHeroClass(Hero hero) {
        System.out.println("Choose class of " + hero.getName());
        System.out.println("0 - to exit\n1 - Леон\n2 - Джесси\n3 - Эдраг\n4 - Поко");
        while (true) {
            Scanner in = new Scanner(System.in);
            String res = in.nextLine();
            switch (res) {
                case "0":
                    System.exit(0);
                case "1":
                    hero.setClas("Леон");
                    break;
                case "2":
                    hero.setClas("Джесси");
                    break;
                case "3":
                    hero.setClas("Эдраг");
                    break;
                case "4":
                    hero.setClas("Поко");
                    break;
                default:
                    System.err.println("Choose number 0 - 4.");
            }
            return hero;
        }
    }

    @Override
    public String chooseHeroName(List<String> names) {
        if (names.isEmpty())
        {
            System.out.println("Hero not found. Create new one.");
            return null;
        }
        System.out.println("Choose one of heroes:\n0 - to exit");
        int i = 1;
        for (String name: names)
        {
            System.out.println(i + " - " + name);
            i++;
        }
        while (true) {
            Scanner in = new Scanner(System.in);
            int res = in.nextInt();
            return names.get(res - 1);
        }
    }

    @Override
    public void firstPrintMap(Hero hero, List<Enemy> enemies, int map_size) {
        for (int i = 0; i < map_size; i += 1)
        {
            for (int j = 0; j < map_size; j += 1)
            {
                int enemyInStr = 0;
                for (Enemy enemy :enemies)
                {
                    if (enemy.getX() == j && enemy.getY() == i)
                    {
                        switch (enemy.getRace()) {
                            case "Mortis":
                                System.out.print('M');
                                break;
                            case "8Bit":
                                System.out.print('8');
                                break;
                            case "Tik":
                                System.out.print('T');
                                break;
                            case "Jeckie":
                                System.out.print('J');
                                break;
                        }
                        enemyInStr += 1;
                    }
                }
                if (i == hero.getX() && j == hero.getY())
                    System.out.print('H');
                else if (enemyInStr == 0)
                    System.out.print("-");
            }
            System.out.print("\n");
        }
    }
}
