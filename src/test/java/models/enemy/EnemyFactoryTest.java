package models.enemy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnemyFactoryTest {

    EnemyFactory factory = new EnemyFactory();

    @Test
    public void newEnemyMortisTest()
    {
        Enemy mortis = factory.newEnemy(1, 39);
        assertThat(mortis.getRace()).isEqualTo("Mortis");
        assertThat(mortis.getAttack()).isEqualTo(3);
        assertThat(mortis.getDefence()).isEqualTo(20);
    }

    @Test
    public void newEnemy8BitTest()
    {
        Enemy bit = factory.newEnemy(2, 39);
        assertThat(bit.getRace()).isEqualTo("8Bit");
        assertThat(bit.getAttack()).isEqualTo(60);
        assertThat(bit.getDefence()).isEqualTo(25);
    }

    @Test
    public void newEnemyTikTest()
    {
        Enemy tik = factory.newEnemy(3, 39);
        assertThat(tik.getRace()).isEqualTo("Tik");
        assertThat(tik.getAttack()).isEqualTo(75);
        assertThat(tik.getDefence()).isEqualTo(0);
    }

    @Test
    public void newEnemyJackyTest()
    {
        Enemy jacky = factory.newEnemy(4, 39);
        assertThat(jacky.getRace()).isEqualTo("Jacky");
        assertThat(jacky.getAttack()).isEqualTo(50);
        assertThat(jacky.getDefence()).isEqualTo(50);
    }

    @Test
    public void newEnemyRandomTest()
    {
        assertThatThrownBy(() -> factory.newEnemy(10, 39));
    }
}
