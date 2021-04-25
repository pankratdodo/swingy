package controller;

import models.hero.Hero;

import java.util.List;

public interface DataBaseService {

    void establishConnection();

    List<String> getHeroesNames();

    void addNewHero(Hero heroToAdd);

    Hero findByName(String name);

    void updateHero(Hero hero);

    void deleteHero(Hero hero);
}
