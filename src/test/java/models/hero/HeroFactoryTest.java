package models.hero;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HeroFactoryTest {
    HeroFactory factory = new HeroFactory();

    @Test
    public void newHeroLeonTest()
    {
        Hero leon = new Hero();
        leon.setName("nameL");
        leon.setRace("Leon");

        Hero res = factory.newHero(leon);
        assertThat(res.getLevel()).isEqualTo(1);
        assertThat(res.getAttack()).isEqualTo(100);
        assertThat(res.getDefense()).isEqualTo(65);
    }

    @Test
    public void newHeroJessieTest()
    {
        Hero jessie = new Hero();
        jessie.setName("nameJ");
        jessie.setRace("Jessie");
        Hero res = factory.newHero(jessie);
        assertThat(res.getLevel()).isEqualTo(1);
        assertThat(res.getAttack()).isEqualTo(80);
        assertThat(res.getDefense()).isEqualTo(70);
    }

    @Test
    public void newHeroEdgarTest()
    {
        Hero edgar = new Hero();
        edgar.setName("nameE");
        edgar.setRace("Edgar");
        Hero res = factory.newHero(edgar);
        assertThat(res.getLevel()).isEqualTo(1);
        assertThat(res.getAttack()).isEqualTo(155);
        assertThat(res.getDefense()).isEqualTo(40);
    }

    @Test
    public void newHeroPocoTest()
    {
        Hero poco = new Hero();
        poco.setName("nameP");
        poco.setRace("Poco");
        Hero res = factory.newHero(poco);
        assertThat(res.getLevel()).isEqualTo(1);
        assertThat(res.getAttack()).isEqualTo(60);
        assertThat(res.getDefense()).isEqualTo(80);
    }

    @Test
    public void newHeroInvalidTest()
    {
        Hero rand = new Hero();
        rand.setName("nameR");
        rand.setRace("Rand");
        assertThatThrownBy(() -> factory.newHero(rand)).hasMessage("Invalid race");

    }
}
