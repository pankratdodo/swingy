package models.artefact;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArtefactTest {

    @Test
    public void artefactTestData()
    {
        Artefact artefact = new Artefact();
        artefact.setName("artefact");
        artefact.setPower(100);

        assertThat(artefact.getPower()).isEqualTo(100);
        assertThat(artefact.toString()).isEqualTo("Artefact(name=artefact, power=100)");

        artefact = new Artefact("name", 10);
        assertThat(artefact.getName()).isEqualTo("name");
    }
}
