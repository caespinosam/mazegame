package com.mazegame.view.cmd.views.input;

import java.util.Iterator;
import java.util.Scanner;

import com.mazegame.view.cmd.printer.PrintMessage;
import com.mazegame.view.menu.Menu;
import com.mazegame.view.menu.MenuOption;

/**
 * This input-handler displays a menu via command line and returns the selected option.
 * @author Cesar 
 *
 * @param <T>
 */
public class MenuInputHandler<T> implements IInputHandler<MenuOption<T>>{
   
    /** The menu to show */
    private Menu<T> menu;
    /** The selected option.*/
    private MenuOption<T> value;
    
    private final Scanner scanner = new Scanner(System.in);
    
    public MenuInputHandler(Menu<T> menu){
        this.menu = menu;
    }
    
    public void show() {
        value = null;
        while (value == null) {
            PrintMessage.print(menu.getLabel() + ":");
            Iterator<MenuOption<T>> iteratorOptions = menu.getOptions();
            while (iteratorOptions.hasNext()) {            
                MenuOption<T> option = iteratorOptions.next();
                PrintMessage.printf("%2s. %s \n", option.getId(), option.getText());
            }
            PrintMessage.print("\n> ");
            value = menu.getSelectedOption(scanner.next());
            if (value == null) {
                PrintMessage.print("Invalid option! ");
            }
        }
    }
    
    public MenuOption<T> getInput() {
        return value;
    }
    
  
}
