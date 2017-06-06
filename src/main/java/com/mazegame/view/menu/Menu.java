package com.mazegame.view.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of available options.
 * 
 * @author Cesar 
 *
 * 
 */
public class Menu<T> {

    /** Title.*/
    private String label;
    /** List of available option to show.*/
    private List<MenuOption<T>> options = new ArrayList<>();

    public Menu(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void addOption(MenuOption<T> option) {
        options.add(option);
    }

    public Iterator<MenuOption<T>> getOptions() {
        return options.iterator();
    }

    public void clearOptions() {
        options.clear();
    }
    
    /**
     * Returns the specified option.
     *@param selectedId the id to look up
     *@return the specified option.
     */
    public MenuOption<T> getSelectedOption(String selectedId) {
        for (MenuOption<T> option : options) {
            if (option.getId().equals(selectedId)) {
                return option;
            }
        }

        return null;
    }

}
