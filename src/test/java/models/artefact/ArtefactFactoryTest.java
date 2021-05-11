package models.artefact;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtefactFactoryTest {

    ArtefactFactory factory = new ArtefactFactory();

    @Test
    public void newArtefactWeaponTest()
    {
        Artefact artefact = factory.newArtefact(1);
        assertThat(artefact.getName()).isEqualTo("weapon");
    }

    @Test
    public void newArtefactArmorTest()
    {
        Artefact artefact = factory.newArtefact(2);
        assertThat(artefact.getName()).isEqualTo("armor");
    }

    @Test
    public void newArtefactHelmTest()
    {
        Artefact artefact = factory.newArtefact(3);
        assertThat(artefact.getName()).isEqualTo("helm");
    }

    @Test
    public void newArtefactDefaultTest()
    {
        Artefact artefact = factory.newArtefact(10);
        assertThat(artefact.getName()).isEqualTo("no one");
        assertThat(artefact.getPower()).isEqualTo(0);
    }
}
