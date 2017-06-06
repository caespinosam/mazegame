package com.mazegame.core.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.mazegame.core.exception.PlayerAlreadyExistException;
import com.mazegame.core.model.character.Hero;

/**
 * Unit test for PlayerPersistorInMemory.
 * 
 * @author xpdev
 *
 */
public class PlayerPersistorInMemoryTest {

  private final IPlayerPersistor playerPersistor = PlayerPersistorInMemory.getInstance();

  @Test
  public void persistHero_When_newCreated() {
    Hero hero = Hero.newInstance("HeroTest", 1);
    playerPersistor.savePlayer(hero);

    Assert.assertNotNull(playerPersistor.getPlayerByName(hero.getName()));

    Iterator<Hero> iterator = playerPersistor.getPlayers();
    Set<Hero> heroes = new HashSet<>();
    iterator.forEachRemaining(heroes::add);

    Assert.assertTrue(heroes.contains(hero));
  }

  @Test
  public void findHero_When_newNameSent() {
    Hero hero = Hero.newInstance("HeroTest2", 1);
    playerPersistor.savePlayer(hero);
    Assert.assertNotNull(playerPersistor.getPlayerByName(hero.getName()));
    Assert.assertNull(playerPersistor.getPlayerByName("Ivalid"));
  }

  @Test(expected = PlayerAlreadyExistException.class)
  public void throwException_When_newNameSent() {
    Hero hero = Hero.newInstance("HeroTest3", 1);
    playerPersistor.savePlayer(hero);
    hero = Hero.newInstance("HeroTest3", 1);
    playerPersistor.savePlayer(hero);
  }
}
