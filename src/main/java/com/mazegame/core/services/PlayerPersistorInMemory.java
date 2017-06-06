package com.mazegame.core.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mazegame.core.exception.PlayerAlreadyExistException;
import com.mazegame.core.model.character.Hero;

/**
 * A basic implementation of {@link IPlayerPersistor}.
 * It stores the new heroes in memory.
 * @author Cesar 
 *
 */
public class PlayerPersistorInMemory  implements IPlayerPersistor {

    private final static PlayerPersistorInMemory INSTANCE = new PlayerPersistorInMemory();
    /** The players created.*/
    private List<Hero> availablePlayers = new ArrayList<>();
    
    private PlayerPersistorInMemory() {
        
    }

    @Override
    public void savePlayer(Hero p) throws PlayerAlreadyExistException {
        if (getPlayerByName(p.getName()) != null) {
            throw new PlayerAlreadyExistException(p.getName());
        }
        availablePlayers.add(p);
    }

    @Override
    public Iterator<Hero> getPlayers() {
      List<Hero> heroes = new ArrayList<>();
      for (Hero hero : availablePlayers) {
    	heroes.add(hero.clone());
      }
        return heroes.iterator();
    }

    @Override
    public Hero getPlayerByName(String name) {
        for (Hero gc : availablePlayers) {
            if (gc.getName().equals(name)) {
                return gc.clone();
            }
        }
        return null;
    }

    /**
     * Returns a singleton instance.
     *@return
     */
    public static final PlayerPersistorInMemory getInstance() {
        return INSTANCE;
    }

}
