package ui.cli;

import javax.swing.*;
import java.util.List;
import java.util.function.Function;

public abstract class MenuBarItem extends JMenu {
    /*
     * Class Description:
     * This is an abstract class which was originally an interface before I wanted it to extend JMenu. It acts as a
     * superclass for every menu bar item other than the search bar such that all of the menu bar items can easily
     * be added to a list and will have the functions available which are needed. The menu bar items don't actually do
     *  very much since I never implemented the profiles which I had originally planned.
     */

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public abstract String getCommandString();

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public Function<List<String>, Boolean> getParser() {
        return this::parse;
    }

    /*
     * REQUIRES: subList is not null
     * MODIFIES: none
     * EFFECTS :
     */
    public abstract Boolean parse(List<String> subList);
}
