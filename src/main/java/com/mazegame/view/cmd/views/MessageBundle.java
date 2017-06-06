package com.mazegame.view.cmd.views;

import java.util.ResourceBundle;

/**
 * Reads the messages bundle file.
 * @author xpdev
 *
 */
public final class MessageBundle
{

 private static final  ResourceBundle rb = ResourceBundle.getBundle("messages");
 
 public static final String MAP_NORTH = "map.north";
 public static final String MAP_SOUTH = "map.south";
 public static final String MAP_YOU = "map.you";
 public static final String MAP_VISITED = "map.visited";
 public static final String MAP_NOT_VISITED = "map.not.visited";
 public static final String MAP_ENEMY= "map.enemy";
 
 public static final String PRESS_ENTER = "press.enter";
 public static final String ATTACK_RESULTS = "attack.results";
 public static final String USE_WEAPON_FROM_BAG = "use.weapon.from.bag";
 public static final String NO_WEAPONS_IN_BAG = "no.weapons.in.bag";
 public static final String NO_HERO_CREATED = "no.hero.created";
 public static final String SELECTED_HERO = "selected.hero";
 public static final String BYE_MESSAGE = "bye.message";
 
 public static final String PLAYER_DIED = "player.died";
 public static final String PLAYER_ATTACKED = "player.attacked";
 public static final String PLAYER_HP = "player.hp";
 public static final String PLAYER_WEAPON = "player.weapon";
 public static final String PLAYER_NAME = "player.name";
 
 public static final String ERROR_READING_INPUT = "error.reading.input";
 public static final String ERROR_CHECK_MAP = "error.check.map";
 public static final String ERROR_NO_CHARACTERS = "error.no.characters";
 public static final String ERROR_INVALID_OPTION = "error.invalid.option";
 
 public static final String ROOM_WITH_ENEMY = "room.with.enemy";
 public static final String ROOM_NAME = "room.name";
 public static final String ROOM_DESCRIPTION = "room.description";
 
 public static final String ENEMY_NAME = "enemy.name";
 public static final String ENEMY_ATTACK = "enemy.attack";
 public static final String ENEMY_HP = "enemy.hp";
 public static final String ENEMY_DEFEATED = "enemy.defeated";
 public static final String ENEMY_DIED = "enemy.died";
 
 public static final String MENU_SELECT_ACTION = "menu.select.action";
 public static final String MENU_SELECT_OPTION = "menu.select.option";
 public static final String MENU_SELECT_CHARACTER = "menu.select.character";
 public static final String MENU_ATTACK = "menu.attack";
 public static final String MENU_STEP_BACK = "menu.step.back";
 public static final String MENU_CHANGE_WEAPON = "menu.change.weapon";
 public static final String MENU_GO_NORTH = "menu.go.north";
 public static final String MENU_GO_SOUTH = "menu.go.south";
 public static final String MENU_GO_EAST = "menu.go.east";
 public static final String MENU_GO_WEST = "menu.go.west";
 public static final String MENU_VIEW_MAP = "menu.view.map";
 public static final String MENU_WHERE_TO_GO = "menu.where.to.go";
 public static final String MENU_ENTER_NAME = "menu.enter.name";
 public static final String MENU_CHOOSE_WEAPON = "menu.choose.weapon";
 public static final String MENU_KNIFE = "menu.knife";
 public static final String MENU_BOW = "menu.bow";
 public static final String MENU_POISON = "menu.poison";
 public static final String MENU_CREATE_HERO = "menu.create.hero";
 public static final String MENU_PLAY_GAME = "menu.play.game";
 public static final String MENU_EXIT = "menu.exit";
 
 public static final String TITTLE_CREATE_CHARACTER = "title.create.character";
 public static final String TITTLE_MAIN_MENU = "title.main.menu";
 public static final String TITTLE_ROOM = "title.room";
 public static final String TITTLE_SELECT_CHARACTER = "title.select.character";
 public static final String TITTLE_EXIT_FOUND = "title.exit.found";

/**
 * Returns the specified text stored in the message bundle file.
 * @param key
 * @return
 */
 public static String  getMessage(String key){
   
   return  rb.getString(key);
 }
  
}
