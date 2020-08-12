package ui.cli;

import javax.swing.*;
import java.util.List;
import java.util.function.Function;

public abstract class MenuBarItem extends JMenu {
    /*
     * Class Description:
     *
     */

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract String getCommandString();

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Function<List<String>, Boolean> getParser() {
        return this::parse;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public abstract Boolean parse(List<String> subList);
}
