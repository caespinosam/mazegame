package com.mazegame.view.cmd.printer;

import com.mazegame.core.model.character.Enemy;
import com.mazegame.core.model.game.Room;
import com.mazegame.core.model.game.State;

/**
 * This strategy prints the map as a basic table with no colors or effects.
 * It prints the borders of each cell.
 * 
 * @author Cesar
 *
 */
public class PrinterMapSimpleTableStrategy implements IPrinterMapStrategy {

    private static final char BORDER_KNOT = '+';
    private static final char HORIZONTAL_BORDER = '-';
    private static final char VERTICAL_BORDER = '|';

    private static final String DEFAULT_AS_NULL = " ";

    private final State state;

    public PrinterMapSimpleTableStrategy(State state) {
        this.state = state;
    }

    @Override
    public void print() {
        print(state.getBoard().getRooms());

    }

    public void print(Room[][] table) {
        PrintMessage.print("North");
        final int[] widths = new int[getMaxColumns(table)];
        adjustColumnWidths(table, widths);
        printPreparedTable(table, widths, getHorizontalBorder(widths));
        PrintMessage.print("South");
    }

    private void printPreparedTable(Room[][] table, int widths[], String horizontalBorder) {
        final int lineLength = horizontalBorder.length();
        PrintMessage.print(horizontalBorder);
        for (final Room[] row : table) {
            if (row != null) {
                PrintMessage.print(getRow(row, widths, lineLength));
                PrintMessage.print(horizontalBorder);
            }
        }
    }

    private String getRow(Room[] row, int[] widths, int lineLength) {
        final StringBuilder builder = new StringBuilder(lineLength).append(VERTICAL_BORDER);
        final int maxWidths = widths.length;
        for (int i = 0; i < maxWidths; i++) {

            builder.append(padRight(getCellValue(safeGet(row, i, null)), widths[i])).append(VERTICAL_BORDER);

        }
        return builder.toString();
    }

    private String getHorizontalBorder(int[] widths) {
        final StringBuilder builder = new StringBuilder(256);
        builder.append(BORDER_KNOT);
        for (final int w : widths) {
            for (int i = 0; i < w; i++) {
                builder.append(HORIZONTAL_BORDER);
            }
            builder.append(BORDER_KNOT);
        }
        return builder.toString();
    }

    private int getMaxColumns(Room[][] rows) {
        int max = 0;
        for (final Room[] row : rows) {
            if (row != null && row.length > max) {
                max = row.length;
            }
        }
        return max;
    }

    private void adjustColumnWidths(Room[][] rows, int[] widths) {
        for (final Room[] row : rows) {
            if (row != null) {
                for (int c = 0; c < widths.length; c++) {
                    final String cv = getCellValue(safeGet(row, c, DEFAULT_AS_NULL));
                    final int l = cv.length();
                    if (widths[c] < l) {
                        widths[c] = l;
                    }
                }
            }
        }
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public String safeGet(Room[] array, int index, String defaultValue) {
        if (array[index] != null) {
            if (state.getCurrentRoom().equals(array[index])) {
                return "(you)";
            }
           

            if (!array[index].isVisited()) {
                return "(not visited)";
            } else {
                if (array[index].getEnemy() != null) {
                    Enemy enemy = array[index].getEnemy();
                    if (!enemy.isDead()) {
                        return "Enemy";
                    }
                }
                return "(visited)";
            }

        }

        return " ";
    }

    private String getCellValue(Object value) {
        return value == null ? DEFAULT_AS_NULL : value.toString();
    }

}
