package view.console;

import models.enemy.Enemy;
import models.hero.Hero;
import view.View;

import java.util.List;
import java.util.Scanner;

public class ConsoleCreateHeroView implements View {

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
    public String createHeroName() {
        System.out.println("Print name of your hero:\n");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    @Override
    public void alreadyExist() {
        System.err.println("Hero with this name is already exist.\nChoose another name.");
    }

    @Override
    public Hero createHeroClass(Hero hero) {
        System.out.println("Choose class of " + hero.getName());
        System.out.println("0 - to exit\n1 - Leon\n2 - Jessie\n3 - Edgar\n4 - Poco");
        while (true) {
            Scanner in = new Scanner(System.in);
            String res = in.nextLine();
            switch (res) {
                case "0":
                    System.exit(0);
                case "1":
                    hero.setClas("Leon");
                    break;
                case "2":
                    hero.setClas("Jessie");
                    break;
                case "3":
                    hero.setClas("Edgar");
                    break;
                case "4":
                    hero.setClas("Poco");
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
    public Hero printMap(Hero hero, List<Enemy> enemies, int map_size) {
        for (int i = 0; i < map_size; i += 1) {
            for (int j = 0; j < map_size; j += 1) {
                int enemyInStr = 0;
                for (Enemy enemy : enemies) {
                    if (enemy.getX() == i && enemy.getY() == j) {
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
                        break;
                    }
                }
                if (i == hero.getX() && j == hero.getY())
                    System.out.print('H');
                else if (enemyInStr == 0)
                    System.out.print("-");
            }
            System.out.print("\n");
        }
        return hero;
    }

    @Override
    public Hero move(Hero hero, List<Enemy> enemies, int map_size) {
        System.out.println("Print: 'W' to move North\n'A' to East\t\t'D' to West\n\t'S'to South");
        Scanner scanner = new Scanner(System.in);
        String key = scanner.nextLine();
        switch (key) {
            case "w":
                hero.setBeforeX(hero.getX());
                hero.setBeforeY(hero.getY());
                hero.setX(hero.getX() - 1);
                break;
            case "a":
                hero.setBeforeY(hero.getY());
                hero.setBeforeX(hero.getX());
                hero.setY(hero.getY() - 1);
                break;
            case "d":
                hero.setBeforeY(hero.getY());
                hero.setBeforeX(hero.getX());
                hero.setY(hero.getY() + 1);
                break;
            case "s":
                hero.setBeforeX(hero.getX());
                hero.setBeforeY(hero.getY());
                hero.setX(hero.getX() + 1);
                break;
            default:
                System.err.println("Invalid key");
        }
        return hero;
    }

    @Override
    public boolean readyToFight(Hero hero, Enemy enemy) {
        while (true) {
            System.out.println("Your hero is " + hero.getClas() + ", attack: " + hero.getAttack() + " defence: " + hero.getDefense() +
                    " hp: " + hero.getActualHp() + " artefactPower: " + hero.getArtefactAttack());
            System.out.println("Your enemy is " + enemy.getRace() + ", attack: " + enemy.getAttack() + " defence: " + enemy.getDefence() +
                    " hp: " + enemy.getActualHp());
            System.out.println("Do you wanna fight?\nPrint 'y' to fight, 'n' to run");
            Scanner scanner = new Scanner(System.in);
            String key = scanner.nextLine();
            switch (key) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.err.println("Invalid key");
            }
        }
    }

    @Override
    public void dead()
    {
        System.out.println("You are dead.");
        System.exit(0);
    }

    @Override
    public Hero enemyIsDead(Hero hero, Enemy enemy, int heroHp, int enemyHp) {
        System.out.println("Enemy is dead.");
        System.out.println("Your experience is " + hero.getExp() + " now.");
        if (!enemy.getArtefactName().equals("no one"))
        {
            System.out.println("You got a " + enemy.getArtefactName() + " with power " + enemy.getArtefactAttack());
            if (enemy.getArtefactName().equals("helm")) {
                heroHp += hero.getActualHp() + enemy.getArtefactAttack();
                System.out.println("The helm gave you health " + enemy.getArtefactAttack() + ". Now you have " + hero.getActualHp());
            }
            else {
                hero.setArtefactName(enemy.getArtefactName());
                hero.setArtefactAttack(enemy.getArtefactAttack());
            }
        }
        hero.setActualHp(heroHp);
        return hero;
    }

    @Override
    public void lvlUp(Hero hero) {
        System.out.println("Your hero is lvl up!");
        System.out.println("Your hero is " + hero.getClas() + ", lvl = "  + hero.getLevel() + ", attack: " + hero.getAttack() + " defence: " + hero.getDefense() +
                " hp: " + hero.getActualHp() + " artefactPower: " + hero.getArtefactAttack());
    }

    @Override
    public void win() {
        System.out.println("You are win! Congratulation!");
        System.out.println("█░█ ▄▀▀▄ █░░█ . ▄▀▄ █▀▄ █▀▀ . █░░░█ ▀█▀ █▄░█\n" +
                "▀█▀ █░░█ █░░█ . █▄█ █▀▄ █▀▀ . █▄█▄█ ░█░ █▀██\n" +
                "░▀░ ░▀▀░ ░▀▀░ . ▀░▀ ▀░▀ ▀▀▀ . ▀▀░▀▀ ▀▀▀ ▀░░▀");
        System.exit(0);
    }
}
