package controller;

import models.Hero;

import java.util.List;

public interface DataBaseService {

    void establishConnection();

    List<String> getHeroesNames();

    void addNewHero(Hero heroToAdd);

}
