package com.mazegame.view.cmd.views.input;

import java.util.Scanner;

import com.mazegame.view.cmd.printer.PrintMessage;

/**
 * This input handler asks for a string value and returns the entered value.
 * 
 * @author Cesar
 *
 */
public class TextInputHandler implements IInputHandler<String> {

  private Scanner scanner = new Scanner(System.in);
  private String label;
  private String value;

  public TextInputHandler(String label) {
    this.label = label;
  }

  @Override
  public void show() {
    value = null;
    while (value == null) {
      PrintMessage.print(label);
      PrintMessage.print("\n> ");
      value = scanner.next();
    }
  }

  @Override
  public String getInput() {
    return value;
  }
}
