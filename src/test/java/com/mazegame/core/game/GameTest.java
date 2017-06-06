package com.mazegame.core.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mazegame.core.exception.InvalidMovementException;
import com.mazegame.core.exception.PlayerAlreadyExistException;
import com.mazegame.core.model.board.Room;
import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.character.GhostAttackBehavior;
import com.mazegame.core.model.character.GoblinAttackBehavior;
import com.mazegame.core.model.character.Hero;
import com.mazegame.core.model.character.ZombieAttackBehavior;
import com.mazegame.core.model.weapon.Bow;
import com.mazegame.core.model.weapon.Knife;
import com.mazegame.core.model.weapon.Poison;

/**
 * Unit test for the main use cases.
 * 
 * @author Cesar
 *
 */
public class GameTest {

  private final static Game game = Game.newInstance();

  @Before
  public void cleanState() {
    game.initState();
  }

  @Test
  public void persistPlayer_When_NewCreated() {
    Hero hero = Hero.newInstance("HeroTest", 1);
    game.savePlayer(hero);
    Iterator<Hero> iterator = game.getCreatedPlayers();
    List<Hero> players = new ArrayList<>();
    iterator.forEachRemaining(players::add);

    Assert.assertNotNull(players.contains(hero));
  }

  @Test(expected = PlayerAlreadyExistException.class)
  public void throwException_When_NewNameAlreadyExists() {
    Hero hero = Hero.newInstance("HeroTest3", 1);
    game.savePlayer(hero);
    hero = Hero.newInstance("HeroTest3", 1);
    game.savePlayer(hero);
  }

  @Test(expected = InvalidMovementException.class)
  public void throwException_When_InvalidMovement() {

    game.goEast();
  }

  @Test
  public void generateBoard_When_GameInitializes() {
    Game gameTmp = Game.newInstance();
    gameTmp.initState();
    Assert.assertNotNull(gameTmp.getCurrentState().getBoard());
  }

  @Test
  public void movePlayerToNorth_When_GoNorthAction() {

    Room entranceRoom = game.getCurrentState().getBoard().getRoom(4, 4);
    game.getCurrentState().setCurrentRoom(entranceRoom);
    Room northRoom = game.getCurrentState().getBoard().getRoom(3, 4);
    game.goNorth();
    Assert.assertEquals(northRoom, game.getCurrentState().getCurrentRoom());

  }

  @Test
  public void movePlayerToSouth_When_GoSouthAction() {

    Room entranceRoom = game.getCurrentState().getBoard().getRoom(3, 4);
    game.getCurrentState().setCurrentRoom(entranceRoom);
    Room southRoom = game.getCurrentState().getBoard().getRoom(4, 4);
    game.goSouth();
    Assert.assertEquals(southRoom, game.getCurrentState().getCurrentRoom());

  }

  @Test
  public void movePlayerToWest_When_GoWestAction() {

    Room entranceRoom = game.getCurrentState().getBoard().getRoom(4, 4);
    game.getCurrentState().setCurrentRoom(entranceRoom);
    Room westRoom = game.getCurrentState().getBoard().getRoom(4, 3);
    game.goWest();
    Assert.assertEquals(westRoom, game.getCurrentState().getCurrentRoom());

  }

  @Test
  public void movePlayerToEast_When_GoEastAction() {

    Room entranceRoom = game.getCurrentState().getBoard().getRoom(4, 3);
    game.getCurrentState().setCurrentRoom(entranceRoom);
    Room eastRoom = game.getCurrentState().getBoard().getRoom(4, 4);
    game.goEast();
    Assert.assertEquals(eastRoom, game.getCurrentState().getCurrentRoom());

  }

  @Test
  public void movePlayerToPreviousRoom_When_StepBackAction() {

    Room entranceRoom = game.getCurrentState().getBoard().getRoom(4, 4);
    game.goWest();
    game.goPreviousRoom();
    Assert.assertEquals(entranceRoom, game.getCurrentState().getCurrentRoom());

  }

  @Test
  public void gameFinished_When_ExitRoomFound() {

    Assert.assertFalse(game.isFinished());
    Room exitRoom = game.getCurrentState().getBoard().getRoom(0, 0);
    game.getCurrentState().setCurrentRoom(exitRoom);
    Assert.assertTrue(game.isFinished());

  }

  @Test
  public void gameOver_When_PlayerDies() {

    Assert.assertFalse(game.isGameOver());
    Enemy enemy = Enemy.newInstance("Ghost 1", 40, new GhostAttackBehavior());
    Hero hero = Hero.newInstance("HeroTest", 0);
    game.setPlayer(hero);
    game.fight(enemy);
    Assert.assertTrue(game.isGameOver());
    Assert.assertTrue(game.getPlayer().isDead());
  }

  @Test
  public void damagePlayer_When_AttackedByEnemy() {

    Enemy enemy = Enemy.newInstance("Ghost", 40, new GhostAttackBehavior());
    Hero hero = Hero.newInstance("HeroTest", 100);
    game.setPlayer(hero);
    int hpBeforeFight = hero.getCurretHP();
    game.fight(enemy);
    int hpAfterFight = hero.getCurretHP();
    Assert.assertTrue(hpAfterFight < hpBeforeFight);

    enemy = Enemy.newInstance("Goblin", 40, new GoblinAttackBehavior());
    hero = Hero.newInstance("HeroTest", 100);
    game.setPlayer(hero);
    hpBeforeFight = hero.getCurretHP();
    game.fight(enemy);
    hpAfterFight = hero.getCurretHP();
    Assert.assertTrue(hpAfterFight < hpBeforeFight);

    enemy = Enemy.newInstance("Zombie", 40, new ZombieAttackBehavior());
    hero = Hero.newInstance("HeroTest", 100);
    game.setPlayer(hero);
    hpBeforeFight = hero.getCurretHP();
    game.fight(enemy);
    hpAfterFight = hero.getCurretHP();
    Assert.assertTrue(hpAfterFight < hpBeforeFight);
  }

  @Test
  public void damageEnemy_When_HitByWeapon() {

    Enemy enemy = Enemy.newInstance("Ghost 1", 40, new GhostAttackBehavior());
    Hero hero = Hero.newInstance("HeroTest", 100);
    hero.setWeapon(Bow.newInstance("Bow", 100));
    game.setPlayer(hero);
    int hpBeforeFight = enemy.getCurretHP();
    game.fight(enemy);
    int hpAfterFight = enemy.getCurretHP();
    Assert.assertTrue(hpAfterFight < hpBeforeFight);

    enemy = Enemy.newInstance("Ghost 1", 40, new GoblinAttackBehavior());
    hero = Hero.newInstance("HeroTest", 100);
    hero.setWeapon(Poison.newInstance("poison", 100));
    game.setPlayer(hero);
    hpBeforeFight = enemy.getCurretHP();
    game.fight(enemy);
    hpAfterFight = enemy.getCurretHP();
    Assert.assertTrue(hpAfterFight < hpBeforeFight);

    enemy = Enemy.newInstance("Ghost 1", 40, new ZombieAttackBehavior());
    hero = Hero.newInstance("HeroTest", 100);
    hero.setWeapon(Knife.newInstance("knife"));
    game.setPlayer(hero);
    hpBeforeFight = enemy.getCurretHP();
    game.fight(enemy);
    hpAfterFight = enemy.getCurretHP();
    Assert.assertTrue(hpAfterFight < hpBeforeFight);
  }

  @Test
  public void weaponEmpty_When_NoAmmo() {
    Enemy enemy = Enemy.newInstance("Ghost 1", 100, new GhostAttackBehavior());
    Hero hero = Hero.newInstance("HeroTest", 100);
    hero.setWeapon(Bow.newInstance("Bow", 1));
    game.setPlayer(hero);
    game.fight(enemy);
    Assert.assertTrue(((Bow) hero.getWeapon()).isEmpty());
    ((Bow) hero.getWeapon()).rechargeAmmo(1);
    Assert.assertFalse(((Bow) hero.getWeapon()).isEmpty());

    enemy = Enemy.newInstance("Ghost 1", 100, new GhostAttackBehavior());
    hero = Hero.newInstance("HeroTest", 100);
    hero.setWeapon(Poison.newInstance("Poison", 1));
    game.setPlayer(hero);
    game.fight(enemy);
    Assert.assertTrue(((Poison) hero.getWeapon()).isEmpty());
    ((Poison) hero.getWeapon()).rechargeAmmo(1);
    Assert.assertFalse(((Poison) hero.getWeapon()).isEmpty());

  }

  @Test
  public void setOldState_When_ResumeGameRequested() {

    game.goNorth();
    Room northRoom = game.getCurrentState().getCurrentRoom();
    game.saveCurrentGame("id123");
    Assert.assertTrue(game.getSavedGames().hasNext());
    game.initState();
    Assert.assertNotEquals(northRoom, game.getCurrentState().getCurrentRoom());
    game.restoreState("id123");
    Assert.assertEquals(northRoom, game.getCurrentState().getCurrentRoom());
  }
}
