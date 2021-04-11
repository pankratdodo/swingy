package view.console;

import models.Hero;
import utils.HeroValidator;
import view.View;

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
        System.out.println("0 - to exit\n1 - Леон\n2 - Шелли\n3 - Эдраг\n4 - Ворон");
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
                    hero.setClas("Шелли");
                    break;
                case "3":
                    hero.setClas("Эдраг");
                    break;
                case "4":
                    hero.setClas("Ворон");
                    break;
                default:
                    System.err.println("Choose number 0 - 4.");
            }
            return hero;
        }
    }

    @Override
    public Hero chooseHeroName() {
////        service.addNewHero(new Hero("name", "LEON"));
//        List<String> names = service.getHeroesNames();
//        if (names.isEmpty())
//        {
//            System.out.println("Hero not found. Create new one.");
//            return null;
//        }
//        return new Hero();
        return null;
    }
}
