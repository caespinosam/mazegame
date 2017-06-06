package com.mazegame.view.cmd.printer;

import static com.mazegame.view.cmd.views.MessageBundle.ERROR_READING_INPUT;
import static com.mazegame.view.cmd.views.MessageBundle.PRESS_ENTER;
import static com.mazegame.view.cmd.views.MessageBundle.getMessage;

import java.io.IOException;

/**
 * This class is used to print messages to the terminal.
 * 
 * @author Cesar
 *
 */
public final class PrintMessage {

    public static void print(String message) {
        System.out.println(message);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void pressEnterToContinue() {
        System.out.println(getMessage(PRESS_ENTER));

        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(getMessage(ERROR_READING_INPUT) + e.getMessage());
        }
    }

}
