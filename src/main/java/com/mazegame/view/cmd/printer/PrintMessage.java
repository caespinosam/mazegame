package com.mazegame.view.cmd.printer;

import java.io.IOException;

/**
 * This class is used to print messages to the terminal.
 * 
 * @author Cesar
 *
 */
public class PrintMessage {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void pressEnterToContinue() {
        System.out.println("Press Enter to continue...");

        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("Error reading your input: " + e.getMessage());
        }
    }

}
