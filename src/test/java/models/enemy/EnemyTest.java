package models.enemy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnemyTest {

    @Test
    public void enemyTestData()
    {
        Enemy enemy = new Enemy();
        enemy.setRace("Mortis");

        assertThat(enemy.getRace()).isEqualTo("Mortis");
        assertThat(enemy.toString()).isEqualTo("Enemy(race=Mortis, attack=0, defence=0, actualHp=0, maxHp=0, x=0, y=0, artefactName=null, artefactAttack=0)");

        enemy = new Enemy("8Bit", 10, 10, 10, 10, 1, 1, "artef", 10);
        assertThat(enemy.getArtefactName()).isEqualTo("artef");
    }
}
