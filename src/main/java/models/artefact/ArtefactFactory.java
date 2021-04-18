package models.artefact;


import java.util.Random;

public class ArtefactFactory {

    public Artefact newArtefact(int artNum)
    {
        Artefact artefact = new Artefact();
        switch (artNum)
        {
            case 1:
                return createWeapon(artefact);
            case 2:
                return createArmor(artefact);
            case 3:
                return createHelm(artefact);
            default:
                return defaultArt(artefact);
        }
    }

    private Artefact createWeapon(Artefact artefact)
    {
        artefact.setName("weapon");
        Random random = new Random();
        artefact.setPower(random.nextInt(10 - 2) + 2);
        return artefact;
    }

    private Artefact createArmor(Artefact artefact)
    {
        artefact.setName("armor");
        Random random = new Random();
        artefact.setPower(random.nextInt(10 - 2) + 2);
        return artefact;
    }

    private Artefact createHelm(Artefact artefact)
    {
        artefact.setName("helm");
        Random random = new Random();
        artefact.setPower(random.nextInt(10 - 2) + 2);
        return artefact;
    }

    private Artefact defaultArt(Artefact artefact)
    {
        artefact.setName("no one");
        artefact.setPower(0);
        return artefact;
    }
}
