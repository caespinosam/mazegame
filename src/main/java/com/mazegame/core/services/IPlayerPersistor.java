package com.mazegame.core.services;

import java.util.Iterator;

import com.mazegame.core.exception.PlayerAlreadyExistException;
import com.mazegame.core.model.character.Hero;

/**
 * Data access methods to manage new heros.
 * 
 * @author Cesar
 *
 */
public interface IPlayerPersistor {

    /**
     * Persists a new hero.
     * 
     * @param p the hero
     * @throws PlayerAlreadyExistException in case the name already exist
     */
    public void savePlayer(Hero p) throws PlayerAlreadyExistException;

    /**
     * Returns the heroes/players that have been created.
     * 
     * @return heroes that have been created
     */
    public Iterator<Hero> getPlayers();

    /**
     * Returns the hero/player with the specified name.
     * 
     * @param name the name to filter.
     * @return the hero/player with the specified name.
     */
    public Hero getPlayerByName(String name);
}
