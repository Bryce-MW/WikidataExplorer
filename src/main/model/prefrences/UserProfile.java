package model.prefrences;

import ui.cli.LayoutManager;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends MenuBarItem {
    /*
     * Class Description:
     * I never ended up implementing this class but the idea what that a user profile would contain a number of
     * layouts as well as user preferences which I also did not implement.
     */
    private final PreferenceManager preferences;
    private final LayoutProfileManager layout;

    private final String name;

    /*
     * REQUIRES: name is not null, defaultLayout is not null
     * MODIFIES: this
     * EFFECTS : creates a new user profile
     */
    public UserProfile(String name, LayoutManager defaultLayout) {
        preferences = new PreferenceManager(new ArrayList<>(1)); // For now, there are no preferences.
        layout = new LayoutProfileManager(new LayoutProfile("Default", defaultLayout)); // I won't bother
        // localizing this
        this.name = name;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the list of preferences for this profile
     */
    public PreferenceManager getPreferences() {
        return preferences;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the list of layout profiles for this profile
     */
    public LayoutProfileManager getLayoutProfileManager() {
        return layout;
    }


    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the string for displaying in the CLI
     */
    @Override
    public String toString() {
        return "P: " + name;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the command string which is used to send commands to this item in the CLI
     */
    @Override
    public String getCommandString() {
        return "P";
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : parses a REPL command directed at this item
     */
    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
