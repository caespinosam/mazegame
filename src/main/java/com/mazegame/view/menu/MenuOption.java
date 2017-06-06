package com.mazegame.view.menu;

/**
 * An option in the menu.
 * 
 * @author Cesar
 *
 * @param <T> the type to return
 * 
 */
public class MenuOption<T> {

  /** The id to select this option. */
  private String id;
  /** The label to show. */
  private String text;
  /** The value to return in case this option is selected. */
  private T value;

  /**
   * 
   * @param id The id to select this option.
   * @param text The label to show in the menu component.
   * @param value The value to return in case this option is selected.
   */
  public MenuOption(String id, String text, T value) {
    super();
    this.id = id;
    this.text = text;
    this.value = value;

  }

  public String getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public T getSelectedValue() {
    return value;
  }

}
