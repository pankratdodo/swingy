package models.enemy;

import models.artefact.Artefact;
import models.artefact.ArtefactFactory;
import utils.ErrorCode;
import utils.exceptions.InvalidDataException;

import java.util.Random;

public class EnemyFactory {

    public Enemy newEnemy(int race, int map_size)
    {
        Enemy enemy = new Enemy();
        enemy = generateCoordinates(enemy, map_size);
        enemy = generateArtefact(enemy);
        switch (race)
        {
            case 1:
                return createMortis(enemy);
            case 2:
                return create8Bit(enemy);
            case 3:
                return createTik(enemy);
            case 4:
                return createJeckie(enemy);
            default:
                throw new InvalidDataException("Invalid race", ErrorCode.INVALID_DATA_ERROR.getCode());
        }
    }

    private Enemy generateCoordinates(Enemy enemy, int map_size)
    {
        Random random = new Random();
        int x = random.nextInt(map_size - 1) + 1;
        int y = random.nextInt(map_size - 1) + 1;
        if (x == map_size / 2 - 1  && y == map_size / 2 - 1) {
            enemy.setX(x + 1);
            enemy.setY(y + 1);
        }
        else {
            enemy.setX(x);
            enemy.setY(y);
        }
        return enemy;
    }

    private Enemy generateArtefact(Enemy enemy)
    {
        Random random = new Random();
        Artefact artefact = new ArtefactFactory().newArtefact(random.nextInt(20 - 1) + 1);
        enemy.setArtefactName(artefact.getName());
        enemy.setArtefactAttack(artefact.getPower());
        return enemy;
    }

    private Enemy createMortis(Enemy enemy)
    {
        enemy.setRace("Mortis");
        enemy.setAttack(3);
        enemy.setDefence(20);
        enemy.setActualHp(500);
        enemy.setMaxHp(500);
        return enemy;
    }

    private Enemy create8Bit(Enemy enemy)
    {
        enemy.setRace("8Bit");
        enemy.setAttack(60);
        enemy.setDefence(25);
        enemy.setActualHp(500);
        enemy.setMaxHp(500);
        return enemy;
    }

    private Enemy createTik(Enemy enemy)
    {
        enemy.setRace("Tik");
        enemy.setAttack(75);
        enemy.setDefence(0);
        enemy.setActualHp(500);
        enemy.setMaxHp(500);
        return enemy;
    }

    private Enemy createJeckie(Enemy enemy)
    {
        enemy.setRace("Jeckie");
        enemy.setAttack(50);
        enemy.setDefence(50);
        enemy.setActualHp(500);
        enemy.setMaxHp(500);
        return enemy;
    }
}
