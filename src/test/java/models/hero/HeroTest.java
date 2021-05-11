package models.hero;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeroTest {

    @Test
    public void heroTestData()
    {
        Hero hero = new Hero();
        hero.setName("name");
        hero.setRace("Leon");

        assertThat(hero.getName()).isEqualTo("name");
        assertThat(hero.getRace()).isEqualTo("Leon");

        assertThat(hero.toString()).isEqualTo("Hero(name=name, race=Leon, level=0, attack=0, exp=0, defense=0, actualHp=0, maxHp=0, x=0, y=0, beforeX=0, beforeY=0, artefactName=null, artefactAttack=0)");

        hero = new Hero("name", "Poco", 1, 10, 10, 10, 10,
                10, 1, 1, 1, 1, "artef", 10);
        assertThat(hero.getArtefactName()).isEqualTo("artef");
    }

}
