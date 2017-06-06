package com.mazegame.view.cmd.printer;

/**
 * Print the map using the specified strategy.
 * @author Cesar 
 *
 */
public final class PrinterMap {

    /**
     * It applies the specified strategy to print the map. 
     *@param strategy  the printing method to apply
     */
    public static void printMap(IPrinterMapStrategy strategy) {
        strategy.print();
        PrintMessage.pressEnterToContinue();
    }
}
