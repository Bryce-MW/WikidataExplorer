package model.prefrences;

import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager extends MenuBarItem {
    /*
     * Class Description:
     * This class would have been a menu of all of the possible preferences which could have been set. I never ended
     * up implementing any preferences so this class does nt do very much at the moment.
     */
    private final ArrayList<UserPreference> preferences;

    /*
     * REQUIRES: preferences is not null
     * MODIFIES: this
     * EFFECTS : creates a new preference manager
     */
    public PreferenceManager(List<UserPreference> preferences) {
        this.preferences = new ArrayList<>(preferences.size());
        this.preferences.addAll(preferences);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the list of prefrences
     */
    public List<UserPreference> getPreferences() {
        return preferences;
    }


    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the CLI representation of this manager
     */
    @Override
    public String toString() {
        return "E: Preferences";
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the string which is used in the CLI to send commands to the prefrence manager
     */
    @Override
    public String getCommandString() {
        return "E";
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : parses a REPL command directed to this manager
     */
    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
